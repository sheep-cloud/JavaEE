package cn.colg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.google.common.base.CaseFormat;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 *
 * @author colg
 */
@Slf4j
public class CodeGeneratorTest {

    @Test
    public void testGenerator() throws Exception {
        // genCode("表名");
        // genCodeByCustomModelName("表名", "实体名");
        genCode("dept");
    }

    /** JDBC 配置 */
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/spring_cloud_8001?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
    private static final String JDBC_USERNAME = "spring_cloud";
    private static final String JDBC_PASSWORD = "123456";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    /** 项目基础包名称 */
    public static final String BASE_PACKAGE = "cn.colg";
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

    /** Mapper基础接口的完全限定名 */
    public static final String MAPPER_INTERFACE_REFERENCE = "tk.mybatis.mapper.common.Mapper";

    /** 项目的基础路径 */
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";
    private static final String JAVA_PATH = "/src/main/java";
    private static final String RESOURCES_PATH = "/src/main/resources";

    /** 生成代码存放路径 */
    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);

    /** 类上的注释 @author */
    private static final String AUTHOR = "colg";

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。 <br/>
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     * 
     * @param tableNames 数据表名称...
     */
    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, null);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码。<br/>
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * 
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String tableName, String modelName) {
        tableName = StrUtil.trim(tableName);
        modelName = StrUtil.trim(modelName);
        genModelAndMapper(tableName, modelName);
        genService(tableName, modelName);
        genController(tableName, modelName);
    }

    /**
     * 生成Model、Mapper
     * 
     * @param tableName
     * @param modelName
     */
    public static void genModelAndMapper(String tableName, String modelName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("MySQL");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, "UTF-8");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        // jdbc 配置
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        // 实现Serializable接口
        PluginConfiguration serializablePlugin = new PluginConfiguration();
        serializablePlugin.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        context.addPluginConfiguration(serializablePlugin);

        // Mapper插件
        PluginConfiguration mapperPlugin = new PluginConfiguration();
        mapperPlugin.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        // 强制生成注解
        mapperPlugin.addProperty("forceAnnotation", "true");
        // 指定dao层继承的父类
        mapperPlugin.addProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(mapperPlugin);

        // 定义Java模型生成器的属性 - entity
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE);
        // trimStrings
        javaModelGeneratorConfiguration.addProperty(PropertyRegistry.MODEL_GENERATOR_TRIM_STRINGS, "true");
        // 指定entity层继承的父类
        javaModelGeneratorConfiguration.addProperty(PropertyRegistry.ANY_ROOT_CLASS, BASE_PACKAGE + ".core.BaseEntity");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        // 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer，为 true时把JDBC DECIMAL 和 NUMERIC 类型解析为java.math.BigDecimal
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        javaTypeResolverConfiguration.addProperty(PropertyRegistry.TYPE_RESOLVER_FORCE_BIG_DECIMALS, "true");
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

        // 主键策略
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (StrUtil.isNotBlank(modelName)) {
            tableConfiguration.setDomainObjectName(modelName);
        }
        context.addTableConfiguration(tableConfiguration);

        // 定义SQL映射生成器的属性 - mapper.xml
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage("mybatis/mapper");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        // 定义Java客户端（Mapper接口）生成器的属性 - dao层
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StrUtil.isEmpty(modelName)) {
            modelName = tableNameConvertUpperCamel(tableName);
        }
        log.info("CodeGeneratorTest.genModelAndMapper() >> {}.Mapper.java : {}", modelName, "生成成功");
        log.info("CodeGeneratorTest.genModelAndMapper() >> {}.Mapper.xml : {}", modelName, "生成成功");
        log.info("CodeGeneratorTest.genModelAndMapper() >> {}.java : {}", modelName, "生成成功");
    }

    /**
     * 生成Service
     * 
     * @param tableName
     * @param modelName
     */
    public static void genService(String tableName, String modelName) {
        Dict data = new Dict(4);
        data.put("author", AUTHOR);
        String modelNameUpperCamel = StrUtil.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
        data.set("modelNameUpperCamel", modelNameUpperCamel)
            .set("modelNameLowerCamel", tableNameConvertLowerCamel(StrUtil.isEmpty(modelName) ? tableName : modelName))
            .set("basePackage", BASE_PACKAGE);

        File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            freemarker.template.Configuration cfg = getConfiguration();
            cfg.getTemplate("service.ftl").process(data, new FileWriter(file));
            log.info("CodeGeneratorTest.genService() >> {}Service.java : {}", modelNameUpperCamel, "生成成功");

            File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data, new FileWriter(file1));
            log.info("CodeGeneratorTest.genService() >> {}ServiceImpl.java : {}", modelNameUpperCamel, "生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    /**
     * 生成Controller
     * 
     * @param tableName
     * @param modelName
     */
    public static void genController(String tableName, String modelName) {
        Dict data = new Dict(5);
        String modelNameUpperCamel = StrUtil.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
        data.set("author", AUTHOR).set("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel))
            .set("modelNameUpperCamel", modelNameUpperCamel)
            .set("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel))
            .set("basePackage", BASE_PACKAGE);

        File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            freemarker.template.Configuration cfg = getConfiguration();
            Template template = cfg.getTemplate("controller.ftl");
            template.process(data, new FileWriter(file));
            log.info("CodeGeneratorTest.genController() >> {}Controller.java : {}", modelNameUpperCamel, "生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }
    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding(CharsetUtil.UTF_8);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    /**
     * 以小驼峰形式转换表名，SYS_USER -> sysUser
     * 
     * @param tableName
     * @return
     */
    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    /**
     * 以驼峰形式转换表名，SYS_USER -> SysUser
     * 
     * @param tableName
     * @return
     */
    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    /**
     * 如输入表名: SYS_USER, 返回 /sys/user; SYSUSER, 返回 /sysuser
     * 
     * 
     * @param tableName
     * @return
     */
    private static String tableNameConvertMappingPath(String tableName) {
        // 兼容使用大写的表名
        tableName = tableName.toLowerCase();
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    /**
     * 如输入Model名: SysUser, 返回 /sys/user; 输入Sysuser, 返回 /sysuser
     * 
     * @param modelName
     * @return
     */
    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    /**
     * 如输入包名：com.arrow, 返回 /com/arrow/
     * 
     * @param packageName
     * @return
     */
    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}