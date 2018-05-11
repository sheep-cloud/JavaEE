package cn.colg.plugins;

import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

/**
 * 扩展生成实体类的规则
 *
 * @author colg
 */
public class LombokGenerator extends DefaultCommentGenerator {

    /** 是否生成注释 */
    private boolean suppressAllComments;
    /** 是否生成时间戳 */
    private boolean suppressDate;

    public LombokGenerator() {
        suppressAllComments = false;
        suppressDate = false;
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        suppressAllComments = StringUtility.isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
        suppressDate = StringUtility.isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
    }

    /**
     * field 注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        // 字段注释
        String remarks = introspectedColumn.getRemarks();
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + remarks);
        field.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {}

    /**
     * class 注释
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

        Console.log(introspectedTable.getRemarks());

        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" *  - entity");
        topLevelClass.addJavaDocLine(" *");
        topLevelClass.addJavaDocLine(" * @author colg");
        if (suppressDate) {
            topLevelClass.addJavaDocLine(" * @date " + DateUtil.now());
        }
        topLevelClass.addJavaDocLine(" */");

        // ======================= @Table BEGN
        /*
        String tableName = introspectedTable.getFullyQualifiedTable().toString();// 表名
        topLevelClass.addAnnotation("@Table(name = \"" + tableName + "\")");
        */
        // ======================= @Table END

        topLevelClass.addImportedType("lombok.AllArgsConstructor");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.ToString");
        topLevelClass.addImportedType("lombok.experimental.Accessors");

        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
        topLevelClass.addAnnotation("@ToString");
        topLevelClass.addAnnotation("@Accessors(chain = true)");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {}

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {}

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {}

    /**
     * get 注释
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        // 字段名
        String lowerCase = introspectedColumn.getActualColumnName();
        // 字段注释
        String remarks = introspectedColumn.getRemarks();
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 获取" + remarks);
        method.addJavaDocLine(" *");
        method.addJavaDocLine(" * @return " + lowerCase + " - " + remarks);
        method.addJavaDocLine(" */");
    }

    /**
     * set 注释
     */
    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        // 字段注释
        String remarks = introspectedColumn.getRemarks();
        String name = method.getParameters().get(0).getName();
        method.addJavaDocLine("/**");
        method.addJavaDocLine("/* 设置" + name);
        method.addJavaDocLine(" *");
        method.addJavaDocLine(" * @param " + name + " " + remarks);
        method.addJavaDocLine(" */");
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {}

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {}

    @Override
    public void addComment(XmlElement xmlElement) {}

    @Override
    public void addRootComment(XmlElement rootElement) {}
    
}