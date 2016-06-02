package com.lw.fsx.util;

import java.util.Properties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class ConfigParser extends DefaultHandler {

	// //定义一个Properties 用来存放属性值
	private Properties props;

	private StringBuffer currentValue = new StringBuffer();

	// 构建器初始化props
	public ConfigParser() {

		this.props = new Properties();
	}

	public Properties getProps() {
		return this.props;
	}

	// 定义开始解析元素的方法. 这里是将<xxx>中的名称xxx提取出来.
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		currentValue.delete(0, currentValue.length());
	}

	// 这里是将<xxx></xxx>之间的值加入到currentValue
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentValue.append(ch, start, length);
	}

	// 在遇到</xxx>结束后,将之前的名称和值一一对应保存在props中
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		props.put(qName.toLowerCase(), currentValue.toString().trim());
	}

}

class ParseXML {
	// 定义一个Properties 用来存放属性值
	private Properties props;

	public Properties getProps() {
		return this.props;
	}

	public void parse(String filename) throws Exception {
		// 将我们的解析器对象化
		ConfigParser handler = new ConfigParser();
		// 获取SAX工厂对象
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(false);
		factory.setValidating(false);
		// 获取SAX解析
		SAXParser parser = factory.newSAXParser();
		try {
			// 将解析器和解析对象xml联系起来,开始解析
			parser.parse(filename, handler);
			// 获取解析成功后的属性
			props = handler.getProps();
		} finally {
			factory = null;
			parser = null;
			handler = null;
		}
	}
}

public class ReadConfigXml {
	private Properties props;

	public ReadConfigXml(String url) {
		ParseXML myRead = new ParseXML();
		try {
			myRead.parse(url);
			props = new Properties();
			props = myRead.getProps();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getHost() {
		return props.getProperty("host");
	}

	public String getDataBase() {
		return props.getProperty("database");
	}

	public String getUserName() {
		return props.getProperty("username");
	}

	public String getPassWord() {
		return props.getProperty("password");
	}

	public String getBizPhone() {
		return props.getProperty("bizphone");
	}

	public String getBizPwd() {
		return props.getProperty("bizpwd");
	}

	public String getMySqlHost() {
		return props.getProperty("mysqlhost");
	}

	public String getMySqlUserName() {
		return props.getProperty("mysqlusername");
	}

	public String getMySqlPassWord() {
		return props.getProperty("mysqlpassword");
	}
	//得到售卡目录
	public String getSellCardItem(String it){
		return props.getProperty("item"+it);
	}
}
