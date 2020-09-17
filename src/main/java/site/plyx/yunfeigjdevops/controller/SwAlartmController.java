package site.plyx.yunfeigjdevops.controller;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.mail.SimpleMailMessage;
import site.plyx.yunfeigjdevops.model.SwAlarm;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;




import java.util.List;

@Log4j2
@RestController
@RequestMapping("/alarm/skywalking")
public class SwAlartmController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private void initRestTemplate(RestTemplateBuilder builder){
        this.restTemplate=builder.build();
    }

    private RestTemplate restTemplate;

    @Value("${spring.mail.send}")
    private String send_email;

    @Value("${spring.mail.receiver}")
    private String receiver_email;


    /*
    接收告警通知发送到邮箱
     */
    @PostMapping("/smtp")
    public void smtpReceiver(@RequestBody List<SwAlarm> alarmList) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 发送者邮箱
        message.setFrom(send_email);
        // 接收者邮箱
        message.setTo(send_email);
        // 主题
        message.setSubject("skywalking告警邮件");
        String content = getContent(alarmList);
        // 邮件内容
        message.setText(content);
        sender.send(message);
        log.info("告警邮件已发送...");
    }

    /*
    接收告警通知发送到微信
     */
    @PostMapping("/wx")
    public String weiXinReceive(@RequestBody List<SwAlarm> alarmList) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        String wxContent = getContent(alarmList);
        String url = "http://example.com/hotels/1/bookings";
        String reqBody = new StringBuilder()
                .append("{")
                .append("\n    \"msgtype\": \"markdown\",")
                .append("\n    \"markdown\": {")
                .append("\n        \"content\": ").append("\"")
                .append("\n")
                .append(wxContent)
                .append("        \"")
                .append("\n")
                .append("    }")
                .append("\n")
                .append("}").toString();
        System.out.println(reqBody);
        HttpEntity<String> request = new HttpEntity<String>(reqBody, headers);
        String result = restTemplate.postForObject(url, request, String.class);
        log.info("微信消息已发送...");
        return result;
    }

    private String getContent(List<SwAlarm> alarmList) {
        StringBuilder sb = new StringBuilder();
        for (SwAlarm dto : alarmList) {
            sb.append("scopeId: ").append(dto.getScopeId())
                    .append("\nscope: ").append(dto.getScope())
                    .append("\n目标 Scope 的实体名称: ").append(dto.getName())
                    .append("\nScope 实体的 ID: ").append(dto.getId0())
                    .append("\nid1: ").append(dto.getId1())
                    .append("\n告警规则名称: ").append(dto.getRuleName())
                    .append("\n告警消息内容: ").append(dto.getAlarmMessage())
                    .append("\n告警时间: ").append(dto.getStartTime())
                    .append("\n\n---------------\n\n");
        }

        return sb.toString();
    }
}

