package com.javaBase.reflect.classType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;

import com.javaBase.reflect.filedType.Test;
import com.javaBase.reflect.model.User;

public class ReflectTypeClass {
	private static Map<String, String> map = new ReflectTypeClass().getColumnNameMap(User.class);
	public static void main(String[] args) throws Exception {
		for (Entry<String, String> entry : map.entrySet()) {  
			  
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		}  
		try {
			//运用反射的原理创建对象
			Class classType=Class.forName("com.sawshaw.Java.reflect.model.User");
			//获取所有的方法
			Method[] methods=classType.getDeclaredMethods();
			for(int i=0;i<methods.length;i++){
				System.out.println("METHOD:"+methods[i].toString());
			}
			//
			User user=new User();
			new ReflectTypeClass().getColumnNameMap(User.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public  <T> void copy(Class<T> classType) throws Exception{
		//动态地获取类
		//Class classType=object.getClass();
		//获取类名
		System.out.println("CLASS："+classType.getName());
		//通过默认构造方法创建一个新的对象
		Object objectCopy=classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
		//获取对象的所有属性
		//classType=User.class;
		Field[] fileds=User.class.getDeclaredFields();
		
		for(Field filed:fileds){
			System.out.println("FILED:"+filed.getName());
			System.out.println(filed.isAnnotationPresent(Column.class));
			if(filed.isAnnotationPresent(Column.class)){
    			Column col = filed.getAnnotation(Column.class);
    			System.out.println("COLUMN:"+col.name());
			}
		}
	}
	 private <T> Map<String, String> getColumnNameMap(Class<T> clazz){
	    	Map<String, String> map = new HashMap<String, String>();
	    	Field[] fields = clazz.getDeclaredFields();
	    	StringBuilder sb = new StringBuilder();
	    	for(Field f : fields){
	    		if(f.isAnnotationPresent(Column.class)){
	    			Column col = f.getAnnotation(Column.class);
	    			if(col != null){
	    				System.out.println("FILED0:"+col.name());
	    				map.put(col.name(), f.getName());
	    			}
	    		}else{
					sb.setLength(0);
					sb.append("get").append(f.getName());
					sb.setCharAt(3, this.upperOrLower(sb.charAt(3)));
					try {
						Method getter = clazz.getMethod(sb.toString());
						Column col = getter.getAnnotation(Column.class);
		    			if(col != null){
		    				System.out.println("FILED:"+col.name());
		    				map.put(col.name(), f.getName());
		    			}
					} catch (Exception e) {
					}
	    		}
	    	}
	    	return map;
	    }
	 private char upperOrLower(char c){
			if(c <= 90 && c >= 65){
	            c += 32;
	        } else if(c <= 122 && c >= 97){
	            c -= 32;
	        }
			return c;
		}

}
