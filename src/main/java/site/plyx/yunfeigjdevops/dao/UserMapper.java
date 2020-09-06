package site.plyx.yunfeigjdevops.dao;

import site.plyx.yunfeigjdevops.model.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查找所有用户
     * @return
     */
    List<User> findAll();
}