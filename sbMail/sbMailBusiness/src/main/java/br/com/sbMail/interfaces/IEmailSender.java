package br.com.sbMail.interfaces;

import br.com.sbMail.model.MailModel;

public interface IEmailSender {

	public void send(MailModel email);

	public void sendAnexo(MailModel email);

	void sendDinamico(MailModel email);

}
