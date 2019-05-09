package fr.pizzeria.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class StringUtils {

	public static void ToString(Object MyObject) {
		Field[] fields = MyObject.getClass().getFields();

		for(Field f : fields) {
			for(Annotation a : f.getAnnotations()) {
				if(a instanceof ToString) {
					if(((fr.pizzeria.utils.ToString) a).uppercase()) { 
						try{
							String value = ""+((String) f.get(MyObject)).toUpperCase();
							f.set(MyObject, value);
						} catch (Exception e) {
							
						}
					}
				}
			}
		}
	}
	//objet
	
	
	//
	
	//check if got tostring
	//update vlaue into uppercase
}
