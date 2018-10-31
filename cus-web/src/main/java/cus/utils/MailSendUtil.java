package cus.utils;

import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class MailSendUtil {

	private static Logger logger = Logger.getLogger(MailSendUtil.class);

	private static Session sendSession;

	public static Session getSendSession(String protocol, String host, int port) {
		if (sendSession == null) {
			logger.info("sendSession is null , now create session");
			try {
				Properties props = new Properties();
				props.put("mail.transport.protocol", protocol);
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", port);
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.socketFactory.fallback", "false");
				props.put("mail.smtp.socketFactory.port", port);
				sendSession = Session.getInstance(props);
			} catch (Exception e) {
				logger.error("sendSession create exception, e=" + e.getMessage());
			}
			logger.info("sendSession created");
		}
		return sendSession;
	}

	public static boolean sendMail(String replyAddress, String ccAddress, String subject, String content,
                                   String[] attachedFilePaths, Session session, String username, String password) {
		boolean sendSuccess = false;
		if (session != null) {
			try {
				MimeMessage message = createMimeMessage(session, replyAddress,
						(ccAddress == null || "".equals(ccAddress)) ? null : ccAddress.split(";"), subject, content,
						attachedFilePaths, username, password);
				Transport transport = session.getTransport();
				transport.connect(username, password);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
				sendSuccess = true;
				logger.info("sendMail success:address =" + replyAddress + "&cc=" + ccAddress + "&subject=" + subject);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		return sendSuccess;
	}

	private static MimeMessage createMimeMessage(Session session, String replyAddress, String[] ccAddress,
                                                 String subject, String content, String[] attachedFilePaths, String username, String password)
			throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(username, username, "UTF-8"));

		// 3.1 To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(replyAddress, replyAddress, "UTF-8"));

		// 3.2 CC: 收件人（可以增加多个收件人、抄送、密送）
		if (ccAddress != null) {
			for (int i = 0; i < ccAddress.length; i++) {
				message.addRecipient(MimeMessage.RecipientType.CC,
						new InternetAddress(ccAddress[i], ccAddress[i], "UTF-8"));
			}
		}
		// 4. Subject: 邮件主题
		message.setSubject(subject, "UTF-8");

		if (attachedFilePaths == null || attachedFilePaths.length == 0) {
			message.setContent(content == null ? "" : content, "text/html;charset=UTF-8");
		} else {
			MimeBodyPart htmlBodyPart = getHtmlBodyPart(content);
			MimeMultipart mmp = new MimeMultipart("mixed");
			mmp.addBodyPart(htmlBodyPart);
			for (String attachedFilePath : attachedFilePaths) {
				MimeBodyPart attachedBodyPart = getAttachedBodyPart(attachedFilePath);
				mmp.addBodyPart(attachedBodyPart);
			}
			message.setContent(mmp);
		}

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}

	private static MimeBodyPart getAttachedBodyPart(String filePath)
			throws MessagingException, UnsupportedEncodingException {
		MimeBodyPart attached = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(filePath);
		attached.setDataHandler(new DataHandler(fds));
		String fileName = doHandlerFileName(filePath);
		attached.setFileName(MimeUtility.encodeWord(fileName));// 处理附件文件的中文名问题
		return attached;
	}

	private static String doHandlerFileName(String filePath) {
		String fileName = filePath;
		if (null != filePath && !"".equals(filePath)) {
			fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		}
		return fileName;
	}

	private static MimeBodyPart getHtmlBodyPart(String content) throws MessagingException {
		MimeBodyPart contentPart = new MimeBodyPart();
		MimeMultipart mmp = new MimeMultipart("related");// 此处MIME消息头组合类型为related
		MimeBodyPart contented = new MimeBodyPart();
		contented.setContent(content, "text/html;charset=UTF-8");// 因正文内容中有中文
		mmp.addBodyPart(contented);
		contentPart.setContent(mmp);
		return contentPart;
	}

}
