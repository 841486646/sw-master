package com.swtec.sw.persist.mybatis.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.plugins.SerializablePlugin;

public class PageExtPlugin extends SerializablePlugin {
	private FullyQualifiedJavaType serializable;
    public PageExtPlugin() {
        serializable = new FullyQualifiedJavaType("java.io.Serializable"); //$NON-NLS-1$
    }

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// add field, getter, setter for limit clause
		addPage(topLevelClass, introspectedTable, "pageBegin");
		addPage(topLevelClass, introspectedTable, "pageSize");
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		java.lang.reflect.Field field;
		try {
			field = GeneratedXmlFile.class.getDeclaredField("isMergeable");
			if (!field.isAccessible()) {
				field.setAccessible(Boolean.TRUE);
			}
			field.set(sqlMap, false);
		} catch (NoSuchFieldException e) {

		} catch (SecurityException e) {

		} catch (IllegalArgumentException e) {

		} catch (IllegalAccessException e) {
		}

		return super.sqlMapGenerated(sqlMap, introspectedTable);
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		XmlElement parentElement = document.getRootElement();

		// 此处生成加上Ext
		List<Attribute> attributes = parentElement.getAttributes();
		for (int i = 0; attributes != null && i < attributes.size(); i++) {
			if (attributes.get(i).getName().equals("namespace")) {
				String value = attributes.get(i).getValue();
				attributes.remove(i);
				attributes.add(new Attribute("namespace", value + "Ext"));
				break;
			}
		}

		// 产生分页语句前半部分
		XmlElement paginationPrefixElement = new XmlElement("sql");
		paginationPrefixElement.addAttribute(new Attribute("id", "Pageable_Prefix"));
		XmlElement pageStart = new XmlElement("if");
		pageStart.addAttribute(new Attribute("test", "pageBegin != null and pageSize !=null"));
		pageStart.addElement(new TextElement("select * from ( "));
		paginationPrefixElement.addElement(pageStart);
		parentElement.addElement(paginationPrefixElement);

