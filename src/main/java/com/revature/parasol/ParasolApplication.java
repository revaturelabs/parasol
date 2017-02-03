package com.revature.parasol;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParasolApplication {

	public static void main(String[] args) {
	    try{
		
	    }
	    catch(Exception e){
		Logger.getRootLogger().error(e);
	    }
		SpringApplication.run(ParasolApplication.class, args);
	}
}
