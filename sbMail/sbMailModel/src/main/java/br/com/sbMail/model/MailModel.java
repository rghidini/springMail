package br.com.sbMail.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MailModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long emailId;
	private String to;
	private List<String> addresses = new ArrayList<>();
	private String subject;
	private String content;
	private String template;
	private String prefix;
	private CustomMail customEmail;
	private Map<String, Object> variables = new LinkedHashMap<>();
	
	public Long getEmailId() {
		return emailId;
	}
	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public CustomMail getCustomEmail() {
		return customEmail;
	}
	public void setCustomEmail(CustomMail customEmail) {
		this.customEmail = customEmail;
	}
    public Map<String, Object> getVariables() {
        return Collections.unmodifiableMap(this.variables);
    }
    public void addVariable(final String name, final Object value) {
        this.variables.put(name, value);
    }
	public List<String> getAddresses() {
		return Collections.unmodifiableList(this.addresses);
	}
	public void addAddress(final String address) {
		this.addresses.add(address);
	}
}
