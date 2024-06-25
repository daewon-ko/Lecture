package sample.cafekiosk_review.spring.client.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailSendClient {

    public boolean sendEmail(final String fromEmail, final String toEmail, final String subject, final String content) {

        // 메일 전송로직
        log.info("메일 전송");
        throw new IllegalArgumentException("메일 전송");
    }
}
