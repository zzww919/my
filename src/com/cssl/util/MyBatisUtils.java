package com.cssl.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
  

public class MyBatisUtils {

	private static SqlSessionFactory factory = null;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	
	static {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
	
	public static SqlSession getSession() {
		SqlSession session = threadLocal.get();
		if(session == null) {
			session = factory.openSession();
			threadLocal.set(session);
		}
		return session;
	}
	
	public static void close() {
		SqlSession session = threadLocal.get();
		if(session!=null) {
			session.close();
		}
		threadLocal.set(null);
	}
	
	
	public static void main(String[] args) {
//		SqlSession s1 = factory.openSession();
//		SqlSession s2 = factory.openSession();
		SqlSession s1 = getSession();
		SqlSession s2 = getSession();
		
		System.out.println(s1 == s2);
	}
	
}
