package cn.scau.edu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.scau.edu.dao.ArticleEvent;
import cn.scau.edu.dao.RegisterEventImp;

/**
 * 该工厂用于获得ArticleEvent的实例，使用了单例模式
 * @author Administrator
 *
 */
public class ClassFactory {
	private static Log log = LogFactory.getLog(ClassFactory.class);
	private static ClassFactory cf = null;
	private ArticleEvent article = null;
	private RegisterEventImp register = null;
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
			String cn1 = pro.getProperty("className1");
			register = (RegisterEventImp)Class.forName(cn1).newInstance();
		} catch (IOException e) {
			log.error(e);
			throw new MyException();
		} catch (InstantiationException e) {
			log.error(e);
			throw new MyException();
		} catch (IllegalAccessException e) {
			log.error(e);
			throw new MyException();
		} catch (ClassNotFoundException e) {
			log.error(e);
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
	
	public RegisterEventImp getRegisterEvent() {
		return register;
	}
	
}
