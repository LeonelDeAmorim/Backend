package application.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class SignUpController {
	
	@GetMapping("/signup")
	public String getSignUp() 
	{
		return "signup";
	}
}