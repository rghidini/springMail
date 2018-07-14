package br.com.sbMail.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

	@Autowired
	private MessageSource messageSource;

	/**
	 * Capturar a mensagem correspondente
	 * @param pMsg
	 * @return
	 */
	public String getMessage( String pMsg ){
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(pMsg, null, locale);
	}
	
}
