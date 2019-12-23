package com.liuyanzhao.ssm.blog.service;

import com.liuyanzhao.ssm.blog.entity.Banner;
import com.liuyanzhao.ssm.blog.entity.Link;
import com.liuyanzhao.ssm.blog.entity.Users;

import java.util.List;

public interface BannerService {


    /**
     * 根据id查询用户信息
     *
     * @param bannerId 用户ID
     * @return 用户
     */
    Banner getBannerById(Integer bannerId);


    /**
     * 删除用户
     *

     */
    void deleteBanner(Integer bannerId);

    /**
     * 添加用户
     *
     * @param banner 用户
     * @return 用户
     */
    void insertBanner(Banner banner);

    void updateBanner(Banner banner);


    List<Banner> listLink();
}
