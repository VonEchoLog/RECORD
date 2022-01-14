package com.record.recordmvc.service;

import com.record.recordmvc.dao.UserDao;
import com.record.recordmvc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public boolean isExist(String username) {
        UserVO user = getByName(username);
        return null!=user;
    }

    public UserVO getByName(String username) {
        return userDao.findByUsername(username);
    }

    public UserVO get(String username, String password){
        return userDao.getByUsernameAndPassword(username, password);
    }

    public void add(UserVO user) {
        userDao.save(user);
    }


}
