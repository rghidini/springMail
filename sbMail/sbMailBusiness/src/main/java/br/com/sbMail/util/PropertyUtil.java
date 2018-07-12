package br.com.sbMail.util;

public enum PropertyUtil {
	
	EXCEPTION_BAD_REQUEST("exception.bad.request"),
	EXCEPTION_NO_CONTENT("exception.no.content"),
	INTERNAL_SERVER_ERROR("exception.internal.error");
	
    private String key;
    
    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private PropertyUtil(String key) {
    	this.key = key;
    }

}
