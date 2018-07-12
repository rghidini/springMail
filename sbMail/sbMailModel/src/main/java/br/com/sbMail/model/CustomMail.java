package br.com.sbMail.model;

public class CustomMail {
	
	private String html;
	private byte[] arquivo;
	private String nomeArquivo;
	private String tipoArquivo;
	private String[] destinatarioCopia;
	private String[] destinatarioCopiaOculta;
	
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public byte[] getArquivo() {
		return arquivo;
	}
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getTipoArquivo() {
		return tipoArquivo;
	}
	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	public String[] getDestinatarioCopia() {
		return destinatarioCopia;
	}
	public void setDestinatarioCopia(String[] destinatarioCopia) {
		this.destinatarioCopia = destinatarioCopia;
	}
	public String[] getDestinatarioCopiaOculta() {
		return destinatarioCopiaOculta;
	}
	public void setDestinatarioCopiaOculta(String[] destinatarioCopiaOculta) {
		this.destinatarioCopiaOculta = destinatarioCopiaOculta;
	}

}
