package com.mapduck.serivce;

import com.mapduck.domain.Own;
import com.mapduck.domain.Warranty;
import com.mapduck.dto.WarrantyDto;
import com.mapduck.repository.OwnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {
    private final OwnRepository ownRepository;
    private final WarrantyService warrantyService;

    // 매일 12시에 실행되도록 설정
    @Scheduled(cron = "0 0 0 * * *")
    public void checkExpired() {
        List<Own> owns = ownRepository.findAll();
        String email = "mapduck.notice@gmail.com";
        String pw = "mapduck2021";
        for (var own : owns) {
            LocalDate expiredDate = own.getPurchasedAt()
                    .plusMonths(
                            warrantyService.getMaxWarrantyDto(
                                    own.getOwnProduct().getWarranties()
                            ).getWrMonth()).toLocalDate();
            long differ = ChronoUnit.DAYS.between(expiredDate, LocalDate.now());
            if (differ == 31) {
                sendMail(email, pw, own, differ);
            } else if (differ == 15) {
                sendMail(email, pw, own, differ);
            } else if (differ == 7) {
                sendMail(email, pw, own, differ);
            } else if (differ == 1) {
                sendMail(email, pw, own, differ);
            }
        }

    }

    public void sendMail(String _email, String _password, Own own, long dayLeft) {
        log.debug("Email Send start");
        // 제목
        String subject = "[MaPDuck] " + own.getOwnProduct().getPrName() + "제품의 보증기간이" + dayLeft + "일 남았습니다.";
        // 보내는 메일 주소
        String fromMail = _email;
        // 보내는 사람 이름
        String fromName = "MaPDuck";
        // 보낼 사람 주소
        String toMail = own.getOwner().getEmail(); // 콤마(,) 나열 가능 // mail contents
        StringBuffer contents = new StringBuffer();
        contents.append(
                "<h1>" + own.getOwnProduct().getPrName() + "[" + own.getOwnProduct().getMoName() + "] 제품의 보증기간이 " + dayLeft + "일 남았습니다.</br>");
        contents.append("소유하신 제품에 이상이 없는지 확인해주세요.</br>");
        contents.append("</br></br>- MaPDuck");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // use Gmail
        props.put("mail.smtp.port", "587"); // set port
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // use TLS
        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() { // set authenticator
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(_email, _password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(fromMail, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 한글의 경우 encoding 필요
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
            message.setSubject(subject);
            message.setContent(contents.toString(), "text/html;charset=UTF-8"); // 내용 설정 (HTML 형식)
            message.setSentDate(new java.util.Date());
            Transport t = mailSession.getTransport("smtp");
            t.connect(_email, _password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            System.out.println("Done Done ~!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
