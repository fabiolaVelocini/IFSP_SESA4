package com.ifsp.sesa4.listeners;

import com.ifsp.sesa4.events.PasswordChangeRequested;
import com.ifsp.sesa4.events.UserCreated;
import com.ifsp.sesa4.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Component
public class UserListener {

    private final String applicationAddress;

    @Autowired
    private final EmailService emailService;

    private final SpringTemplateEngine thymeleafTemplateEngine;

    public UserListener(
            @Value("${application-address}") String applicationAddress,
            EmailService emailService,
            SpringTemplateEngine thymeleafTemplateEngine
    ) {
        this.applicationAddress = applicationAddress;
        this.emailService = emailService;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    private void userCreated(UserCreated event) throws MessagingException {
        String email = event.user.getEmail();
        String name = event.user.getName();
        String subject = "[SESA4] Primeiro acesso";
        String token = event.user.getPasswordToken();
        String link = this.applicationAddress + "/first-access?passwordToken=" + token + "&email=" + email + "&isFirstAccess=true";

        Context thymeleafContext = new Context();

        thymeleafContext.setVariable("email", email);
        thymeleafContext.setVariable("name", name);
        thymeleafContext.setVariable("link", link);

        String content = this.thymeleafTemplateEngine.process("first-access.html", thymeleafContext);

        this.emailService.sendEmail(email,
                subject,
                content
        );

    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    private void passwordChangeRequested(PasswordChangeRequested event) throws MessagingException {
        String email = event.user.getEmail();
        String name = event.user.getName();
        String subject = "[SESA4] Recuperar senha";
        String token = event.user.getPasswordToken();
        String link = this.applicationAddress + "/update-password?passwordToken=" + token + "&email=" + email + "&isFirstAccess=false";

        Context thymeleafContext = new Context();

        thymeleafContext.setVariable("name", name);
        thymeleafContext.setVariable("link", link);

        String content = thymeleafTemplateEngine.process("password-update.html", thymeleafContext);

        this.emailService.sendEmail(email,
                subject,
                content
        );
    }

}
