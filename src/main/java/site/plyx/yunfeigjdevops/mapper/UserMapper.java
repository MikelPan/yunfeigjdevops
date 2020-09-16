package site.plyx.yunfeigjdevops.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.plyx.yunfeigjdevops.model.User;


public interface UserMapper extends BaseMapper<User> {

    User findUserByName(String name);
}
