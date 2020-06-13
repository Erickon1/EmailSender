package mx.erma.emailSender.service;

import mx.erma.emailSender.dto.EmailDto;

public interface IEmailService {
	
	void sendEmail(EmailDto dto);

}
