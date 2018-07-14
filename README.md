# springMail

## Para utilização é necessário realizar as seguintes alterações:  
 + Abrir o arquivo application.properties localizado no modulo **sbMailConfig** e alterar os valores de **spring.mail.username** e **spring.mail.password** por seu **usuário e senha do Gmail**;  
 + Abrir a classe **MailConfig** e alterar os valores de **javaMailSender.setUsername()** e **javaMailSender.setPassword()** por seu **usuário e senha do Gmail**;  
 + Abrir a classe **EmailSenderService** e alterar o valor da constante **EMAIL_PREFIX** por seu **usuário do Gmail**;  
 + Abrir o template **email.html** e definir a mensagem que deseja enviar.
