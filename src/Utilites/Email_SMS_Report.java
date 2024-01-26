//package reporting;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.io.Zip;
//import org.testng.annotations.Test;
//
//import Config.CommonMethods;
//import Config.DriverScript;
//
//public class Email_SMS_Report  {
//
//	@Test
//	public static void mailSending() {
//		String from = prop.getProperty("from.mail.id");
//		String to = prop.getProperty("to.mail.ids");
//		//System.out.println("from mail: " + from);
//		//System.out.println("to mail: " + to);
//		final String username = prop.getProperty("from.mail.id");
//		final String password = prop.getProperty("from.mail.password");
//		//System.out.println("username : "+username+"- password : "+password);
//		String host = prop.getProperty("mail.host");
//
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.port", "587");
//
//		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		});
//
//		try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(from));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			Date date = new Date();
//			int passCountInt = Integer.parseInt(passCount);
//			int failCountInt = Integer.parseInt(failCount);
//			int skipCountInt = Integer.parseInt(skipCount);
//			String mailMessage = " - " + dateFormat.format(date) + " " + " Total :" + totalCount + " Pass :"
//					+ passCountInt + " Fail :" + failCountInt + " Skip :" + skipCountInt;
//
//			message.setSubject(prop.getProperty("Subject") + mailMessage);
//			BodyPart EmailBody = new MimeBodyPart();
//			EmailBody.setText(" Please Find an Attachment ");
//			Multipart multipart = new MimeMultipart();
//			multipart.addBodyPart(EmailBody);
//
//			EmailBody = new MimeBodyPart();
//			
//			// Screen shots attachment as 3rd file
//			Zip zip = new Zip();
//			int ff = new File(prop.getProperty("screenShotsPath") + pdate + File.separator).listFiles().length;
//		
//			/*if (ff >= 1) {
//				String zipFileName = prop.getProperty("screenShotsPath") + pdate + ".zip";
//				zip.zip(new File(prop.getProperty("screenShotsPath") + pdate),
//						new File(zipFileName));
//				System.out.println("screenshots are zipped");
//				MimeBodyPart emailbody3 = new MimeBodyPart();
//				DataSource source2 = new FileDataSource(zipFileName);
//				emailbody3.setDataHandler(new DataHandler(source2));
//				emailbody3.setFileName(zipFileName);
//				multipart.addBodyPart(emailbody3);
//
//				System.out.println("screenshot are attached to mail");
//			} else {
//				System.out.println("screenshots are not attached to Mail body due to No screenshots are available");
//			}
//*/
//			System.out.println("Before SMS");
//			sendSMS();
//			System.out.println("After SMS");
//
//			DataSource source = new FileDataSource(report);
//			EmailBody.setDataHandler(new DataHandler(source));
//			EmailBody.setFileName(report);
//
//			// 2nd file attachment
//			MimeBodyPart emailbody2 = new MimeBodyPart();
//			String filename1 = prop.getProperty("Filename1");
//			DataSource source1 = new FileDataSource(filename1);
//			emailbody2.setDataHandler(new DataHandler(source1));
//			emailbody2.setFileName(filename1);
//
//			multipart.addBodyPart(EmailBody);
//			System.out.println("Report attached");
//			multipart.addBodyPart(emailbody2);
//			System.out.println("output console text file attached");
//			message.setContent(multipart);
//			Transport.send(message);
//			System.out.println("All Report is attached to email body");
//			System.out.println("Mail sent sucessfully ");
//			FileUtils.deleteQuietly(new File(prop.getProperty("screenShotsPath") + DriverScript.pdate + ".zip"));
//			System.out.println("screenshots zip folder removed");
//			System.out.println("Attachments Mail sent sucessfully");
//
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public static void sendSMS() {
//		try {
//
//			String smsUserId = prop.getProperty("smsUserId");
//			String smsPassword = prop.getProperty("smsPassword");
//			String smsSenderID = prop.getProperty("smsSenderID");
//			String smsUrl = prop.getProperty("smsUrl");
//			System.out.println("smsUserId: " + smsUserId);
//			System.out.println("smsPassword: " + smsPassword);
//			System.out.println("smsSenderID: " + smsSenderID);
//			System.out.println("smsUrl: " + smsUrl);
//			String data = "";
//			data += "username=" + smsUserId; // your loginId
//			data += "&pass=" + URLEncoder.encode(smsPassword, "UTF-8"); // your
//																		// password
//			data += "&senderid=" + smsSenderID;
//			data += "&dest_mobileno=" + URLEncoder.encode(prop.getProperty("MobileNo"), "UTF-8"); // a
//																									// valid
//																									// phone
//			// no.
//			data += "&tempid=39405&response=Y&F1=" + totalCount + "&F3=" + passCount + "&F2=" + failCount + " Skip="
//					+ skipCount;
//			int failsms = Integer.parseInt(failCount);
//			int skipsms = Integer.parseInt(skipCount);
//			if (failsms > 0 || skipsms > 0) {
//				System.out.println("data :" + data);
//				URL url = new URL(smsUrl + "?" + data);
//				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//				conn.setRequestMethod("GET");
//				conn.setDoOutput(true);
//				conn.setDoInput(true);
//				conn.setUseCaches(false);
//				conn.connect();
//				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//				String line;
//				StringBuilder buffer = new StringBuilder();
//				while ((line = rd.readLine()) != null) {
//					System.out.println("line :" + line);
//					buffer.append(line).append("\n");
//				}
//				rd.close();
//				conn.disconnect();
//				System.out.println("SMS sent sucessfully");
//			} else {
//				System.out.println("All TestCases are Passed");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//}
package Utilites;
