package br.com.sbMail.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import br.com.sbMail.interfaces.ITemplateEmail;

@Service
public class TemplateEmailService implements ITemplateEmail{

	@Autowired
	private SpringTemplateEngine  templateEngine;

	@Override
	public String buildTemplate(String message, String template) {
		Context context = new Context();
		context.setVariable("message", message);
		return templateEngine.process(template, context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String buildTemplate(Map<String, Object> variables, String template) {
		Context context = new Context();
		variables.forEach((key, value) -> context.setVariable(key, value));
		return templateEngine.process(template, context);
	}
	
}
