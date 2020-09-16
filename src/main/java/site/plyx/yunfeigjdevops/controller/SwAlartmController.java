package site.plyx.yunfeigjdevops.controller;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.LinkedMultiValueMap;
import site.plyx.yunfeigjdevops.dao.SwAlartmDTO;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;



import java.io.IOException;

import java.util.List;
import java.util.Map;

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

    @Value("${spring.weiXin.url}")
    private String weiXin_url;

    /*
    接收告警通知发送到邮箱
     */
    @PostMapping("/smtp")
    public void smtpReceiver(@RequestBody List<SwAlartmDTO> alarmList) {
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
    @PostMapping("/weixin")
    public void weiXinReceiver(@RequestBody  List<SwAlartmDTO> alarmList) {

        MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();

    }

    /*
    封装企业微信数据
     */
    private String weiXinContent(String content, boolean isAtAll, List<String> mobileList) {
        //消息内容
        Map<String,String> contentMap = Maps.newHashMap();

        //通知人
        Map<String, Object> atMap = Maps.newHashMap();
        //1.是否通知所有人
        atMap.put("isAll", isAtAll);
        //2.通知具体人的手机号码列表
        atMap.put("atMobiles", mobileList);

        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype", "text");
        reqMap.put("text", contentMap);
        reqMap.put("at", atMap);

        return JSON.toJSONString(reqMap);
    }

    private String getContent(List<SwAlartmDTO> alarmList) {
        StringBuilder sb = new StringBuilder();
        for (SwAlartmDTO dto : alarmList) {
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

