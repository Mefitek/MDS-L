package mds.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
public class Cv4Application
{

	public static void main(String[] args)
	{
		SpringApplication.run(Cv4Application.class, args);
	}

	@GetMapping
	@ResponseBody
	public String hello(){
		return "Uvodni stranka localhost:8080/";
	}

}
