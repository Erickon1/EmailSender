package mx.erma.emailSender.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.erma.emailSender.dto.EmailDto;
import mx.erma.emailSender.service.IEmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	private IEmailService service;
	
	public EmailController(IEmailService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<String> send(@RequestBody EmailDto dto ) {
		service.sendEmail(dto);
		return new ResponseEntity<>("ok",HttpStatus.OK);
	}

}
