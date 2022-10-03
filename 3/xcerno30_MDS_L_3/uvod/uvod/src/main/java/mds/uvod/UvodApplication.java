package mds.uvod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class UvodApplication {

	public static void main(String[] args) {
		SpringApplication.run(UvodApplication.class, args);
	}


	@GetMapping
	@ResponseBody
	public String hello(){
		return "Hello World";
	}

}
