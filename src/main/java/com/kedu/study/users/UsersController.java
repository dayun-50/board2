package com.kedu.study.users;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@CrossOrigin(origins = "*")
public class UsersController {
	@Autowired
	private UsersService uServ;
	
	// 회원가입
	@PostMapping
	public ResponseEntity<Map<String, Object>> userInsert(@RequestBody UsersDTO dto){
	    Map<String, Object> result = new HashMap<>();
	    try {
	        uServ.insert(dto);
	        result.put("success", true);
	        result.put("message", "회원가입 완료!");
	    } catch(Exception e) {
	        result.put("success", false);
	        result.put("message", e.getMessage());
	    }
	    return ResponseEntity.ok(result);
	}
	
	// 로그인
	@GetMapping
	public ResponseEntity<String> login(String id, String pw){
		uServ.login(id, pw);
		return ResponseEntity.ok().build();
	}
}
