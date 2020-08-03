package ltd.jezhu.promets.base.conf.dfs;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import ltd.jezhu.promets.base.util.InjectUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * fast dfs 客户端
 * @author ymzhu
 * @since 2020/8/3 10:15
 */
@Component
public class FastDfsClient {


    private final FastFileStorageClient client;
    private final FdfsWebServer server;

    @Autowired
    public FastDfsClient(
            FastFileStorageClient client,
            FdfsWebServer server
    ) {
        this.client = InjectUtils.check(client);
        this.server = InjectUtils.check(server);
    }

    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = client.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        String fullPath = storePath.getFullPath();
        getResAccessUrl(fullPath);
        return fullPath;

    }

    public String uploadFile(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            StorePath storePath = client.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
            return storePath.getFullPath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] downloadFile(String filePath) {
        StorePath storePath = StorePath.parseFromUrl(filePath);
        return client.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
    }

    public Boolean deleteFile(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return false;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(filePath);
            client.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 封装文件完整URL地址
     */
    public String getResAccessUrl(String path) {
        String url = server.getWebServerUrl() + path;
        System.out.println("上传文件地址为：\n" + url);
        return url;
    }
}
