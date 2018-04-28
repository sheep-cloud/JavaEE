# eclipse中使用Lombok
## 1、Lombok安装
1. 下载lombok.jar包 https://projectlombok.org/download.html

2. 运行Lombok.jar: `java -jar E:\maven\repository\org\projectlombok\lombok\1.16.20\lombok-1.16.20.jar`	lombok.jar所在路径

	数秒后将弹出一框，以确认eclipse的安装路径

3. 确认完eclipse的安装路径后，点击install/update按钮，即可安装完成

4. 安装完成之后，请确认eclipse安装路径下是否多了一个lombok.jar包，并且其

	配置文件eclipse.ini中是否 添加了如下内容: 
    `-javaagent:D:\Spring Tool Suite\spring-tool-suite-3.7.3.RELEASE\lombok.jar`
    
	如果上面的答案均为true，那么恭喜你已经安装成功，否则将缺少的部分添加到相应的位置即可

5. 重启eclipse或spring-tool-suite

## 2、Lombok 基本用法
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqs7fgrptvj30fh0asmx7.jpg)