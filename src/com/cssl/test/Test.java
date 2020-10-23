package com.cssl.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthScrollPaneUI;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import com.cssl.dao.EmpMapper;
import com.cssl.pojo.Emp;
import com.cssl.util.MyBatisUtils;

import oracle.net.aso.e;
import oracle.net.aso.s;

class Test {
      
	private static SqlSessionFactory factory=null;
	private SqlSession session;
	private EmpMapper em;
  
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 factory=MyBatisUtils.getFactory();
		 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		
	}

	@BeforeEach
	void setUp() throws Exception {
		session=factory.openSession();
		em=session.getMapper(EmpMapper.class);
	}

	@AfterEach
	void tearDown() throws Exception {
		session.commit();
		session.close();
	}

	@org.junit.jupiter.api.Test
	void test() {
	   Emp emp=new Emp();
	   emp.setEname("321");
	   emp.setHiredate(new Date());
	   emp.setSal(900);
	   emp.setEmpno(4);
	   int i=em.updateEmp(emp);
	   System.out.println("受影响的函数："+i);
	}
	
	@org.junit.jupiter.api.Test
	void testMap() {
		Map<String, Object> map =new HashMap<String,Object>();
		List<Emp> list=em.selectMap(map);
		System.out.println(list.size());
		list.forEach(s->{
			System.out.println(s.toString());
		});
	}
	@org.junit.jupiter.api.Test
	void testupdate() {
		Emp emp=new Emp();
		emp.setEmpno(1);
		emp.setHiredate(new Date());
		int i=em.updateEmp(emp);
		System.out.println(i);
	}
	
	@org.junit.jupiter.api.Test
	void testupdatemap() {
		Map<String, Object> map=new HashMap<String,Object>();
	   map.put("empno", 1);
	   map.put("hiredate",new Date());
	   int i=em.updateMap(map);
	   System.out.println(i);
	
	}
	
	@org.junit.jupiter.api.Test
	void testupdateParameter() {
		int i=em.updateParam(new Date(), 1);
		System.out.println(i);
	}
	
	@org.junit.jupiter.api.Test
	void testdelete() {
		Emp emp=new Emp();
		emp.setEmpno(4);
		int i=em.deleteEmp(null);
		System.out.println(i);
	}
	
	@org.junit.jupiter.api.Test
	void testin() {
		List<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		List<Emp> list2=em.selectin(list);
	}
	
	@org.junit.jupiter.api.Test
	void testdeletein() {
		 List<Integer> list =new ArrayList<>();
		 list.add(5);
		 list.add(6);
		 list.add(7);
		 int i=em.deletein(list);
		 session.commit();
		 System.out.println(i);
	}
	@org.junit.jupiter.api.Test
	void testpx() {
		 List<Emp> list=em.selectpx("desc", 0, 4);
	}
}
