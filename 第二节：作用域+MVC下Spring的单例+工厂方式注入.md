### word笔记文档：《Spring02 Spring IoC.docx》
### 基于XML的DI

参考工程：spring01

- bean的定义与注册
  - alias别名
<alias>或者bean中有多个name作为别名


```
<bean id="person" name="human,people" class="com.limiao.spring.Person" lazy-init="false">

<alias name="person" alias="people2"></alias>
```

- 构造器注入--需要有对应的构造方法


```
<bean id="person" class="com.limiao.spring.Person" scope="singleton">
        <constructor-arg name="car" ref="car"></constructor-arg>
</bean>
```
 

- 属性注入
  - 使用p-namespace
 
```
添加一个namespace
xmlns:p=http://www.springframework.org/schema/p
    
使用p
<bean id="person" class="com.msb.Person"  p:age="21" p:name = "zhangsan" p:food-ref="food">
```
- 
  - 使用c- namespace

```
构造器注入
<bean id="foo" class="x.y.Foo" c:bar-ref="bar" c:baz-ref="baz" c:email= "foo@bar.com"/>
```

-
  - 使用java.util.Properties


```
import java.util.Properties;

private Properties others;

第一种方式：
<property name="others">
        <value>
                test=true,
                address=www.baidu.com
        </value>
</property>

第二种方式：
<property name="adminEmails">
  <props>
      <prop key="administrator">administrator@example.org</prop>
      <prop key="support">support@example.org</prop>
      <prop key="development">development@example.org</prop>
  </props>
</property>

```

- 集合
  - list
  - set
  - map
  - array

```
<property name="list">
    <list>
            <value>a</value>
            <value>b</value>
            <value>c</value>
    </list>
</property>
<property name="map">
    <map>
            <entry key="1" value="a"></entry>
            <entry key="2" value="b"></entry>
            <entry key="3" value="c"></entry>
    </map>
</property>
```

#### 作用域

spring为bean提供了6种作用域，其中4种只有在web-aware的ApplicationContext种才有用。用户也可以创建自定义的作用域。

==singleton 、prototype 、websocket、request、session、application==

==问题1：spring对对象的生产，一共就两种==

1.	单例：singleton -> websocket、request、session、application -> 生命周期绑定了
2.	New出来的，prototype


#### MVC下Spring的单例

问题2：什么时候用new出来的？什么时候用单例的？

	POJO的创建使用prototype，也就是new出来的
	Spring在SSM框架中起到的作用是：帮我们创建单例对象
	
==面试题：MVC下Spirng为什么用单例？为了性能==

==面试题：单例在并发情况下有没有问题？==

	有，单例类中不能有状态数据，如果有，要非常小心使用
	单例类中的成员变量，在多线程的情况下，多个线程操作成员变量，造成问题

==Spring在MVC模式中，controller是单例的，service层是单例的，dao层是单例的==

==Model中的POJO 是多例的==
![image](https://raw.githubusercontent.com/musictaste/spring/master/image/201.png)

==连接JDBC，connection是单例的，Connection的连接建立和关闭是在线程的ThreadLocal中进行的，这样达到了线程间隔离==

![image](https://raw.githubusercontent.com/musictaste/spring/master/image/202.png)

==想在一个singleton内多次调用短存活时间的bean（propotype、request、session等），希望调用的是不同的实例，那么就需要使用AOP proxy技术==


- 线程安全问题

业务对象并没有做线程的并发限制，因此不会出现各个线程之间的等待问题，或是死锁问题
MVC中的实体bean不是单例的（POJO不是单例的）


- 成员变量

在并发访问的时候这些成员变量将会是并发线程中的共享对象，也是影响线程安全的重要因素
引用类型的成员

其中引用类型的成员变量即我们在controller中注入的service，在service中注入的dao，这里将其定义为成员变量主

要是为了实例化进而调用里面的业务方法，在这些类中一般不会有全局变量，因此只要我们的业务方法不含有独立的

全局变量即使是被多线程共享，也是线程安全的。

Controller service dao 层中的业务类是多线程共享的，但是每个线程在处理数据的时候具体处理的数据是在每个线程中各自有一份。

- controller层

final类型 线程安全

==成员变量 有状态数据有线程安全问题==


---

### 工厂方式注入

==**参考工程：spring03**==

为满足更复杂的需求，Spring也提供了工厂方式来创建更加灵活的Bean。

**留意观察工厂类和实现类的创建次数**

#### 动态工厂


```java
抽象接口 Car
public interface Car {
     public String getName();
     public String getPrice();

}

实现类 Audi车
public class Audi implements Car {
    public String getName() {
        return "我是奥迪";
    }
    public String getPrice() {
        return "700000";
    }
}


汽车工厂类 CarFactory
public class CarFactory {
	public Car getCar(String name) throws Exception {
        if(name.equals("audi")){
            return new Audi();
        }else {
            throw  new Exception("暂时无法生产");
        }
    }
}

Bean配置
<bean id="carFactory" class="com.limiao.spring.CarFactory"></bean>
<bean id="car" class="com.limiao.spring.Car" factory-bean="carFactory" factory-method="getCar">
    <constructor-arg value="audi"/>
</bean>
```


####  静态工厂

```java

工厂类
public class CarFactory {
    public static Car getCar(String name) throws Exception {
        if(name.equals("audi")){
            return new Audi();
        }else {
            throw  new Exception("暂时无法生产");
        }
    }
}

Bean配置
<bean id="car" class="com.limiao.spring.CarFactory" factory-method="getCar">
    <constructor-arg value="audi"/>
</bean>

```




