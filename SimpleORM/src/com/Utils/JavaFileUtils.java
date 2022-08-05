package com.Utils;
import com.Core.*;
import com.Bean.*;
import java.util.*;
import java.io.*;

/**
 * Encapsulate the common methods of generating a java file(source code).
 * @author Woo
 *
 */
public class JavaFileUtils {
	
	/**
	 * generate the field information of java according to the column information
	 * eg: varchar username to private String username and the setter and getter method of the username
	 * @param column, column name in the table
	 * @param converter,  type conversion (database to java)
	 * @return source code of the java field and setter and getter method
	 */
	public static JavaFieldGetSet createFieldGetSetSrc(ColumnInformation column, 
											  		   TypeConverter converter) {
		
		JavaFieldGetSet jfgs = new JavaFieldGetSet();
		String javaFieldType = converter.databaseType2JavaType(column.getDataType());
		jfgs.setFieldInformation("\tprivate " + javaFieldType + " " + column.getName() + ";\n");
		
		StringBuilder getSrc = new StringBuilder();
		getSrc.append("\tpublic " + javaFieldType + " get" + 
					  StringUtils.firstChar2UpperCase(column.getName()) + "()" + " {\n");
		getSrc.append("\t\treturn " + column.getName() + ";\n");
		getSrc.append("\t}\n");
		jfgs.setGetInformation(getSrc.toString());
		
		StringBuilder setSrc = new StringBuilder();
		setSrc.append("\tpublic void set" + StringUtils.firstChar2UpperCase(column.getName()) + "(");
		setSrc.append(javaFieldType + " " + column.getName() + ") {\n");
		setSrc.append("\t\tthis." + column.getName() + " = " + column.getName() + ";\n");
		setSrc.append("\t}\n");
		jfgs.setSetInformation(setSrc.toString());
		return jfgs;
	} 
	
	/**
	 * generate the source code of the java class according to the information of the table
	 * @param tableInformation, the information of the table
	 * @param converter, type conversion
	 * @return the source code of the java class
	 */
	public static String createJavaSrc(TableInformation tableInformation, TypeConverter converter) {
		StringBuilder Src = new StringBuilder();
		Map<String, ColumnInformation> columns = tableInformation.getColumns();
		List<JavaFieldGetSet> javaInfos = new ArrayList<>();
		for (ColumnInformation c : columns.values()) {
			javaInfos.add(createFieldGetSetSrc(c, converter));
		}
		Src.append("package " + DBManager.getConfiguration().getPoPackage() + ";\n");
		Src.append("import java.sql.*;\n");
		Src.append("import java.util.*;\n\n");
		Src.append("public class " + StringUtils.firstChar2UpperCase(tableInformation.getTableName()) + " {\n\n");
		for (JavaFieldGetSet field : javaInfos) {
			Src.append(field.getFieldInformation());
		}
		Src.append("\n\n");
		for (JavaFieldGetSet get : javaInfos) {
			Src.append(get.getGetInformation());
		}
		for (JavaFieldGetSet set : javaInfos) {
			Src.append(set.getSetInformation());
		}
		Src.append("\tpublic " + StringUtils.firstChar2UpperCase(tableInformation.getTableName()) + "()" + " {}\n");
		Src.append("}\n");
		return Src.toString();
	}
	
	public static void createJavaPoFile(TableInformation tableInformation, TypeConverter converter) {
		String Src = createJavaSrc(tableInformation, converter);
		BufferedWriter Bw = null;
		String srcPath = DBManager.getConfiguration().getSrcPath() + "/";
		String packagePath = DBManager.getConfiguration().getPoPackage().replace(".", "/");
		File Dir = new File(srcPath + packagePath); // directory
		if (! Dir.exists()) {
			Dir.mkdirs();
		}
		try {
			Bw = new BufferedWriter(new FileWriter(Dir.getAbsolutePath() + "/" + StringUtils.firstChar2UpperCase(tableInformation.getTableName()) + ".java"));
			Bw.write(Src);
			System.out.print("corresponding Table: " + tableInformation.getTableName());
			System.out.print(", java file generated: " + StringUtils.firstChar2UpperCase(tableInformation.getTableName()) + ".java");
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (Bw != null) {
				try {
					Bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
