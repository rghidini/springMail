package br.com.sbMail.config;

import java.util.Properties;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
public class MailConfig {

	@Bean
	public SpringTemplateEngine emailTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(this.htmlTemplateResolver());
		return templateEngine;
	}
	
	private ITemplateResolver htmlTemplateResolver() {
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setOrder(NumberUtils.INTEGER_ZERO);
		templateResolver.setPrefix("/br/com/sbMail/template/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateResolver.DEFAULT_TEMPLATE_MODE);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCacheable(false);
		return templateResolver;
	}
	
	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost("lesoto.rede1.br");
		javaMailSender.setPort(25);

		Properties p = new Properties();
		p.setProperty("mail.transport.protocol", "smtp");
		p.setProperty("mail.smtp.auth", "false");
		p.setProperty("mail.smtp.starttls.enable", "true");
		p.setProperty("mail.debug","false");
		
		javaMailSender.setJavaMailProperties(p);

		return javaMailSender;
	}
	
}
