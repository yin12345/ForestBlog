package com.liuyanzhao.ssm.blog.mapper;

import com.liuyanzhao.ssm.blog.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    /**
     * 根据ID删除
     *

     * @return 影响行数
     */
    int deleteByuser1anduser2(@Param("user1_id")Integer user1_id, @Param("user2_id")Integer user2_id);

    /**
     * 添加
     *
     * @param users 用户
     * @return 影响行数
     */
    int insert(Users users);



    /**
     * 根据ID查询
     *
     * @param user1_id 用户ID
     * @return 用户
     */
    List<Users> getUsersByuser1_id(Integer user1_id);

    Users getUsersByuser1_idanduser2_id(@Param("user1_id")Integer user1_id, @Param("user2_id")Integer user2_id);

    int update(Users users);

    List<Users> getUsersByuser2_id(Integer user2_id);
}
