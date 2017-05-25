package com.ulceredge.slotmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SlotmachineApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(SlotmachineApplication.class, args);
	}
        
        @Bean
        Account account() {
            return Account.getInstance();
        }
        
        @Bean
        Slotmachine slotmachine() {
            return new Slotmachine();
        }
}
