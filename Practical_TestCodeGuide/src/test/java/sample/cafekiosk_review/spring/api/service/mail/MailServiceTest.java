package sample.cafekiosk_review.spring.api.service.mail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk_review.spring.client.mail.MailSendClient;
import sample.cafekiosk_review.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk_review.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {


    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;


    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        //given


        when(mailSendClient.sendEmail(anyString(), anyString(),
                anyString(), anyString())).thenReturn(true);
//        when(mailSendHistoryRepository.save(any(MailSendHistory.class))).thenReturn(true);
        given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString())).willReturn(true);

//        doReturn(true)
//                .when(mailSendClient)
//                .sendEmail(anyString(), anyString(), anyString(), anyString());

        //when
        boolean result = mailService.sendMail("", "", "", "");

        //then
        assertThat(result).isTrue();
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));

    }

}
