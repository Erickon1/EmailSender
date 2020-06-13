package mx.erma.emailSender.service.impl;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import mx.erma.emailSender.dto.EmailDto;
import mx.erma.emailSender.service.IEmailService;

@Service
public class EmailService implements IEmailService{

    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender,
    		TemplateEngine templateEngine) {
		this.javaMailSender = javaMailSender;
		this.templateEngine = templateEngine;
	}
	
	@Override
	public void sendEmail(EmailDto dto) {
		
        System.out.println("Sending Email...");
		try {
			send(dto);
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
        System.out.println("Done");

	}
	
	
	private void send(EmailDto dto) 
			throws MessagingException, IOException {

		String content = build(dto.getBody());
        MimeMessage msg = javaMailSender.createMimeMessage();
        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(dto.getEmail());
        helper.setSubject(dto.getSubject());
        // true = text/html
        helper.setText(content, true);
		javaMailSender.send(msg);

    }
	
	private String build(String message) {
		
	  Context context = new Context();
	  context.setVariable("body", message);
	  return templateEngine.process("mailTemplate", context);
	  
	}

}
