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

    @Autowired
    private BannerService bannerService;


    @RequestMapping(value = {"/", "/article"})
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model) {
        HashMap<String, Object> criteria = new HashMap<>(1);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        //文章列表
        PageInfo<Article> articleList = articleService.pageArticle(pageIndex, pageSize, criteria);
        for (Article article : articleList.getList()) {
            //用户信息
            User user = userService.getUserById(article.getArticleUserId());
            article.setUser(user);
        }
        model.addAttribute("pageInfo", articleList);


        List<Banner> linkList = bannerService.listLink();
        model.addAttribute("linkList",linkList);

        //公告
        List<Notice> noticeList = noticeService.listNotice(NoticeStatus.NORMAL.getValue());
        model.addAttribute("noticeList", noticeList);
//        //友情链接
//        List<Link> linkList = linkService.listLink(LinkStatus.NORMAL.getValue());
//        model.addAttribute("linkList", linkList);

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
        request.setAttribute("articleNum1", articlePageInfo.getList().size());
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
        request.setAttribute("watchNum1", userList.size());
        request.setAttribute("userList", userList);

        //获取粉丝列表
        List<User> fanList = userService.listfan(fan_id);
        System.out.println(fanList);
        request.setAttribute("fanNum1", fanList.size());
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
        request.setAttribute("articleNum1", articlePageInfo.getList().size());
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
        request.setAttribute("watchNum1", userList.size());
        request.setAttribute("userList", userList);

        //获取粉丝列表
        List<User> fanList = userService.listfan(user1_id);
        System.out.println(fanList);
        request.setAttribute("fanNum1", fanList.size());
        request.setAttribute("fanList", fanList);

        return "Home/Page/userDetail";
    }

    //取消关注
    @RequestMapping("/unwatch1")
    public String unwatch1(@RequestParam("articleId") Integer articleId, @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model, HttpServletRequest request) {

        //文章信息，分类，标签，作者，评论
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), articleId);
        if (article == null) {
            return "Home/Error/404";
        }
        HttpSession session = request.getSession();
        Integer fan_id = (Integer) session.getAttribute("id");
        //用户信息
        User user = userService.getUserById(article.getArticleUserId());
        article.setUser(user);
        System.out.println("111111111111");
        //取消关注
        usersService.unwatch(fan_id, user.getUserId());
        //判断是否关注
        Users users = usersService.pan(fan_id, user.getUserId());
        if (users == null) {
            session.setAttribute("watch", 1);
        } else {
            session.setAttribute("watch", 0);
        }

        //获取关注的用户
        List<User> userList = userService.listWatcherUser(user.getUserId());
        System.out.println(userList);
        request.setAttribute("watchNum", userList.size());
        //文章列表
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        criteria.put("userId", user.getUserId());
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        request.setAttribute("articleNum", articlePageInfo.getList().size());


        //文章信息
        model.addAttribute("article", article);

        //评论列表
        List<Comment> commentList = commentService.listCommentByArticleId(articleId);
        model.addAttribute("commentList", commentList);

        //相关文章
        List<Integer> categoryIds = articleService.listCategoryIdByArticleId(articleId);
        List<Article> similarArticleList = articleService.listArticleByCategoryIds(categoryIds, 5);
        model.addAttribute("similarArticleList", similarArticleList);

        //猜你喜欢
//        List<Article> mostViewArticleList = articleService.listArticleByViewCount(5);
//        model.addAttribute("mostViewArticleList", mostViewArticleList);

        //获取下一篇文章
        Article afterArticle = articleService.getAfterArticle(articleId, -1);
        model.addAttribute("afterArticle", afterArticle);

        //获取上一篇文章
        Article preArticle = articleService.getPreArticle(articleId, -1);
        model.addAttribute("preArticle", preArticle);

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        List<Article> recentArticleList = articleService.listRecentArticle(5);
        model.addAttribute("recentArticleList", recentArticleList);


        return "Home/Page/articleDetail";
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
        request.setAttribute("articleNum1", articlePageInfo.getList().size());
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
        request.setAttribute("watchNum1", userList.size());
        request.setAttribute("userList", userList);

        //获取粉丝列表
        List<User> fanList = userService.listfan(user1_id);
        System.out.println(fanList);
        request.setAttribute("fanNum1", fanList.size());
        request.setAttribute("fanList", fanList);

        return "Home/Page/userDetail";
    }

    //关注
    @RequestMapping("/watch1")
    public String watch1(@RequestParam("articleId") Integer articleId, @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                         @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model, HttpServletRequest request) {

        //文章信息，分类，标签，作者，评论
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), articleId);
        if (article == null) {
            return "Home/Error/404";
        }
        HttpSession session = request.getSession();
        Integer fan_id = (Integer) session.getAttribute("id");
        //用户信息
        User user = userService.getUserById(article.getArticleUserId());
        article.setUser(user);
        System.out.println("111111111111");
        //关注

        Integer user1_id = (Integer) session.getAttribute("id");

        Users users1 = usersService.getUsersByuser1_idanduser2_id(fan_id, user.getUserId());

        //判断是否关注
        Users users = usersService.pan(fan_id, user.getUserId());
        if (users == null) {
            session.setAttribute("watch", 1);
        } else {
            session.setAttribute("watch", 0);
        }

        //获取关注的用户
        List<User> userList = userService.listWatcherUser(user.getUserId());
        System.out.println(userList);
        request.setAttribute("watchNum", userList.size());
        //文章列表
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        criteria.put("userId", user.getUserId());
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        request.setAttribute("articleNum", articlePageInfo.getList().size());


        //文章信息
        model.addAttribute("article", article);

        //评论列表
        List<Comment> commentList = commentService.listCommentByArticleId(articleId);
        model.addAttribute("commentList", commentList);

        //相关文章
        List<Integer> categoryIds = articleService.listCategoryIdByArticleId(articleId);
        List<Article> similarArticleList = articleService.listArticleByCategoryIds(categoryIds, 5);
        model.addAttribute("similarArticleList", similarArticleList);

        //猜你喜欢
//        List<Article> mostViewArticleList = articleService.listArticleByViewCount(5);
//        model.addAttribute("mostViewArticleList", mostViewArticleList);

        //获取下一篇文章
        Article afterArticle = articleService.getAfterArticle(articleId, -1);
        model.addAttribute("afterArticle", afterArticle);

        //获取上一篇文章
        Article preArticle = articleService.getPreArticle(articleId, -1);
        model.addAttribute("preArticle", preArticle);

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        List<Article> recentArticleList = articleService.listRecentArticle(5);
        model.addAttribute("recentArticleList", recentArticleList);


        return "Home/Page/articleDetail";
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
        for (Article article : articlePageInfo.getList()) {
            //用户信息
            User user = userService.getUserById(article.getArticleUserId());
            article.setUser(user);
        }
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

}




