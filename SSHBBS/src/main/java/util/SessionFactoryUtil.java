package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

	private static SessionFactory factory;
	static{
		//获取配置文件对象
		try{
			Configuration cfg = new Configuration().configure();
			factory = cfg.buildSessionFactory(); 
		}catch(Exception e){
			e.printStackTrace();
		}
		//根据配置文件信息产生sessionfactory对象
	}
	public static SessionFactory getSessionFactory(){
		return factory;
	}
	public static Session getSession(){
		Session session = factory.openSession();
		return session;
	}
}
