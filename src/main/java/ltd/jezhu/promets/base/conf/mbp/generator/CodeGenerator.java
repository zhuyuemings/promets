package ltd.jezhu.promets.base.conf.mbp.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 代码生成器
 * 参考：https://mp.baomidou.com/config/generator-config.html#基本配置
 * @author ymzhu
 * @date 2019/12/3 17:15
 */
@Log4j2
public class CodeGenerator {

    public static void main(String[] args) {
        generateCode();
    }

    /*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓执行生成仅需要修改以下常量↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/
    /**
     * 定义数据库连接地址、驱动、用户名、密码
     */
    private final static String DB_URL = "jdbc:mysql://47.112.11.98:3306/promets?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private final static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "csvCYlrRh4>#";
    /**
     * 定义生成代码所属模块
     */
    private final static String MODULE_NAME = "generated";
    /**
     * 定义需要生成代码的数据库表，多个表用英文逗号分隔
     */
    private final static String[] TABLES = {"demo_demo"};
    /**
     * 定义生成代码注释作者，即执行本次生成或负责本模块开发的开发者
     */
    private final static String AUTHOR = "ymzhu";
    /**
     * 定义生成代码类的注释，选填
     */
    private final static String COMMENT = "";
    /**
     * 定义生成代码文件的描述，即生成代码的特别说明，选填
     */
    private final static String DESCRIPTION = "Generated by mybatis-plus generator.";
    /**
     * 定义生成代码文件的版本号，即项目版本号，必填
     */
    private final static String VERSION = "3.0.1-SNAPSHOT";
    /**
     * 忽略实体类列表，多个用逗号分隔，个别实体类出现手动修改时请加入该列表(建议使用文件全名，例如："Demo.java")，以防止重新生成时发生覆盖，重新生成时也要关注该列表
     */
    private final static String IGNORE_ENTITY_LIST = "";
    /*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑执行生成仅需要修改以上常量↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*/

    /**
     * 定义生成代码所在项目路径，此处将代码生成至lejia-dao子项目中
     */
    private final static String PROJECT_PATH = System.getProperty("user.dir");
    /**
     * 定义生成代码基本路径
     */
    private final static String OUT_PUT_DIR = PROJECT_PATH + "/src/main/java";
    /**
     * 定义生成代码后是否打开输出路径
     */
    private final static boolean OPEN_OUT_PUT_DIR = false;
    /**
     * 定义生成代码所在包路径（最终位置由PACKAGE_PARENT+MODULE_NAME+具体业务[mapper/entity/service/svc]组成）
     */
    private final static String PACKAGE_PARENT = "ltd.jezhu.promets";
    /**
     * 定义生成控制器所在包名
     */
    private final static String PACKAGE_CONTROLLER = "svc";
    /**
     * 定义生成控制器后缀名（%s占位符）
     */
    private final static String CONTROLLER_SUFFIX = "%sSvc";
    /**
     * 自定义模板变量
     */
    private final static String VM_DATETIME = "datetime";
    private final static String VM_COMMENT = "comment";
    private final static String VM_DESCRIPTION = "description";
    private final static String VM_VERSION = "version";
    /**
     * 定义注释时间格式
     */
    private final static String DATE_FORMAT = "yyyy.MM.dd HH:mm";

    private static void generateCode() {

        AutoGenerator mpg = new AutoGenerator();
        /* 全局配置 */
        GlobalConfig gc = new GlobalConfig();
        gc.setDateType(DateType.ONLY_DATE);
        gc.setControllerName(CONTROLLER_SUFFIX);
        gc.setOutputDir(OUT_PUT_DIR);
        gc.setAuthor(AUTHOR);
        gc.setOpen(OPEN_OUT_PUT_DIR);
        mpg.setGlobalConfig(gc);
        /* 数据源配置 */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        dsc.setDriverName(DB_DRIVER);
        dsc.setUsername(DB_USERNAME);
        dsc.setPassword(DB_PASSWORD);
        mpg.setDataSource(dsc);
        /* 包配置 */
        PackageConfig pc = new PackageConfig();
        pc.setParent(PACKAGE_PARENT);
        pc.setXml(pc.getMapper());
        pc.setServiceImpl(pc.getService());
        pc.setController(PACKAGE_CONTROLLER);
        pc.setModuleName(MODULE_NAME);
        mpg.setPackageInfo(pc);
        /* 自定义模板变量 */
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(4);
                //通过cfg.datetime引用
                map.put(VM_DATETIME, new SimpleDateFormat(DATE_FORMAT).format(new Date()));
                map.put(VM_COMMENT, COMMENT);
                map.put(VM_DESCRIPTION, DESCRIPTION);
                map.put(VM_VERSION, VERSION);
                this.setMap(map);
            }
        };
        /* 定义生成entity覆盖已存在代码 */
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                //判断自定义文件夹是否需要创建,这里调用默认的方法
                checkDir(filePath);
                //对于已存在的文件，只需重复生成 entity
                File file = new File(filePath);
                boolean exist = file.exists();
                String filename = file.getName();
                if (exist) {
                    return FileType.ENTITY == fileType && !IGNORE_ENTITY_LIST.contains(filename);
                }
                //不存在的文件都需要创建
                return true;
            }
        });
        mpg.setCfg(cfg);
        /* 配置模板 */
        TemplateConfig templateConfig = new TemplateConfig();
        mpg.setTemplate(templateConfig);
        /* 策略配置 */
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(ALL_TABLES);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("");
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        strategy.setTableFillList(fillList());
        strategy.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);
        /* 定义模板引擎 */
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

    private final static String[] ALL_TABLES = {
            "sys_user_info",
            "user_bound_config",
            "user_diary_data",
            "wx_user_info",
    };

    /**
     * 默认填充字段
     */
    private static List<TableFill> fillList() {
        List<TableFill> fillList = new ArrayList<>();
        fillList.add(new TableFill("is_deleted", FieldFill.INSERT));
        fillList.add(new TableFill("created_at", FieldFill.INSERT));
        fillList.add(new TableFill("modified_at", FieldFill.UPDATE));
        fillList.add(new TableFill("deleted_at", FieldFill.UPDATE));
        return fillList;
    }
}
