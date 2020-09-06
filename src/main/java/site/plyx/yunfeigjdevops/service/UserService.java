package site.plyx.yunfeigjdevops.service;

import java.util.List;
import site.plyx.yunfeigjdevops.model.User;

public interface UserService {
    /**
     * 查找所有用户
     * @return
     */
    List<User> findAll();
}
