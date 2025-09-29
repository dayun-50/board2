package com.kedu.study.users;

import java.util.HashMap;
import java.util.Map;

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

	// 로그인
	public String login(String id, String pw) {
		Map<String, Object> params = new HashMap<>(); 
		params.put("id", id); 
		params.put("pw", pw);
		return mybatis.selectOne("Users.login", params);
	}
}
