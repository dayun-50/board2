package com.kedu.study.users;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {	
	@Autowired
	private UsersDAO dao;

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

	// 로그인
	public String login(String id, String pw) {
		String realPw = encrypt(pw);
		return dao.login(id, realPw);
	}

}
