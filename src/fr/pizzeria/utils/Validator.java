package fr.pizzeria.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import fr.pizzeria.exception.ValidateException;

public class Validator {

	public static void ToValidate(Object MyObject) throws ValidateException, IllegalArgumentException, IllegalAccessException {
		Field[] fields = MyObject.getClass().getFields();

		for(Field f : fields) {
			for(Annotation a : f.getAnnotations()) {
				if(a instanceof Rule) {
					double min = ((Rule) a).min();
					 if(Double.compare((double)f.get(MyObject),min) < 0) {
						 throw new ValidateException("you need a minimum value of "+min);
					 }
				}
			
			}
		}
	}
}
