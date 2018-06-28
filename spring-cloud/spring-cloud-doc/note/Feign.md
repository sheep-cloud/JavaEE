# Feign 声明式服务调用客户端

## 1. pom.xml

```xml
		<!-- feign 依赖 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-feign</artifactId>
        </dependency>
```

## 2. 启动类

```java
/**
 * Spirng Boot 启动类
 * 
 * <pre>
 * '@EnableEurekaClient'：               启动后自动注册进eureka服务中
 * '@EnableFeignClients'：               开启 Feign 功能，rest 通信
 * </pre>
 * 
 * @author colg
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudConsumerDeptFeign80Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerDeptFeign80Application.class, args);
    }
}
```

## 3. 创建Feign的客户端接口定义

```java
/**
 * 部门Service - 客户端
 *
 * @author colg
 */
@FeignClient(value = "SPRING-CLOUD-DEPT")
@RequestMapping("/dept")
public interface DeptClientService {

    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    ResultBean add(Dept dept);

    /**
     * 根据id获取部门
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    ResultBean get(@PathVariable("id") Long id);

    /**
     * 获取所有部门
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    ResultBean list();
}
```

## 4. 消费者 Controller

```java
/**
 * 部门Controller 消费者
 *
 * @author colg
 */
@RestController
@RequestMapping("/consumer/dept")
public class ConsumerDeptController {

    @Autowired
    private DeptClientService deptClientService;

    @GetMapping("/get/{id}")
    public ResultBean get(@PathVariable Long id) {
        return deptClientService.get(id);
    }

    @GetMapping("/list")
    public ResultBean list() {
        return deptClientService.list();
    }

    @PostMapping("/add")
    public ResultBean add(Dept dept) {
        return deptClientService.add(dept);
    }
}
```

