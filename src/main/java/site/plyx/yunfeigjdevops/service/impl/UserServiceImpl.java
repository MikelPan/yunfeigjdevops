package site.plyx.yunfeigjdevops.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.plyx.yunfeigjdevops.dao.UserMapper;
import site.plyx.yunfeigjdevops.model.User;
import site.plyx.yunfeigjdevops.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

}
