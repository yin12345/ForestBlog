package com.liuyanzhao.ssm.blog.service.impl;
;import com.liuyanzhao.ssm.blog.entity.User;
import com.liuyanzhao.ssm.blog.entity.Users;
import com.liuyanzhao.ssm.blog.mapper.UsersMapper;
import com.liuyanzhao.ssm.blog.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService{

    @Autowired(required = false)
    private UsersMapper usersMapper;



    @Override
    public List<Users> getUsersByuser1_id(Integer user1_id) {
        List<Users> users=new ArrayList<>();
        try {
            users=usersMapper.getUsersByuser1_id(user1_id);
        }catch (Exception e){
            e.printStackTrace();
        }


        return users;
    }

    @Override
    public Users getUsersByuser1_idanduser2_id(Integer user1_id, Integer user2_id) {

        Users users=null;
        try {
            users=usersMapper.getUsersByuser1_idanduser2_id(user1_id,user2_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(users==null){
            users=new Users();
            users.setUser1_id(user1_id);
            users.setUser2_id(user2_id);
            usersMapper.insert(users);
            System.out.println(users.getUser1_id());
            System.out.println(users.getUser2_id());
            users=usersMapper.getUsersByuser1_idanduser2_id(user1_id,user2_id);
        }

        return users;
    }

    @Override
    public void deleteUsers(Integer user1_id,Integer user2_id) {
        usersMapper.deleteByuser1anduser2(user1_id,user2_id);
    }

    @Override
    public void insertUsers(Users users) {
         usersMapper.insert(users);
    }

    @Override
    public void updateUsers(Users users) {
     usersMapper.update(users);
    }

    @Override
    public void unwatch(Integer user1_id, Integer user2_id) {


        try {
            System.out.println(11111111);
         int a=   usersMapper.deleteByuser1anduser2(user1_id,user2_id);
         System.out.println(a);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public Users pan(Integer user1_id, Integer user2_id) {

        Users users=null;
        try {
            users=usersMapper.getUsersByuser1_idanduser2_id(user1_id,user2_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
}
