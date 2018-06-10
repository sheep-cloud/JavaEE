# zuul-gateway 路由网关

## 1. zuul 展示
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqv4docxtgj30dd07ht8o.jpg)

## 2. pom.xml
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqv4e6ojnwj30h002o3yd.jpg)

## 3. 启动
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqv4epfycnj30l709iq30.jpg)

## 4. 访问方式
	http://路由网关地址:端口/服务名称/服务Controller地址
	http://localhost:9527/spring-cloud-dept/dept/get/1

## 5. 路由访问映射规则
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqv81v654yj30o003xmx1.jpg)

	修改后的访问方式：
	http://localhost:9527/colg/mydept/dept/get/1