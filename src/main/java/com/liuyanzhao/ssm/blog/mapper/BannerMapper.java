package com.liuyanzhao.ssm.blog.mapper;

import com.liuyanzhao.ssm.blog.entity.Banner;
import com.liuyanzhao.ssm.blog.entity.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    int delete( Integer bannerId);

    int update(Banner banner);

    int insert(Banner banner);

    Banner getBannerById(Integer bannerId);

    List<Banner> listBanner();


}
