package br.com.sbMail.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.sbMail.exception.CustomGenericException;

@Service
public class UtilService {

	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

	@Autowired
	private MessageSource messageSource;

	public String encriptToMD5( String pParam ){

		String pReturn = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(pParam.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			pReturn = number.toString(16).toUpperCase();

		} catch (Exception e) {
			throw new CustomGenericException(HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return pReturn;
	}


	/**
	 * Validar CPF
	 * @param pNum
	 * @return
	 */
	public boolean validaCpf( Long pNum ){

		String cpf = String.valueOf(pNum);

		if ((cpf==null) || (cpf.length()!=11)) return false;

		Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString()); 
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
			soma += digito*peso[peso.length-str.length()+indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}


	/**
	 * Capturar a mensagem correspondente
	 * @param pMsg
	 * @return
	 */
	public String getMessage( String pMsg ){
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(pMsg, null, locale);
	}
	
	/**
	 * Convert String para data
	 * @param pData
	 * @return
	 */
	public Timestamp convertStringToDateTime( String pData ){
		
		 try {
		      DateFormat formatter;
		      formatter = new SimpleDateFormat("ddMMyyyy");
		      Date date = formatter.parse(pData);
		      java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

		      return timeStampDate;
		    } catch (ParseException e) {
		      System.out.println("Exception :" + e);
		      return null;
		    }
	}
	
}
