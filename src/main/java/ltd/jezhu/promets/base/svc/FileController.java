package ltd.jezhu.promets.base.svc;

import ltd.jezhu.promets.base.conf.dfs.FastDfsClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 文件服务控制器
 * @author ymzhu
 * @since 2020/8/3 11:58
 */
@RestController
@RequestMapping("/fdfs")
public class FileController {
    @Autowired
    public FileController(){

    }
    private FastDfsClient dfsClient;

    @PostMapping("/upload")
    public void uploadFile(MultipartFile file) throws IOException {
        String s = dfsClient.uploadFile(file);
        String resAccessUrl = dfsClient.getResAccessUrl(s);
    }

    @GetMapping("/download")
    public void downloadFile(String filePath, HttpServletResponse response) throws IOException {
        byte[] bytes = dfsClient.downloadFile(filePath);
        String fileName = "哈哈.jpg";
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        //方式一
        // fileName=new String(fileName.getBytes(), "ISO8859-1")
        //方式二
        fileName = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        IOUtils.write(bytes, response.getOutputStream());
    }

    @GetMapping("/delete")
    public void deleteFile(String filePath) {
        Boolean result = dfsClient.deleteFile(filePath);
    }
}
