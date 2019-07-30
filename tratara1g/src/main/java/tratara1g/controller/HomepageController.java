package tratara1g.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomepageController {
	
	@GetMapping(value= {"/"})
	public String home() {
		return "homepage"; //view
	}
	
	@GetMapping("/admin")
	public String admin() {

		return "admin"; //view
	}
	
	@PostMapping({"/403", "/error"})
	public String error403() {

		return "403Page"; //view
	}
}
