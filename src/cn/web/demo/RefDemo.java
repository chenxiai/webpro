package cn.web.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.web.model.Product;

// 演示反射的基本实用
public class RefDemo {
	public static void main(String[] args) throws Exception {
		// 采用正常的语法进行Product对象赋值
		Product product = new Product();
		product.setName("华为荣耀");
		System.out.println(product.getName());
		System.out.println("采用反射的方式进行Product对象赋值");
		// 获取Product.class文件的三种方式
		// 1: 直接通过类文件获取
		Class clazz = Product.class;
		// 2: 通过forName(类全名)来说去Class文件
		Class clazz2 = Class.forName("cn.web.model.Product");
		// 3: 通过对象获取此对象相应的类型
		Class clazz3 = product.getClass();
		// 结论：三种方式获取都是唯一的Product.class文件
		if (clazz == clazz2 && clazz2 == clazz3) {
			System.out.println("获取都是唯一的Product.class文件");
		}
		// 2: 通过反射的方式创建Product.class类型的对象
		Product product2 = (Product) clazz.newInstance();
		// 3: 通过反射获取Product.class文件中属性、方法
		// int[] arr = { 1, 2, 3, 4 };
		// 此方式只能获取当前类和父类的public方法
		System.out.println("clazz.getMethods()..............");
		Method[] methods = clazz.getMethods();
		for (Method temp : methods) {
			System.out.println(temp);
		}
		System.out.println("clazz.getDeclaredMethods()..............");
		// 此方式仅获取当前类的所有方法(对象),包括private私有
		methods = clazz.getDeclaredMethods();
		for (Method temp : methods) {
			System.out.println(temp);
		}
		// 根据方法名获取只定的某个方法(Method类型的对象)
		Method setName = clazz.getDeclaredMethod("setName", String.class);
		// product.setName("华为荣耀"); // 采用反射来实现
		setName.invoke(product2, "小米手机");
		Method getName = clazz.getDeclaredMethod("getName");
		System.out.println(getName.invoke(product2));
		System.out.println(getName.invoke(product));
		// 通过反射直接获取私有属性,并且给它赋值
		System.out.println("此方式仅获取当前类的所有属性,包括private私有");
		for (Field temp : clazz.getDeclaredFields()) {
			System.out.println(temp);
		}
		System.out.println("此方式只能获取当前类和父类的public属性");
		for (Field temp : clazz.getFields()) {
			System.out.println(temp);
		}
		// 通过反射获取私有属性,并且给它赋值
		Field field = clazz.getDeclaredField("name");
		// 取消Java的语法检查
		field.setAccessible(true);
		// 对象.属性=值
		field.set(product2, "苹果手机");
		System.out.println(field.get(product2));
	}
}
