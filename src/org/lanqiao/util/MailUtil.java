package org.lanqiao.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;



public class MailUtil {
//	static final String SMTPHOST="smtp.qq.com";
//	static final String AUTHPASSWORD="dzzrbtvpjaexbegc";
//	static final String FROM="759606780@qq.com";//发送邮件的帐号
	static final String SMTPHOST="smtp.163.com";
	static final String AUTHPASSWORD="lanqiao110";
	static final String FROM="lanqiaomail@163.com";//发送邮件的帐号
	public static void sendMail(String to, String subject, String content){
		//1、建立与遇见服务器的会话--session
		Properties props =new Properties();
		props.setProperty("mail.smtp.host",SMTPHOST);//设置邮件服务器
		props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件的协议
		props.setProperty("mail.smtp.auth", "true");//设置验证
		Session session=Session.getDefaultInstance(props);
		//qq邮箱开启ssl认证
   		MailSSLSocketFactory sf=null;
   		try {
   			sf = new MailSSLSocketFactory();
   		} catch (GeneralSecurityException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		sf.setTrustAllHosts(true);
   		props.put("mail.smtp.ssl.enable", "true");
   		props.put("mail.smtp.ssl.socketFactory", sf); 
		//2、创建一封邮件---MimeMessage
		MimeMessage message =new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(FROM,"lanqiao" ,"UTF-8" ));//设置发送邮件的帐号
			message.setSubject(subject);//设置发送主题
			message.setSentDate(new Date());//设置发送日期
			message.setContent(content,"text/html;charset=utf-8");//设置发送主题
			
			
			//设置收件人
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to,subject,"UTF-8"));
//			message.setRecipient(MimeMessage.RecipientType.CC,new InternetAddress("799062291@qq.com","xzb","UTF-8") );
//			message.setRecipient(MimeMessage.RecipientType.BCC,new InternetAddress("759606780@qq.com","xzb","UTF-8") );
			
			
			//3、发送邮件--Transport
			Transport transpot =session.getTransport();
			transpot.connect(FROM,AUTHPASSWORD);
			transpot.sendMessage(message, message.getAllRecipients());
			transpot.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public  void sendMail( String subject, String content,String ...mailAddress){
		
		
	}
}
