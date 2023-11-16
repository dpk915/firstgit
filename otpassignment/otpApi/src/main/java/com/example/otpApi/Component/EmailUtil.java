// EmailUtil.java
package com.example.otpApi.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtp(String email, String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP");

        mimeMessageHelper.setText(
            "<div>" +
            "  <a href=\"http://localhost:8080/verify-account?email=" + email + "&otp=" + otp + "\" target=\"_blank\">Click link to verify</a>" +
            "</div>",
            true
        );

        javaMailSender.send(mimeMessage);
    }
}
