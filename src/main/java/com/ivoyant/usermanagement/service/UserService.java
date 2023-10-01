package com.ivoyant.usermanagement.service;

import com.ivoyant.usermanagement.entity.User;
import com.ivoyant.usermanagement.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Value("${TWILIO_ACCOUNT_SID}")
    private String userName;
    @Value("${TWILIO_AUTH_TOKEN}")
    private String password;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailSenderService emailSenderService;

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        if (savedUser != null) {
            String phoneNumber = savedUser.getPhoneNumber();
            String a = userName;
            String b = password;
            Twilio.init(userName, password);
            Long userID = savedUser.getId();
            String userPassword = savedUser.getPassword();
            String twilioMessage = "User Successfully Registered and your User Id : " + userID + " and your Password :" + userPassword + " Don't share with any one";
            //SMS Notification Service
            Message.creator(new PhoneNumber(phoneNumber),
                    new PhoneNumber("+12706329318"), twilioMessage).create();
            // WhatsAPP Notification Service
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:+918495979687"),
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                    twilioMessage
            ).create();
            //MAIL Notification Service
            String subject = "Successfull Registration Alert";
            emailSenderService.sendSimpleEmail(user.getEmail().toString(), subject, twilioMessage);
        }
        return savedUser;
    }

}


