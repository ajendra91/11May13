package com.example.Sechuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	/*@Scheduled(fixedRate = 1000,initialDelay = 3000)
	public void hello(){
		String[] args={"ds"};
		main(args);
		System.out.println("hello devtool");
	}*/

	@Scheduled(cron = "0/5 * * * * ?")
	public void demo(){
		System.out.println("cron");
	}

}
