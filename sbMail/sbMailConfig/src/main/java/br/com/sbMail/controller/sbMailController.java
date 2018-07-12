package br.com.sbMail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sbMail.interfaces.IEmailSender;
import br.com.sbMail.model.MailModel;

@CrossOrigin
@RestController
@RequestMapping("/email")
public class sbMailController {
	
	@Autowired
	private IEmailSender service; 

	@PostMapping("/dinamico")
	public ResponseEntity<Void> enviarEmailDinamico(@RequestBody MailModel email) {
		service.sendDinamico(email);
		return ResponseEntity.ok().build();
	}
	
}
