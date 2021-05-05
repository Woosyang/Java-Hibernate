package com.Utils;
import java.lang.reflect.*;

/**
 * Encapsulate the common methods of the Reflection
 * @author Woo
 *
 */
public class ReflectionUtils {
	/**
	 * use the reflection to call the getter method corresponding to the designated 
	 * field of the targeted object
	 * @param fieldName, name of the designated field of the object
	 * @param obj, targeted object
	 * @return result of the getter method, data type is Object
	 */
	@SuppressWarnings("all")
	public static Object invokeGet(String fieldName, Object obj) {
		Class c = obj.getClass();
		try {
			Method m = c.getMethod("get" + fieldName, null);
			Object value = m.invoke(obj, null);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void invokeSet(Object obj, String columnName, Object columnValue) {
		if (columnValue != null) {
			try {
				Method m = obj.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName), columnValue.getClass());
				m.invoke(obj, columnValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
