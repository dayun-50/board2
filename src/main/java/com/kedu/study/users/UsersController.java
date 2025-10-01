package com.kedu.study.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/users")
@RestController
public class UsersController {
	@Autowired
	private UsersService uServ;

	// 회원가입
	@PostMapping
	public ResponseEntity<Integer> userInsert(@RequestBody UsersDTO dto) {
		int result = uServ.insert(dto);
		return ResponseEntity.ok(result);
	}

	//1. axios가 request에 세션이크를 담아서 보내주지 않음 
	//2. 서버에서 크로스 오리진 상태에서는 세션키를 인정하지 않음
	//인서트 , 로그인
	@PostMapping("/login") 
	public ResponseEntity<Map<String,String>> insert(@RequestBody UsersDTO dto, HttpSession session){
		String isExist = uServ.loginCheck(dto);// 0이면 로그인 불가

		if(isExist != null) { 
			session.setAttribute("loginId", isExist);
			Map<String,String> result = new HashMap<>();
			result.put("loginId",(String)session.getAttribute("loginId")); 
			return ResponseEntity.ok(result); 
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<List<UsersDTO>> selectUserDate(@PathVariable String name){
		System.out.println("asfafsddsafdfs----"+name);
		List<UsersDTO> list = uServ.selectUserDate(name);
		System.out.println("adfs::::"+list);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<Integer> secession(@PathVariable String name){
		int result = uServ.secession(name);
		return ResponseEntity.ok(result);
	}
}
