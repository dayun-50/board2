package com.kedu.study.users;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDAO {

	@Autowired
	private SqlSession mybatis;

	// 회원가입
	public int insert(UsersDTO dto) {
		return mybatis.insert("Users.insert", dto);
	}

	//loginCheck
	public String loginCheck (UsersDTO dto) {
		return mybatis.selectOne("Users.loginCheck", dto);
	}
	
	//secession
	public int secession(String name) {
		return mybatis.delete("Users.deleteByname", name);
	}
	
	//마이페이지 데이터
	public List<UsersDTO> selectUserDate(String name){
		return mybatis.selectList("Users.selectAllByName", name);
	}

}
