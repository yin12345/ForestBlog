package com.liuyanzhao.ssm.blog.controller.admin;

import com.liuyanzhao.ssm.blog.entity.Article;
import com.liuyanzhao.ssm.blog.entity.Comment;
import com.liuyanzhao.ssm.blog.entity.User;
import com.liuyanzhao.ssm.blog.service.ArticleService;
import com.liuyanzhao.ssm.blog.service.BannerService;
import com.liuyanzhao.ssm.blog.service.CommentService;
import com.liuyanzhao.ssm.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.liuyanzhao.ssm.blog.util.MyUtils.getIpAddr;

/**
 * @author liuyanzhao
 */
@Controller
public class BannerController {
    @Autowired
    private BannerService bannerService;



    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping("/banner")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }

}
