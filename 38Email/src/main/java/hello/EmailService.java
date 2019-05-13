package hello;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@RestController
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration config;

	@RequestMapping("/send2")
	public String send() {

		SimpleMailMessage smm= new SimpleMailMessage();
		smm.setTo("ajendrapanwar2016@gmail.com");
		smm.setSubject("ajendra ka msg");
		smm.setText("000000000000000000000000");
		sender.send(smm);
		return "success";

	}
	
	@RequestMapping("/send")
	public String sendEmail() throws MessagingException, 
	TemplateNotFoundException, MalformedTemplateNameException, 
	ParseException, IOException, TemplateException {
		
		MimeMessage message = sender.createMimeMessage();
		
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment
			helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

			Template t = config.getTemplate("demo.html");
			
			Map<String, Object> model1 = new HashMap<>();
			model1.put("Name", "ajendra mail");
			model1.put("location", "Bangalore,India");
			
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model1);

			helper.setTo("ajendrapanwar2016@gmail.com");
			//String str[]={};
			//helper.setBcc(str);
			helper.setText(html, true);
			helper.setSubject("xxxxxxxxxxx");
			helper.setFrom("ginusiscorp@gmail.com");
			sender.send(message);

			

		

		return "success";
	}
	

}
