package fury.templatewise.login.security.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "security/login";
	}
	
	@RequestMapping("/loginAfter")
	public String loginAfter(HttpServletRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.getPassword());
		
		System.out.println(request.getUserPrincipal().getName());
		
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userDetails.getAuthorities();
		
		for (GrantedAuthority grantedAuthority : authorities) {
			System.out.println(grantedAuthority.getAuthority());
		}
		
		System.out.println(userDetails.toString());
		
		return "security/loginAfter";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "test";
	}
	
}
