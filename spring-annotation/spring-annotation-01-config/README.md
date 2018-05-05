# Spring 注解驱动开发

## 给容器中注册组件
1. 包扫描+组件注解（@Controller/@Service/@Repository/@Component）[自己写的类]
2. @Bean[导入的第三方包里面的组件]
3. @Import[快速给容器中导入一个组件]