package site.plyx.yunfeigjdevops.dao;

import lombok.*;

@Data
public class SwAlartmDTO {
    private Integer scopeId;
    private String scope;
    private String name;
    private Integer id0;
    private Integer id1;
    private String ruleName;
    private String alarmMessage;
    private Long startTime;
}
