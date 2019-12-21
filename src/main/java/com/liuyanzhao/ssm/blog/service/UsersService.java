package com.liuyanzhao.ssm.blog.service;

import com.liuyanzhao.ssm.blog.entity.User;
import com.liuyanzhao.ssm.blog.entity.Users;

import java.util.List;

public interface UsersService {


    /**
     * 根据id查询用户信息
     *
     * @param user1_id 用户ID
     * @return 用户
     */
    List<Users> getUsersByuser1_id(Integer user1_id);

    Users getUsersByuser1_idanduser2_id(Integer user1_id,Integer user2_id);
    /**
     * 删除用户
     *

     */
    void deleteUsers(Integer user1_id,Integer user2_id);

    /**
     * 添加用户
     *
     * @param users 用户
     * @return 用户
     */
    void insertUsers(Users users);

    void updateUsers(Users users);

    void unwatch(Integer user1_id, Integer user2_id);

    Users pan(Integer user1_id, Integer user2_id);
}
