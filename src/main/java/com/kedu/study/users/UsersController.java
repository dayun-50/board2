package com.kedu.study.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UsersController {
	@Autowired
	private UsersService uServ;
	
	// 회원가입
	@PostMapping
	public ResponseEntity<Void> userInsert(@RequestBody UsersDTO dto){
		uServ.insert(dto);
		return ResponseEntity.ok().build();
	}
	
	// 로그인
	@GetMapping
	public ResponseEntity<String> login(String id, String pw){
		String name = uServ.login(id, pw);
		return ResponseEntity.ok(name);
	}
}
