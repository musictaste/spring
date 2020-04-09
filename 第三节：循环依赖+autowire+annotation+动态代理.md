# 循环依赖的bean 

word笔记文档：《Spring03 Spring IoC.docx》

==**参考工程：spring02**==

![image](https://raw.githubusercontent.com/musictaste/spring/master/image/203.png)

- 构造器注入循环依赖

当循环依赖的bean都是通过构造器注入依赖的时候，==无论这些bean是singleton还是prototype，在获取bean的时候都会失败==。

- 通过属性注入
  - ==循环依赖的bean都是singleton 成功==
  - ==循环依赖的bean都是prototype 失败==
  - ==同时有singleton和prototype 当先获取的那个bean是singleton时，就会成功，否则失败==
  - 当Spring容器在创建A时，会发现其引用了B，从而会先去创建B。同样的，创建B时，会先去创建C，而创建C时，又先去创建A。最后A、B、C之间互相等待，谁都没法创建成功

分析：
    
prototype,会生成好多循环引用，造成引用无法被回收；==为了避免循环引用，spring容器会检测循环引用==


单例singleTon：单例模式的对象，在spring中直接实例化；因为只有一个，spring允许

原型prototype：prototype会检查引用，如果有引用对象没有被实例化，暂停实例化，去实例化引用的对象

==singleton和prototype同时存在：单例对象的引用对象也变成单例模式的==
    
==抛出异常：Requested bean is currently in creation: Is there an unresolvable circular reference?==
  


```
bean配置
<!--<bean id="person" class="com.limiao.spring.Person" scope="singleton">
        <constructor-arg name="car" ref="car"></constructor-arg>
</bean>

<bean id="car" class="com.limiao.spring.Car" scope="singleton">
        <constructor-arg name="bmw" ref="bmw"></constructor-arg>
</bean>

<bean id="bmw" class="com.limiao.spring.BMW" scope="singleton">
        <constructor-arg name="person" ref="person"></constructor-arg>
</bean>-->

<bean id="person" class="com.limiao.spring.Person" scope="singleton">
        <property name="car" ref="car"></property>
</bean>

<bean id="car" class="com.limiao.spring.Car" scope="prototype">
        <property name="bmw" ref="bmw"></property>
</bean>

<bean id="bmw" class="com.limiao.spring.BMW" scope="prototype">
        <property name="person" ref="person"></property>
</bean>

测试一：
public static void main(String[] args) {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	Person p1 = (Person) ctx.getBean("person");
	Person p2 = (Person) ctx.getBean("person");

	System.out.println(p1==p2);
	System.out.println(p1.getCar()==p2.getCar());
	System.out.println(p1.getCar().getBmw()==p2.getCar().getBmw());
}
运行结果：
true
true
true

测试二：
public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	
		Person p1 = ctx.getBean("person",Person.class);
		Person p2 = ctx.getBean("person",Person.class);

		Car car1 = ctx.getBean("car",Car.class);
		Car car2 = ctx.getBean("car",Car.class);

		BMW bmw1 = ctx.getBean("bmw",BMW.class);
		BMW bmw2 = ctx.getBean("bmw",BMW.class);

		System.out.println(p1==p2);
		System.out.println(car1==car2);
		System.out.println(bmw1==bmw2);

	}
运行结果：
true
false
false	
```

#### depends-on 提前初始化

==对象A 对 对象B不是强引用，是弱引用，采用depends-on==

对象A 对 对象B是强引用，采用ref


```
public class A {

    private C c; //强引用

    public void weakRefence(){
        new B().getName(); //弱引用
    }
}

```

#### lazy-init ==在容器启动后，bean被使用到的时候才加载==

#### 空值注入

- Value标签

==标识空字符串 “”==

```
<property name="name"><value></value></property>
```

- Null标签

==标识Null==

```
<property name="name"><null></null></property>
```

# autowire自动注入

使用自动注入需要在配置文件中bean上添加**autowire**

```xml
<bean id="person" class="com.msb.Person" autowire="byName">
</bean>
<bean id="pet" class="com.msb.Pet">
 	<property name="name" value="kele"></property>
</bean>

```


**实体**

```java
public class Person {

	private String name;
	private Pet pet;
}
public class Pet {

	private String name;
}

```



可选两种类型

#### byName

byName方式自动注入：==要求注入的bean的id必须和被注入的bean对象的属性名一致==

#### byType

byType方式自动注入==：要求注入的bean的对象类型与被注入的bean对象类型一致，并且在配置文件中的Bean相同类型必须唯一==

==如果存在多个，会抛异常==：

No qualifying bean of type 'com.msb.Pet' available: expected single matching bean but found 2: pet,pet2

#### 全局自动注入

==在首行Beans标签下添加default-autowire属性。==

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans           http://www.springframework.org/schema/beans/spring-beans.xsd"
	
	default-autowire="byType"
	>

```



# annotation注解注入

==**参考工程：spring04**==

使用注解需要导入AOP包

==在配置文件中添加Context约束==

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	
	xmlns:context="http://www.springframework.org/schema/context"
	
	xsi:schemaLocation="
	http://www.springframework.org/schema/beans           http://www.springframework.org/schema/beans/spring-beans.xsd
	
	http://www.springframework.org/schema/context			http://www.springframework.org/schema/context/spring-context.xsd
	"
	>

```



### **<context:component-scan>**


```xml
<context:component-scan base-package="com.msb"></context:component-scan>
```

**component-scan**可以自动扫描包内容，并注册Bean到Spring容器

### @Component

在需要注册到容器的类上添加@Component标签，标识这个类由Spring容器接管

#### 约定大于配置

在一个类上添加@Component默认会使用首字母小写的类名作为ID注册到Spring容器。

如果需要手动指定Bean Id可以使用**@Component("p")**

### 同属@Component的额外三个注解

==@Controller @Service @Repository==

这三个注意在MVC开发中会经常用到，除了注解名字和Component不一样之外，其余功能都一样。

==Spring额外提供这三个注解的目的主要是为了区分MVC中每个类的区别。==

## @Scope

**使用注解注册Bean 默认的作用域还是singleton，可以使用@Scope("prototype")改变对象作用域**

## @Value

在使用注解给对象注入值的时候，不再需要Get/Set方法

### 基础类型

使用@Value注解

```java
	@Value("小明")
	private String name;

```


### 对象引用

```java
	@Autowired
	private Pet MyPet;
```

使用@Autowired注解

==默认是ByType的，如果需要ByName需要配合@Qualifier注解==


```
@Autowired()
@Qualifier("p2")
private Pet MyPet;

```

# 面向切面编码，代码增强

==AOP(Aspect Oriented Programming)面向切面编程。==

==面向切面，是与OOP(Object Oriented Programming)面向对象编程并列的编程思想。==

Spring支持两种方法,那么我们在使用spring进行动态代理时究竟使用的哪一种方法呢？

==spring优先支持实现接口的方式,如果没有接口则使用cglib方式==


### 代理

**通过代理可以隐藏目标类的具体实现;在不修改目标类代码的情况下能够对其功能进行增强。**

- ==委托类和代理类有相同的接口或者共同的父类==
- 代理类为委托类负责处理消息，并将消息转发给委托类
- 委托类和代理类对象通常存在关联关系
- 一个代理类对象与一个委托类对象关联
- 代理类本身并不是真正的实现者！而是通过调用委托类的方法来实现功能！


### 静态代理

参考工程：spring05

==使用硬编码的方式增强原有方法==


静态代理可以在目标类方法的：
1.	==面向接口==
2.	==方法内容不可修改，方法返回内容是可以修改的==
3.	执行前后做修饰
4.	==private的方法不能增强==
5.	是一个具体的类，还需要封装具体的方法


优点：可以做到不对目标对象进行修改的前提下，对目标对象进行功能的扩展和拦截。

==缺点：因为代理对象，需要实现与目标对象一样的接口，会导致代理类十分繁多，不易维护，同时一旦接口增加方法，则目标对象和代理类都需要维护==。







```
//抽象接口
public interface Human {
    public void eat();
}

//Girl -> 目标对象 -> 被包装/增强的对象
public class Girl implements Human {
    public void eat() {
        System.out.println("eat....");
    }
}

//代理类
//ProxyGirl 代理对象，包含对原对象方法的增强，通过构造方法传入原对象，
//并实现和原对象相同的接口，实现接口方法，便可以利用Java多态的特性，
//通过访问代理方法同时能够调起原对象的实现，并对其增强。
public class GirlProxy implements Human {
    private Human human;

    public GirlProxy() {
        super();
    }

    public GirlProxy(Human human) {
        super();
        this.human = human;
    }

    public void eat() {
        System.out.println("before....");
        human.eat();
        System.out.println("after....");
    }
}

//测试类
public class GetBean {
	public static void main(String[] args) {
		Girl girl = new Girl();
		GirlProxy girlProxy = new GirlProxy(girl);
		girlProxy.eat();
	}
}
```



### 动态代理

动态代理是指动态的在内存中构建代理对象（需要我们制定要代理的目标对象实现的接口类型），即利用JDK的API生成指定接口的对象，也称之为JDK代理或者接口代理。

- ==目标对象实现了接口 -> JDK动态代理==
- ==目标对象没有实现口 -> CGLib==

#### JDK动态代理，通过反射

参考工程spring06

```java

//抽象接口
public interface Human {
    public void eat();

    public void dance();
}

//具体类
public class Girl implements Human {
    public void eat() {
        System.out.println("eat....");
    }

    public void dance() {
        System.out.println("dance----");
    }
}

//JDK动态代理
public class GetBean {
	public static void main(String[] args) {
		final Girl girl = new Girl();
		Human prxyGirl = (Human) Proxy.newProxyInstance(Girl.class.getClassLoader(), Girl.class.getInterfaces(), new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println(method.getName()+"...方法被执行");

				if(method.getName().equals("eat")){
					System.out.println("eat before...");
					Object invoke = method.invoke(girl,args);
					System.out.println("eat after....");
					return  invoke;
				}

				System.out.println("dance before...");
				Object invoke = method.invoke(girl,args);
				System.out.println("dance after....");
				return  invoke;
			}
		});

		prxyGirl.eat();
		System.out.println("=================");
		prxyGirl.dance();
	}
}

```


#### CGLIB动态代理

![image](https://raw.githubusercontent.com/musictaste/spring/master/image/301.png)

参考工程spring07


```java

//委托类：
public class Girl {
    public void eat() {
        System.out.println("eat....");
    }

    public void dance() {
        System.out.println("dance----");
    }
}

//代理工厂
public class CGlibFactory implements MethodInterceptor {
    private Object target;

    public CGlibFactory(Object target) {
        super();
        this.target = target;
    }

    public CGlibFactory() {
        super();
    }

    public Object createProxy(){
        //增强器
        Enhancer enhancer = new Enhancer();
        //创建子类，作为代理类
        enhancer.setSuperclass(Girl.class);
        //设置回调类
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before....");
        method.invoke(target,objects);
        System.out.println("after....");
        return null;
    }
}

//测试类
public class GetBean {
	public static void main(String[] args) {
		final Girl girl = new Girl();
		Girl proxyGirl  = (Girl) new CGlibFactory(girl).createProxy();
		proxyGirl.eat();
		System.out.println("=============");
		proxyGirl.dance();
	}
}

```


#### 底层ASM