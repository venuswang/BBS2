package cn.scau.edu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cn.scau.edu.dao.ArticleEvent;

/**
 * 该工厂用于获得ArticleEvent的实例，使用了单例模式
 * @author Administrator
 *
 */
public class ClassFactory {
	private static ClassFactory cf = null;
	private ArticleEvent article = null;
	private Properties pro = null;
	
	private ClassFactory() {
		if(null == pro) {
			pro = new Properties();
		}
		InputStream inStream = ClassFactory.class.getClassLoader().getResourceAsStream("Config/className.properties");
		try {
			pro.load(inStream);
			String cn = pro.getProperty("className");
			article = (ArticleEvent)Class.forName(cn).newInstance();
		} catch (IOException e) {
			throw new MyException();
		} catch (InstantiationException e) {
			throw new MyException();
		} catch (IllegalAccessException e) {
			throw new MyException();
		} catch (ClassNotFoundException e) {
			throw new MyException();
		}
	}
	
	public static ClassFactory getInstance() {
		if(null == cf) {
			cf = new ClassFactory();
		}
		return cf;
	}
	
	public ArticleEvent getArticleEvent() {
		return article;
	}
	
}
