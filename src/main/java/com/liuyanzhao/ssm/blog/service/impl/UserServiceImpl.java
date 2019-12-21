package com.liuyanzhao.ssm.blog.service.impl;

import com.liuyanzhao.ssm.blog.entity.Users;
import com.liuyanzhao.ssm.blog.mapper.ArticleMapper;
import com.liuyanzhao.ssm.blog.mapper.UserMapper;
import com.liuyanzhao.ssm.blog.entity.User;
import com.liuyanzhao.ssm.blog.mapper.UsersMapper;
import com.liuyanzhao.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 *
 * @author 言曌
 * @date 2017/8/24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private UsersMapper usersMapper;


    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Override
    public List<User> listUser() {
        List<User> userList = userMapper.listUser();
        for (int i = 0; i < userList.size(); i++) {
            Integer articleCount = articleMapper.countArticleByUser(userList.get(i).getUserId());
            userList.get(i).setArticleCount(articleCount);
        }
        return userList;
    }
    @Override
    public List<User> listWatcherUser(Integer user1_id) {
        List<Users> usersList = usersMapper.getUsersByuser1_id(user1_id);
        System.out.println(usersList);
        List<User> userList =new ArrayList();
        for (int i = 0; i < usersList.size(); i++) {
            Integer userId =usersList.get(i).getUser2_id();
            User user=userMapper.getUserById(userId);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(User user) {

        userMapper.update(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public User insertUser(User user) {
        user.setUserRegisterTime(new Date());
        userMapper.insert(user);
        return user;
    }

    @Override
    public User getUserByNameOrEmail(String str) {
        return userMapper.getUserByNameOrEmail(str);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public List<User> listfan(Integer user2_id) {
        List<Users> usersList = usersMapper.getUsersByuser2_id(user2_id);
        System.out.println(usersList);
        List<User> userList =new ArrayList();
        for (int i = 0; i < usersList.size(); i++) {
            Integer userId =usersList.get(i).getUser1_id();
            User user=userMapper.getUserById(userId);
            userList.add(user);
        }
        return userList;
    }


}
