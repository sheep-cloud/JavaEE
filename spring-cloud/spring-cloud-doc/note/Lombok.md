# eclipse中使用Lombok
## 1、Lombok安装
1. 下载lombok.jar包 https://projectlombok.org/download.html

2. 运行Lombok.jar: 
     `java -jar E:\Maven\repository\org\projectlombok\lombok\1.16.20\lombok-1.16.20.jar`lombok.jar所在路径

  数秒后将弹出一框，以确认eclipse的安装路径

3. 确认完eclipse的安装路径后，点击install/update按钮，即可安装完成

4. 安装完成之后，请确认eclipse安装路径下是否多了一个`lombok.jar`包，并且其

  配置文件eclipse.ini中是否 添加了如下内容: 

   `-javaagent:D:\Spring Tool Suite\spring-tool-suite-3.7.3.RELEASE\lombok.jar`

  如果上面的答案均为true，那么恭喜你已经安装成功，否则将缺少的部分添加到相应的位置即可

5. 重启eclipse或spring-tool-suite

## 2、Lombok 基本用法
- entity

  ```java
  /**
   * 部门Entity </br>
   * 
   * <pre>
   * Dept(Entity) orm     mysql -> Dept(table)    类表关系映射
   * 
   * lombok：
   *  '@NoArgsConstructor'：       无参构造
   *  '@AllArgsConstructor':       全参构造
   *  '@Data':                     getter/setter、重写：toString()、hashCoode()、equals()
   *  '@Accessors(chain = true)':  链式编程
   * </pre>
   * 
   * @author colg
   */
  @ApiModel(description = "部门Entity")
  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Accessors(chain = true)
  public class Dept implements Serializable {
  ```

  

- log

```java
@CommonsLog  
Creates private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(LogExample.class);  
@Log  
Creates private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());  
@Log4j  
Creates private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LogExample.class);  
@Log4j2  
Creates private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogExample.class);  
@Slf4j  
Creates private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class);  
@XSlf4j  
Creates private static final org.slf4j.ext.XLogger log = org.slf4j.ext.XLoggerFactory.getXLogger(LogExample.class);  
```