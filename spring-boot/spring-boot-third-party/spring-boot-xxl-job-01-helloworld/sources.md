解压源码,按照maven格式将源码导入IDE, 使用maven进行编译即可，源码结构如下：
	
	xxl-job-admin：调度中心；作用：统一管理任务调度平台上调度任务，负责触发调度执行，并且提供任务管理平台。
	xxl-job-core：公共依赖
	xxl-job-executor：执行器Sample示例（选择合适的版本执行器，可直接使用，也可以参考其并将现有项目改造成执行器）
		作用：负责接收“调度中心”的调度并执行；可直接部署执行器，也可以将执行器集成到现有业务项目中。
	    ：xxl-job-executor-sample-spring：Spring版本，通过Spring容器管理执行器，比较通用，推荐这种方式；
	    ：xxl-job-executor-sample-springboot：Springboot版本，通过Springboot管理执行器；
	    ：xxl-job-executor-sample-jfinal：JFinal版本，通过JFinal管理执行器；
	    ：xxl-job-executor-sample-nutz：Nutz版本，通过Nutz管理执行器；
	    
- /doc :文档资料
- /db :“调度数据库”建表脚本
- /xxl-job-admin :调度中心，项目源码
- /xxl-job-core :公共Jar依赖
- /xxl-job-executor-samples :执行器，Sample示例项目（大家可以在该项目上进行开发，也可以将现有项目改造生成执行器项目）