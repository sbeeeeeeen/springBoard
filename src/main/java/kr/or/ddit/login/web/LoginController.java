package kr.or.ddit.login.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.login.service.LoginServiceInf;
import kr.or.ddit.model.StudentVo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@SessionAttributes(value={"student", "boardList"})
@RequestMapping("/login")
@Controller
public class LoginController {
	
	@Resource(name="loginService")
	private LoginServiceInf loginService;
	
	@RequestMapping(value="/login")
	public String login(){
		return "login/login";
	}
	
	@RequestMapping(value="/stdLogin", method=RequestMethod.POST)
	public String main(StudentVo student, Model model){
		Map<String, String> loginInfo = new HashMap<String, String>();
		loginInfo.put("std_id", student.getStd_id());
		loginInfo.put("pass", student.getPass());
		if(loginService.getStudent(loginInfo)){
			model.addAttribute("boardList", loginService.getBoardList());
			model.addAttribute("student", student);
			return "main";
		}else{
			return "/login/login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(SessionStatus session){
		session.setComplete();
		return "/login/login";
	}
}
