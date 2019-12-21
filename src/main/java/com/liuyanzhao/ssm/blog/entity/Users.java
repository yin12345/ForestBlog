package com.liuyanzhao.ssm.blog.entity;

import java.io.Serializable;

public class Users implements Serializable {
    private Integer user1_id;
    private Integer user2_id;

    public Integer getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(Integer user1_id) {
        this.user1_id = user1_id;
    }

    public Integer getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(Integer user2_id) {
        this.user2_id = user2_id;
    }
}
