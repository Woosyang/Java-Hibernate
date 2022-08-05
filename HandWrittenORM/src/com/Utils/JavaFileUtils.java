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
	 * eg: varchar username -> private String username and the setter and getter method of the username
	 * @param column, column name in the table
	 * @param converter,  type conversion (database -> java)
	 * @return source code of the java field and setter and getter method
	 */
	public static JavaFieldGetSet createFieldGetSetSrc(ColumnInformation column, 
											  		   TypeConverter converter) {
		
		JavaFieldGetSet jfgs = new JavaFieldGetSet();
		String javaFieldType = converter.databaseType2JavaType(column.getDataType());
		// assume all of the fields are private
		jfgs.setFieldInformation("\tprivate " + javaFieldType + " " + column.getName() + ";\n");
		
		// public String getUsername() {return username;}
		// generate the source code of the getter method
		StringBuilder getSrc = new StringBuilder();
		getSrc.append("\tpublic " + javaFieldType + " get" + 
					  StringUtils.firstChar2UpperCase(column.getName()) + "()" + " {\n");
		getSrc.append("\t\treturn " + column.getName() + ";\n");
		getSrc.append("\t}\n");
		jfgs.setGetInformation(getSrc.toString());
		
		// public void setUsername(String username) {this.username = username;}
		// generate the source code of the setter method
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
		// use the method above
		List<JavaFieldGetSet> javaInfos = new ArrayList<>();
		//                  set of the ColumnInformation
		for (ColumnInformation c : columns.values()) {
			javaInfos.add(createFieldGetSetSrc(c, converter));
		}
		// generate package statement
		Src.append("package " + DBManager.getConfiguration().getPoPackage() + ";\n");
		
		// generate import statement, assume we only use java.util and java.sql
		Src.append("import java.sql.*;\n");
		Src.append("import java.util.*;\n\n");
		
		// generate declaration the class statement
		Src.append("public class " + StringUtils.firstChar2UpperCase(tableInformation.getTableName()) + " {\n\n");
		
		// generate field list, eg: private int name;....
		for (JavaFieldGetSet field : javaInfos) {
			Src.append(field.getFieldInformation());
		}
		Src.append("\n\n");
		
		// generate get method list, eg: getName()
		for (JavaFieldGetSet get : javaInfos) {
			Src.append(get.getGetInformation());
		}
		
		// generate set method list, eg: setName()
		for (JavaFieldGetSet set : javaInfos) {
			Src.append(set.getSetInformation());
		}
		
		// generate the default constructor, eg: public Emp() {}
		// ignore other constructors
		Src.append("\tpublic " + StringUtils.firstChar2UpperCase(tableInformation.getTableName()) + "()" + " {}\n");
		
		// generate the end syntax
		Src.append("}\n");
		return Src.toString();
	}
	
	public static void createJavaPoFile(TableInformation tableInformation, TypeConverter converter) {
		String Src = createJavaSrc(tableInformation, converter);
		// use I/O Stream to write a java file
		BufferedWriter Bw = null;
		// in this case the path is just src
		String srcPath = DBManager.getConfiguration().getSrcPath() + "/";
		String packagePath = DBManager.getConfiguration().getPoPackage().replace(".", "/");
		File Dir = new File(srcPath + packagePath); // directory
		// System.out.println(Dir.getAbsolutePath() + "------------");
		// if the directory doesn't exist, generate one for user
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
	
	public static void main(String[] args) {
		/*
		// test the createFieldGetSetSrc()
		ColumnInformation ci = new ColumnInformation("id", "int", 0);
		JavaFieldGetSet f = createFieldGetSetSrc(ci, new MySqlConverter());
		System.out.println(f);
		*/
		
		
		Map<String, TableInformation> Map = TableContext.tables;
		
		// create all the java classes according to the table
		for (TableInformation t : Map.values()) {
			createJavaPoFile(t, new MySqlConverter());
		}
		/*
		TableInformation t = Map.get("employee");
		// System.out.println(createJavaSrc(t, new MySqlConverter()));
		createJavaPoFile(t, new MySqlConverter());
		*/
	}
}
