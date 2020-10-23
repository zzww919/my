package com.cssl.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cssl.pojo.Emp;

public interface EmpMapper {
	
	public List<Emp> selectMap(Map<String, Object> map);
	
	
	//ÐÞ¸Ä
	public int updateEmp(Emp emp);
	
	public int updateMap(Map<String, Object> map);
	
	public int deleteEmp(Emp em);
	
	public int deletein(List<Integer> list);
	
	public List<Emp> selectParam(@Param("eno") Integer eno);
	
	public List<Emp> selectpx(@Param("d") String d,@Param("a") Integer a,@Param("b") Integer b);
     
	public List<Emp> selectin(List<Integer> list);

	public int updateParam(@Param("d") Date d,@Param("e") Integer eno);
}
