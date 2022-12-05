package com.ifsp.sesa4;

import com.ifsp.sesa4.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Sesa4Application {

	@Autowired
	private EmailService senderService;

	public static void main(String[] args) {
		SpringApplication.run(Sesa4Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		senderService.sendSimpleEmail("",
				"[SESA4] Projeto",
				"A aplicação foi iniciada com sucesso!");

	}

}
