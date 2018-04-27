#  相关技术
## 1、swagger2 
访问地址：http://localhost:8001/swagger-ui.html

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrccmjc8jj31gq0qat9r.jpg)

## 2、配置
### 1. pom.xml
```
	<properties>
		<swagger2.version>2.8.0</swagger2.version>
	</properties>

        <!-- swagger2 依赖-->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>${swagger2.version}</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>${swagger2.version}</version>
		</dependency>
```

### 2. SwaggerConfig
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrchlhny8j30rs0jndgb.jpg)
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrci6pkirj30yh0f9mxj.jpg)

## 3、Swagger2语法（精简版）
```
    /*
     * Swagger2 语法：
     * 作用在Model上：
     *  1. @ApiModel(description = "部门对象") ：    Model的描述
     *  2. @ApiModelProperty("主键")：                                 属性描述
     *  
     * 作用在Controller上：
     *  1. @Api(tags = {"部门管理"})：                                   Controller的类描述
     *  2. @ApiOperation("添加部门")：                                   Controller的方法描述
     *  3. @ApiImplicitParam(name = "id", value = "部门id", paramType = "Long", required = true)
     *      name：属性；value：属性描述；paramType：属性类型；required：是否必填
     */
```