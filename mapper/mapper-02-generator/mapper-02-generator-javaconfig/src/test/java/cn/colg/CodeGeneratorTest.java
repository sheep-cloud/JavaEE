package cn.colg;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
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

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
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
        genCode("product_info");
    }

    /** JDBC 配置 */
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sell?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    /** 项目基础包名称 */
    public static final String BASE_PACKAGE = "cn.colg";
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".mapper";
    public static final String ROOT_CLASS = "cn.colg.core.BaseEntity";

    /** 项目的基础路径 */
    private static final String JAVA_PATH = "src/main/java";
    private static final String RESOURCES_PATH = "src/main/resources";
    private static final String MAPPER_PATH = "/mybatis/mapper";

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
    }

    /**
     * 生成Model、Mapper
     * 
     * @param tableName
     * @param modelName
     */
    public static void genModelAndMapper(String tableName, String modelName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("Mysql");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, CharsetUtil.UTF_8);
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        // Mapper插件
        PluginConfiguration mapperPlugin = new PluginConfiguration();
        mapperPlugin.setConfigurationType("cn.colg.plugin.LombokPlugin");
        // 指定mapper接口父类
        mapperPlugin.addProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        // 强制生成注解
        mapperPlugin.addProperty("forceAnnotation", "false");
        context.addPluginConfiguration(mapperPlugin);

        // 实现Serializable接口
        PluginConfiguration serializablePlugin = new PluginConfiguration();
        serializablePlugin.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        context.addPluginConfiguration(serializablePlugin);

        // 数据库连接的信息：驱动类、连接地址、用户名、密码
        JDBCConnectionConfiguration jdbcConnection = new JDBCConnectionConfiguration();
        jdbcConnection.setConnectionURL(JDBC_URL);
        jdbcConnection.setUserId(JDBC_USERNAME);
        jdbcConnection.setPassword(JDBC_PASSWORD);
        jdbcConnection.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnection);

        // 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer，为 true时把JDBC DECIMAL 和 NUMERIC 类型解析为java.math.BigDecimal
        JavaTypeResolverConfiguration javaTypeResolver = new JavaTypeResolverConfiguration();
        javaTypeResolver.addProperty(PropertyRegistry.TYPE_RESOLVER_FORCE_BIG_DECIMALS, "true");
        context.setJavaTypeResolverConfiguration(javaTypeResolver);

        // 定义Java模型生成器的属性 - entity
        JavaModelGeneratorConfiguration javaModelGenerator = new JavaModelGeneratorConfiguration();
        javaModelGenerator.setTargetProject(JAVA_PATH);
        javaModelGenerator.setTargetPackage(MODEL_PACKAGE);
        javaModelGenerator.addProperty(PropertyRegistry.ANY_ROOT_CLASS, ROOT_CLASS);
        context.setJavaModelGeneratorConfiguration(javaModelGenerator);

        // mapper映射生成器的属性 - mapper.xml
        SqlMapGeneratorConfiguration sqlMapGenerator = new SqlMapGeneratorConfiguration();
        sqlMapGenerator.setTargetProject(RESOURCES_PATH);
        sqlMapGenerator.setTargetPackage(MAPPER_PATH);
        context.setSqlMapGeneratorConfiguration(sqlMapGenerator);

        // mapper接口生成器的属性 - dao层
        JavaClientGeneratorConfiguration javaClientGenerator = new JavaClientGeneratorConfiguration();
        javaClientGenerator.setTargetProject(JAVA_PATH);
        javaClientGenerator.setTargetPackage(MAPPER_PACKAGE);
        javaClientGenerator.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGenerator);

        // 指定数据库
        TableConfiguration table = new TableConfiguration(context);
        table.setTableName(tableName);
        if (StrUtil.isNotEmpty(modelName)) {
            table.setDomainObjectName(modelName);
            modelName = tableNameConvertUpperCamel(tableName);
        }
        // 主键策略
        table.setGeneratedKey(new GeneratedKey("id", "JDBC", true, null));
        context.addTableConfiguration(table);

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
        log.info("CodeGeneratorTest.genModelAndMapper() >> {}.Mapper.java : {}", modelName, "生成成功");
        log.info("CodeGeneratorTest.genModelAndMapper() >> {}.Mapper.xml : {}", modelName, "生成成功");
        log.info("CodeGeneratorTest.genModelAndMapper() >> {}.java : {}", modelName, "生成成功");
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

}