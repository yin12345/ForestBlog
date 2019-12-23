package com.liuyanzhao.ssm.blog.service.impl;

import com.liuyanzhao.ssm.blog.entity.Banner;
import com.liuyanzhao.ssm.blog.entity.Link;
import com.liuyanzhao.ssm.blog.entity.Users;
import com.liuyanzhao.ssm.blog.mapper.BannerMapper;
import com.liuyanzhao.ssm.blog.mapper.UsersMapper;
import com.liuyanzhao.ssm.blog.service.BannerService;
import com.liuyanzhao.ssm.blog.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;

@Service
@Slf4j
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Banner getBannerById(Integer bannerId) {
        return bannerMapper.getBannerById(bannerId);
    }

    @Override
    public void deleteBanner(Integer bannerId) {

        bannerMapper.delete(bannerId);
    }

    @Override
    public void insertBanner(Banner banner) {

        bannerMapper.insert(banner);
    }

    @Override
    public void updateBanner(Banner banner) {

        bannerMapper.update(banner);
    }

    @Override
    public List<Banner> listLink() {
        return bannerMapper.listBanner();
    }
}
