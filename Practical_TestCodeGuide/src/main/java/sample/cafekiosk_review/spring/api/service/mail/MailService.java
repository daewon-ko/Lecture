package sample.cafekiosk_review.spring.api.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk_review.spring.client.mail.MailSendClient;
import sample.cafekiosk_review.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk_review.spring.domain.history.mail.MailSendHistoryRepository;

@RequiredArgsConstructor
@Service
public class MailService {
    private final MailSendClient mailSendClient;
    private final MailSendHistoryRepository mailSendHistoryRepository;

    public boolean sendMail(final String fromEmail, final String toEmail, final String subject, final String content) {

        boolean result = mailSendClient.sendEmail(fromEmail, toEmail, subject, content);
        if (result) {
            mailSendHistoryRepository.save(MailSendHistory.builder()
                    .fromEmail(fromEmail)
                    .toEmail(toEmail)
                    .subject(subject)
                    .content(content).build());
            return true;

        }
        return false;

    }
}
