package cn.web.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.web.model.Product;

// 主要演示了Spring管理Bean的基本语法
public class SpringDemo {

	public static void main(String[] args) {
		// 1: 加载Spring的配置文件
		ApplicationContext context = new ClassPathXmlApplicationContext("cn/web/demo/spring-bean.xml");
		System.out.println("spring配置文件加载成功...........");
		// 2: 根据配置文件,通过id获取要创建的Bean对象
		Product product = context.getBean("product", Product.class);
		Product product2 = context.getBean("product", Product.class);
		System.out.println(product);
		System.out.println(product2);
	}

}
