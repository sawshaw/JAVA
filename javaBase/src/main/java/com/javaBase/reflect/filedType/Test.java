package com.javaBase.reflect.filedType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.javaBase.reflect.model.Student;

public class Test {
	private static Map<String, String> map = new Test().getColumnNameMap(Student.class);
	public static void main(String[] args) {
		for (Entry<String, String> entry : map.entrySet()) {  
			  
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		}  
		 String name=new Test().getTableName(Student.class);
		  System.out.println("Table Name:"+name);
		
	}
	private <T> String getTableName(Class<T> clazz){
		String tableName = "";
		//查询是否有Table注解
		Table table = clazz.getAnnotation(Table.class);
		if(table != null){
			tableName = table.name();
		}
		if(tableName==null|| "".equals(tableName)){
			Entity entity = clazz.getAnnotation(Entity.class);
			if(entity != null){
				tableName = entity.name();
			}
		}
		return tableName;
	}
	
    public <T> Map<String, String> getColumnNameMap(Class<T> clazz){
    	Map<String, String> map = new HashMap<String, String>();
    	Field[] fields = clazz.getDeclaredFields();
    	StringBuilder sb = new StringBuilder();
    	for(Field f : fields){
    		if(f.isAnnotationPresent(Column.class)){
    			Column col = f.getAnnotation(Column.class);
    			if(col != null){
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
