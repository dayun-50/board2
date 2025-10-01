package com.kedu.study.users;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {	
	@Autowired
	private UsersDAO dao;

	//이코드 나중에 파일따로 빼서 암호화용 클레스안에 메서드 보관하고 끄내오는쪽으로 바꾸셈
	public static String encrypt(String text) { //SHA 암호화
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
			byte[] digest = md.digest(bytes);

			StringBuilder builder = new StringBuilder();
			for (byte b : digest) {
				builder.append(String.format("%02x", b));
			}
			return builder.toString();

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-512 암호화 실패", e);
		}
	}

	// 회원가입
	public int insert(UsersDTO dto) {
		dto.setPw(encrypt(dto.getPw()));
		return dao.insert(dto);
	}

	//loginCheck
	public String loginCheck (UsersDTO dto) {
		dto.setPw(encrypt(dto.getPw()));
		return dao.loginCheck(dto);
	}
	
	// secession
	public int secession(String name) {
		return dao.secession(name);
	}
	
	//마이페이지 데이터
	public List<UsersDTO> selectUserDate(String name){
		return dao.selectUserDate(name);
	}
	
}
