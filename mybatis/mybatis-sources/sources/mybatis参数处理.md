# mybatis 参数处理
## 1、单个参数: Mybatis不会做特殊处理
- #{参数名}:	取出参数值
	
## 2、多个参数: Mybatis会做特殊处理
- 多个参数会被封装成一个map
	key: 	param1...param，或者参数的索引也可以
	value:	传入的参数值
	#{}就是从map中获取指定的key的值；
- 异常: org.apache.ibatis.binding.BindingException: Parameter 'id' not found. Available parameters are [arg1, arg0, param1, param2]
- 操作: 
		方法: Employee getEmpByIdAndLastName(Integer id, String lastName);
		取值: #{id}, #{lastName}
			
## 3、命名参数: 明确指定封装参数时map的key; @Param("id")
- 多个参数会被封装成一个map，
	key:	使用@Param注解指定的值
	value:	参数值
	#{指定的key}取出对应的参数值

## 4、POJO:	
- 如果多个参数正好是业务逻辑的数据模型，就可以直接传入pojo；
	#{属性名}: 取出传入的pojo的属性值
	
## 5、Map:
- 如果多个参数不是业务模型中的数据，没有对应的pojo，不经常使用，为了方便，我们也可以传入map
	#{key}取出map中对应的值
	
## 6、TO:
- 如果多个参数不是业务模型中的数据，但是经常要使用，推荐来编写一个TO(Transfer Object)数据传输对象
	page {
		int index;
		int size;
	}

====================================================================================================

## 7、取值技巧
public Employee getEmp(@Param("id) Integer id, String lastName);
	`取值： id ==> #{id/param1}		lastName ==> #{param2}
	
public Employee getEmp(Integer id, @Param("e) Employee employee);
	`取值: id ==> #{param1}			lastName ==> #{param2.lastName/e}
	
## 8、特别注意: 如果是Collection(List, Set)类型或者是数组，也会特殊处理，也是把传入的List或者数组封装在Map中。
	key: Collection(collection),	如果是List还可以使用这个key(list)
public Employee getEmp(List<Integer> ids);
	`取值: 取出第一个id的值 ==> #{list[0]}
	
====================================================================================================

## 9、结合源码，Mybatis怎么处理参数
	Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);
- ParamNameResolver解析参数封装map的;
	names：构造方法已经确定好了
		 (java.util.Collections$UnmodifiableSortedMap<K,V>) {0=id, 1=lastName}
	确定流程：
	1. 获取每个表了Param注解的@Param值：id, lastName
	2. 每次解析一个参数给map中保存信息：（key:参数索引，value:name的值）
		name的值：
			标注了@Param注解，注解的值
			没有标注：
				1. 全局配置：useActualParamName(工程必须采用Java 8编译，并且加上-parameters选项)：name=参数名
				2. name=map.size();	相当于当前元素的索引	{0=id, 1=lastName, 2=2}
		

	args[1, "jerry"]
	  public Object getNamedParams(Object[] args) {
	    final int paramCount = names.size();
	    // 1、 参数为null直接返回
	    if (args == null || paramCount == 0) {
	      return null;
	      
	      // 2、如果只有一个元素，并且没有@Param注解：args[0]:单个参数直接返回
	    } else if (!hasParamAnnotation && paramCount == 1) {
	      return args[names.firstKey()];
	      // 3、多个元素或者有@Param注解
	    } else {
	      final Map<String, Object> param = new ParamMap<Object>();
	      int i = 0;
	      // 4、遍历names集合：{0=id, 1=lastName}
	      for (Map.Entry<Integer, String> entry : names.entrySet()) {
	      	// names集合的value作为key；	names集合的key又作为取值的参考args[0]: args[1, "jerry"]
	      	// {id:agrs[0], lastName=args[1]}
	      	// {id:1, lastName:"jerry"}
	        param.put(entry.getValue(), args[entry.getKey()]);
	        // add generic param names (param1, param2, ...)
	        
	        // 额外的将每一参数也保存到map中，使用新的key：param1...paramN
	        // 效果：有@Param注解可以#{指定的key}，或者#{param1}
	        final String genericParamName = GENERIC_NAME_PREFIX + String.valueOf(i + 1);
	        // ensure not to overwrite parameter named with @Param
	        if (!names.containsValue(genericParamName)) {
	          param.put(genericParamName, args[entry.getKey()]);
	        }
	        i++;
	      }
	      return param;
	    }
	  }
	  

## 10、参数值的获取

### #{}:	可以获取map中的值或者pojo对象属性的值；

### ${}:	可以获取map中的值或者pojo对象属性的值；
	SELECT * FROM tbl_employee WHERE id = ${id} AND last_name = #{lastName}
	Preparing: SELECT * FROM tbl_employee WHERE id = 1 AND last_name = ?
- 区别：
	#{}:	是以预编译的形式，将参数设置到sql语句中，ParparedStatement，防止sql注入；
	${}:	取出的值直接拼装在sql语句中；会有安全问题；
	大多情况下，我们取参数的值都应该使用#{}；
	
	生jdbc不支持占位符的地方就可以使用${}进行取值
	比如分表、排序等等不支持占位符的语句：
		SELECT * FROM 2017_salary WHERE XXX;
		SELECT * FROM ${}_salary WHERE XXX;
		排序：SELECT * FROM tbl_employee ORDER BY ${f_name} ${order}
		
	#{}: 更丰富的用法
		规定参数的一些规则：
			javaType、jdbcType、mode(存储过程)、numericScale、
			resultMap、typeHandler、jdbcTypeName、expression(未来准备支持的功能);
			
		jdbcType通常需要在某种特定的条件下被设置：
			在我们数据为null的时候，有些数据可能不能识别mybatis对null的默认竖立。比如Oracle(报错);
			jdbcType OTHER：无效的类型；因为mybatis对所有的null都映射的是原生Jdbc的OTHER类型。
			
		由于全局配置中：jdbcTypeForNull=OTHER；oracle不支持；
		1、#{email, jdbcType = OTHER};
		2、jdbcTypeForNull=NULL
			