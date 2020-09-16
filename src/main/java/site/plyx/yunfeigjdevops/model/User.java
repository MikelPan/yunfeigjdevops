package site.plyx.yunfeigjdevops.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
@TableName("user_info")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    private String gender;
    private Integer age;
    private Integer phone;
    private String register_mode;
    private Integer third_party_id;
}
