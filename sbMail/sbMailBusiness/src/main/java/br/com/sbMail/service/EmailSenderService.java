package br.com.sbMail.service;

import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.sbMail.exception.CustomGenericException;
import br.com.sbMail.interfaces.IEmailSender;
import br.com.sbMail.model.CustomMail;
import br.com.sbMail.model.MailModel;
import br.com.sbMail.util.PropertyUtil;
import br.com.sbMail.util.UtilService;

@Service
public class EmailSenderService implements IEmailSender{
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private TemplateEmailService template;
	
	private static final String EMAIL_PREFIX = "xxxx"; //TODO USUARIO GMAIL
	private static final String DOMINIO = "@gmail.com";
	
	@Autowired
	private UtilService util;

	@Override
	public void send(MailModel email) {
		
		if(Objects.nonNull(email)) {
			
			String html = template.buildTemplate(email.getContent(), email.getTemplate());
			
			MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper msg = new MimeMessageHelper(mimeMessage);
					msg.setTo(email.getTo());
					msg.setSubject(email.getSubject());
					msg.setFrom(buildEmailSender(email.getPrefix()));
					msg.setText(html, true); 
				}
			};
			
			try {
				emailSender.send(messagePreparator);
			} catch (Exception e) {
				throw new CustomGenericException(HttpStatus.INTERNAL_SERVER_ERROR, e);
			}
		} else {
			throw new CustomGenericException(util.getMessage(PropertyUtil.EXCEPTION_BAD_REQUEST.getKey()), HttpStatus.BAD_REQUEST);
		}
	}
	
	private String buildEmailSender(String emailPrefix) {
		return StringUtils.isEmpty(emailPrefix) ? EMAIL_PREFIX.concat(DOMINIO) : emailPrefix.concat(DOMINIO);
	}

	@Override
	public void sendAnexo(MailModel email) {
		
		if(Objects.nonNull(email)) {
			
			Boolean enviaHtml = Objects.nonNull(email.getCustomEmail().getHtml()) ? Boolean.TRUE : Boolean.FALSE;
			
			MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, enviaHtml);
					msg.setTo(email.getTo());
					
					if(Objects.nonNull(email.getCustomEmail().getDestinatarioCopia())) {
						msg.setCc(email.getCustomEmail().getDestinatarioCopia());
					}
					if(Objects.nonNull(email.getCustomEmail().getDestinatarioCopiaOculta())) {
						msg.setBcc(email.getCustomEmail().getDestinatarioCopiaOculta());
					}
					
					msg.setSubject(email.getSubject());
					msg.setFrom(buildEmailSender(email.getPrefix()));
					msg.setText(email.getCustomEmail().getHtml(), enviaHtml); 
					
					if(Objects.nonNull(email.getCustomEmail().getArquivo())) {
						ByteArrayDataSource dSource = new ByteArrayDataSource(email.getCustomEmail().getArquivo(), email.getCustomEmail().getTipoArquivo());
						msg.addAttachment(email.getCustomEmail().getNomeArquivo(), dSource);
					}
				}
			};
			 
			try {
				emailSender.send(messagePreparator);
			} catch (Exception e) {
				throw new CustomGenericException(HttpStatus.INTERNAL_SERVER_ERROR, e);
			}
		} else {
			throw new CustomGenericException(util.getMessage(PropertyUtil.EXCEPTION_BAD_REQUEST.getKey()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@Override
	public void sendDinamico(MailModel email) {
		
		if (Objects.nonNull(email) && !email.getAddresses().isEmpty()) {
			
			String html = template.buildTemplate(email.getVariables(), email.getTemplate());
			
			MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper msg = new MimeMessageHelper(mimeMessage);
					msg.setTo(email.getAddresses().stream().toArray(String[]::new));
					msg.setSubject(email.getSubject());
					msg.setFrom(buildEmailSender(email.getPrefix()));
					msg.setText(html, Boolean.TRUE);
					buildEmailCustom(email.getCustomEmail(), msg);
				}
			};

			try {
				emailSender.send(messagePreparator);
			} catch (Exception e) {
				throw new CustomGenericException(HttpStatus.INTERNAL_SERVER_ERROR, e);
			}
		} else {
			throw new CustomGenericException(util.getMessage(PropertyUtil.EXCEPTION_BAD_REQUEST.getKey()), 
					HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * @param email
	 * @param msg
	 * @throws MessagingException
	 */
	private void buildEmailCustom(CustomMail customEmail, MimeMessageHelper msg) throws MessagingException {
		if (Objects.nonNull(customEmail)) {
			if (Objects.nonNull(customEmail.getDestinatarioCopia())) {
				msg.setCc(customEmail.getDestinatarioCopia());	
			}
			if (Objects.nonNull(customEmail.getDestinatarioCopiaOculta())) {
				msg.setBcc(customEmail.getDestinatarioCopiaOculta());	
			}
			if(Objects.nonNull(customEmail.getArquivo())) {
				ByteArrayDataSource dSource = new ByteArrayDataSource(
						customEmail.getArquivo(), customEmail.getTipoArquivo());
				msg.addAttachment(customEmail.getNomeArquivo(), dSource);
			}
		}
	}
	
}
