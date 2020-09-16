//package site.plyx.yunfeigjdevops.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import site.plyx.yunfeigjdevops.model.User;
//import site.plyx.yunfeigjdevops.mapper.UserMapper;
//
//@service
//@Transactional
//public class UserService {
//    /**
//     * 查找所有用户
//     * @return
//     */
//
//    @Autowired
//    private UserMapper userMapper;
//
//    public List<User> list() {
//        return UserMapper.selectList(new QueryWrapper<>());
//    }
//
//}
