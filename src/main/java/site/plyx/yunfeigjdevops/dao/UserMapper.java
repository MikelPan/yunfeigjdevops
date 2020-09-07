package site.plyx.yunfeigjdevops.dao;

import org.apache.ibatis.annotations.Mapper;
import site.plyx.yunfeigjdevops.model.User;
import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查寻全部用户
     * @return
     */
    List<User> findAll();
}