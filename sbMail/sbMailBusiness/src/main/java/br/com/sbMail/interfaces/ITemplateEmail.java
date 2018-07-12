package br.com.sbMail.interfaces;

import java.util.Map;

public interface ITemplateEmail {

	public String buildTemplate(String message, String template);

	/**
	 * @param variables
	 * @param template
	 * @return
	 */
	String buildTemplate(final Map<String, Object> variables, final String template);

}
