package com.bakefinity.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {
    public static void sendOrderConfirmationEmail(String toEmail, String customerName, String orderDetails) {
        final String fromEmail = "ma5boozbakeryshop@gmail.com";
        final String password = "cpuj qdeq kgmz xyor";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Order Confirmation - Ma5booz");
            message.setText("Dear " + customerName + ",\n\nThanks for your order! Here are the details of your purchase: \n" + orderDetails + "\n\nWe’ll ship it to you soon 🍰");

            Transport.send(message);
            System.out.println("Confirmation email sent to " + toEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}