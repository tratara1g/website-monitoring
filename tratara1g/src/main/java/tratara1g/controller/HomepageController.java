package tratara1g.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomepageController {
	
	@GetMapping("/")
	public String main() {

		return "homepage"; //view
	}
	
	@PostMapping("/")
	public String admin() {

		return "admin"; //view
	}
}
