package com.record.recordmvc.dao;

import com.record.recordmvc.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserVO,Integer> {

    UserVO findByUsername(String username);

    UserVO getByUsernameAndPassword(String username, String password);
}