		// 产生分页语句后半部分
		XmlElement paginationSuffixElement = new XmlElement("sql");
		paginationSuffixElement.addAttribute(new Attribute("id", "Pageable_Suffix"));
		XmlElement pageEnd = new XmlElement("if");
		pageEnd.addAttribute(new Attribute("test", "pageBegin != null and pageSize !=null"));
		pageEnd.addElement(new TextElement(" ) pageable_limited limit #{pageBegin} , #{pageSize}"));
		paginationSuffixElement.addElement(pageEnd);
		parentElement.addElement(paginationSuffixElement);

		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType() + "Ext");
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		context.getCommentGenerator().addJavaFileComment(interfaze);

		String rootInterface = introspectedTable.getMyBatis3JavaMapperType();

		if (StringUtility.stringHasValue(rootInterface)) {
			FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
			interfaze.addSuperInterface(fqjt);
			interfaze.addImportedType(fqjt);
		}

		GeneratedJavaFile gjf = new MyExtGeneratedJavaFile(interfaze,
				context.getJavaModelGeneratorConfiguration().getTargetProject(),
				context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());
		List<GeneratedJavaFile> gjfs = super.contextGenerateAdditionalJavaFiles(introspectedTable);
		if (gjfs == null)
			gjfs = new ArrayList<>();
		gjfs.add(gjf);
		return gjfs;
	}

	private class MyExtGeneratedJavaFile extends GeneratedJavaFile {
		public MyExtGeneratedJavaFile(CompilationUnit compilationUnit, String targetProject, String fileEncoding,
				JavaFormatter javaFormatter) {
			super(compilationUnit, targetProject, fileEncoding, javaFormatter);
		}

		@Override
		public String getFormattedContent() {
			File targetFile = getTargetFile(getTargetProject(), getTargetPackage(), getFileName());
			if (targetFile != null) {
				try {
					return readFileAsString(targetFile);
				} catch (IOException e) {
				}
			}

			return super.getFormattedContent();
		}

		private String readFileAsString(File filePath) throws IOException {
			StringBuffer fileData = new StringBuffer();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(filePath),"UTF-8"));
			String tempLine = null;
			while((tempLine = reader.readLine()) != null){
				fileData.append(tempLine);
				fileData.append("\r\n");
			}
			reader.close();
			return fileData.toString();
		}

		private File getTargetFile(String targetProject, String targetPackage, String targetFileName) {
			File project = new File(targetProject);
			if (!project.isDirectory())
				return null;

			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(targetPackage, "."); //$NON-NLS-1$
			while (st.hasMoreTokens()) {
				sb.append(st.nextToken());
				sb.append(File.separatorChar);
			}

			File directory = new File(project, sb.toString());
			if (!directory.isDirectory())
				return null;

			File targetFile = new File(directory, targetFileName);
			if (targetFile.exists())
				return targetFile;

			return null;
		}
	}

	@Override
	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {
		XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
		String namespace = introspectedTable.getMyBatis3SqlMapNamespace() + "Ext";
		answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
				namespace));

		context.getCommentGenerator().addRootComment(answer);

		Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
				XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
		document.setRootElement(answer);

		String xmlMapperFileExtName = introspectedTable.getFullyQualifiedTable().getDomainObjectName()
				+ "MapperExt.xml";

		GeneratedXmlFile gxf = new GeneratedXmlFile(document, xmlMapperFileExtName,
				introspectedTable.getMyBatis3XmlMapperPackage(),
				context.getSqlMapGeneratorConfiguration().getTargetProject(), true, context.getXmlFormatter());

		List<GeneratedXmlFile> gxfs = super.contextGenerateAdditionalXmlFiles(introspectedTable);
		if (gxfs == null)
			gxfs = new ArrayList<>();
		gxfs.add(gxf);
		return gxfs;
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		XmlElement pageStart = new XmlElement("include"); //$NON-NLS-1$
		pageStart.addAttribute(new Attribute("refid", "Pageable_Prefix"));
		element.getElements().add(0, pageStart);

		XmlElement isNotNullElement = new XmlElement("include"); //$NON-NLS-1$
		isNotNullElement.addAttribute(new Attribute("refid", "Pageable_Suffix"));
		element.getElements().add(isNotNullElement);

		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	/**
	 * @param topLevelClass
	 * @param introspectedTable
	 * @param name
	 */
	private void addPage(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(new FullyQualifiedJavaType("java.lang.Integer"));
		field.setName(name);
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}

	@Override
    protected void makeSerializable(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {

        topLevelClass.addImportedType(serializable);
        topLevelClass.addSuperInterface(serializable);
        String classString = topLevelClass.getType().getFullyQualifiedName();
        Field field = new Field();
        field.setFinal(true);
        
        field.setInitializationString(getLongHashCode(classString.getBytes())+"L"); //$NON-NLS-1$
        field.setName("serialVersionUID"); //$NON-NLS-1$
        field.setStatic(true);
        field.setType(new FullyQualifiedJavaType("long")); //$NON-NLS-1$
        field.setVisibility(JavaVisibility.PRIVATE);
        context.getCommentGenerator().addFieldComment(field, introspectedTable);

        topLevelClass.getFields().add(0, field);

    }
	
    private long getLongHashCode(byte[] value) {
    	long h = 0;
    	if(value.length>0){
    		for (int i = 0; i < value.length; i++) {
                h = 31 * h + value[i];
            }
    	}
        return h;
    }	
	/**
	 * This plugin is always valid - no properties are required
	 */
	public boolean validate(List<String> warnings) {
		return true;
	}
	
	
	public static void main(String[] argc){
		try {
			System.out.println(readFileAsString1(new File("C:\\TTradeContractFavMapperExt.java")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String readFileAsString1(File filePath) throws IOException {
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(filePath),"UTF-8"));
		String tempLine = null;
		while((tempLine = reader.readLine()) != null){
			fileData.append(tempLine);
			fileData.append("\r\n");
		}
		reader.close();
		return fileData.toString();
	}
}