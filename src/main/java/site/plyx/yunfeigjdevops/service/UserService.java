package site.plyx.yunfeigjdevops.service;

import org.springframework.stereotype.Service;
import site.plyx.yunfeigjdevops.mapper.UserMapper;
import site.plyx.yunfeigjdevops.model.User;

import javax.annotation.Resource;

/**
 * 后台用具管理
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询
     * @parms id
     * @return
     */

    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 新增用户
     * @param user
     */
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    /**
     * 修改用户
     * @param user
     */
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }
}
