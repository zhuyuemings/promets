package ltd.jezhu.promets.conf.dfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * fast dfs 配置类
 * FdfsClientConfig 导入FastDFS-Client组件
 * EnableMBeanExport 解决jmx重复注册bean的问题
 * @author ymzhu
 * @since 2020/8/3 10:18
 */
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastDfsConfiguration {
}