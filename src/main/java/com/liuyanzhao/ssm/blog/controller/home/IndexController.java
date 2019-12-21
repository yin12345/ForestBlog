package com.liuyanzhao.ssm.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.liuyanzhao.ssm.blog.entity.Link;

import com.liuyanzhao.ssm.blog.enums.ArticleStatus;
import com.liuyanzhao.ssm.blog.enums.LinkStatus;
import com.liuyanzhao.ssm.blog.enums.NoticeStatus;

import com.liuyanzhao.ssm.blog.entity.*;
import com.liuyanzhao.ssm.blog.service.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 用户的controller
 *
 * @author 言曌
 * @date 2017/8/24
 */
@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = {"/", "/article"})
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model) {
        HashMap<String, Object> criteria = new HashMap<>(1);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        //文章列表
        PageInfo<Article> articleList = articleService.pageArticle(pageIndex, pageSize, criteria);
        new Thread(() -> {
            for (Article article : articleList.getList()) {
                //用户信息
                User user = userService.getUserById(article.getArticleUserId());
                article.setUser(user);
            }
        }).start();
        model.addAttribute("pageInfo", articleList);


        //公告
        List<Notice> noticeList = noticeService.listNotice(NoticeStatus.NORMAL.getValue());
        model.addAttribute("noticeList", noticeList);
        //友情链接
        List<Link> linkList = linkService.listLink(LinkStatus.NORMAL.getValue());
        model.addAttribute("linkList", linkList);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList", recentCommentList);
        model.addAttribute("pageUrlPrefix", "/article?pageIndex");

        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(5);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(5);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        return "Home/index";
    }

    @RequestMapping(value = "user")
    public String user(@RequestParam("userId") Integer userId,
                       @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                       @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Integer fan_id = (Integer) session.getAttribute("id");

        System.out.println("111111111111");
        //差当前用户`
        User currentUser = userService.getUserById(userId);
        model.addAttribute("currentUser", currentUser);

        //判断是否关注
        Users users = usersService.pan(fan_id, userId);
        if (users == null) {
            session.setAttribute("watch", 1);
        } else {
            session.setAttribute("watch", 0);
        }
        //文章列表
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        criteria.put("userId", userId);
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList", recentCommentList);
        model.addAttribute("pageUrlPrefix", "/search?pageIndex");


        //获取关注的用户
        List<User> userList = userService.listWatcherUser(userId);
        System.out.println(userList);
        request.setAttribute("userList", userList);

        //获取粉丝列表
        List<User> fanList = userService.listfan(fan_id);
        System.out.println(fanList);
        request.setAttribute("fanList", fanList);

        System.out.println("2222222");
        return "Home/Page/userDetail";
    }

    //取消关注
    @RequestMapping("unwatch")
    public String unwatch(@RequestParam("user2_id") Integer user2_id, @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                          @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model, HttpServletRequest request) {


        HttpSession session = request.getSession();
        Integer user1_id = (Integer) session.getAttribute("id");

        //差当前用户`
        User currentUser = userService.getUserById(user2_id);
        model.addAttribute("currentUser", currentUser);

        //取消关注
        usersService.unwatch(user1_id, user2_id);

        //判断是否关注
        Users users = usersService.pan(user1_id, user2_id);
        if (users == null) {
            session.setAttribute("watch", 1);
        } else {
            session.setAttribute("watch", 0);
        }


        //文章列表
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        criteria.put("userId", user2_id);
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList", recentCommentList);
        model.addAttribute("pageUrlPrefix", "/search?pageIndex");

        //获取关注的用户
        List<User> userList = userService.listWatcherUser(user2_id);
        System.out.println(userList);
        request.setAttribute("userList", userList);

        //获取粉丝列表
        List<User> fanList = userService.listfan(user1_id);
        System.out.println(fanList);
        request.setAttribute("fanList", fanList);

        return "Home/Page/userDetail";
    }

    //关注
    @RequestMapping(value = "watch")
    public String watch(@RequestParam("user2_id") Integer user2_id, HttpServletRequest request, @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model) {
        //差当前用户`
        User currentUser = userService.getUserById(user2_id);
        model.addAttribute("currentUser", currentUser);

        //关注
        HttpSession session = request.getSession();
        Integer user1_id = (Integer) session.getAttribute("id");
        System.out.println(user1_id);
        System.out.println(user2_id);
        Users users = usersService.getUsersByuser1_idanduser2_id(user1_id, user2_id);
        System.out.println(users);

        //判断是否关注
        Users users1 = usersService.pan(user1_id, user2_id);
        if (users1 == null) {
            session.setAttribute("watch", 1);
        } else {
            session.setAttribute("watch", 0);
        }

        //文章列表
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        criteria.put("userId", user2_id);
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList", recentCommentList);
        model.addAttribute("pageUrlPrefix", "/search?pageIndex");

        //获取关注的用户
        List<User> userList = userService.listWatcherUser(user2_id);
        System.out.println(userList);
        request.setAttribute("userList", userList);

        //获取粉丝列表
        List<User> fanList = userService.listfan(user1_id);
        System.out.println(fanList);
        request.setAttribute("fanList", fanList);

        return "Home/Page/userDetail";
    }

    @RequestMapping(value = "/search")
    public String search(
            @RequestParam("keywords") String keywords,
            @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model) {
        //文章列表
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        criteria.put("keywords", keywords);
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        new Thread(() -> {
            for (Article article : articlePageInfo.getList()) {
                //用户信息
                User user = userService.getUserById(article.getArticleUserId());
                article.setUser(user);
            }
        }).start();
        model.addAttribute("pageInfo", articlePageInfo);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList", recentCommentList);
        model.addAttribute("pageUrlPrefix", "/search?pageIndex");
        return "Home/Page/search";
    }

    @RequestMapping("/404")
    public String NotFound(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "Home/Error/404";
    }

    @RequestMapping("/500")
    public String ServerError(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "Home/Error/500";
    }

    @RequestMapping("/fix")
    public String FixBlock(Model model) {
        return "Home/Page/fix";
    }

}




