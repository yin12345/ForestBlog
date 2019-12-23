package com.liuyanzhao.ssm.blog.controller.home;


import com.alibaba.fastjson.JSON;

import com.github.pagehelper.PageInfo;
import com.liuyanzhao.ssm.blog.entity.*;
import com.liuyanzhao.ssm.blog.enums.ArticleStatus;

import com.liuyanzhao.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章的controller
 *
 * @author 言曌
 * @date 2017/8/24
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UsersService usersService;
    /**
     * 文章详情页显示
     *
     * @param articleId 文章ID
     * @return modelAndView
     */
    @RequestMapping(value = "/article/{articleId}")
    public String getArticleDetailPage(@PathVariable("articleId") Integer articleId,@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                       @RequestParam(required = false, defaultValue = "30") Integer pageSize, Model model, HttpServletRequest request) {

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
        // 此用户文章
        model.addAttribute("userArticleList", articlePageInfo.getList().stream().limit(5).collect(Collectors.toList()));

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
        Article afterArticle = articleService.getAfterArticle(articleId,-1);
        model.addAttribute("afterArticle", afterArticle);

        //获取上一篇文章
        Article preArticle = articleService.getPreArticle(articleId,-1);
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
        return "Home/Page/articleDetail";
    }


    @RequestMapping(value = "/userarticle/{articleId}")
    public String getUserArticleDetailPage(@PathVariable("articleId") Integer articleId, Model model) {

        //文章信息，分类，标签，作者，评论
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), articleId);
        if (article == null) {
            return "Home/Error/404";
        }

        //用户信息
        User user = userService.getUserById(article.getArticleUserId());
        article.setUser(user);

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
        Article afterArticle = articleService.getAfterArticle(articleId,user.getUserId());
        model.addAttribute("afterArticle", afterArticle);

        //获取上一篇文章
        Article preArticle = articleService.getPreArticle(articleId,user.getUserId());
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
        //文章列表
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        criteria.put("userId", user.getUserId());
        PageInfo<Article> articlePageInfo = articleService.pageArticle(1, 100, criteria);
        // 此用户文章
        model.addAttribute("userArticleList", articlePageInfo.getList().stream().limit(5).collect(Collectors.toList()));
        return "Home/Page/userarticleDetail";
    }

        /**
         * 点赞增加
         *
         * @param id 文章ID
         * @return 点赞量数量
         */
    @RequestMapping(value = "/article/like/{id}", method = {RequestMethod.POST})
    @ResponseBody
    public String increaseLikeCount(@PathVariable("id") Integer id) {
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), id);
        Integer articleCount = article.getArticleLikeCount() + 1;
        article.setArticleLikeCount(articleCount);
        articleService.updateArticle(article);
        return JSON.toJSONString(articleCount);
    }

    /**
     * 文章访问量数增加
     *
     * @param id 文章ID
     * @return 访问量数量
     */
    @RequestMapping(value = "/article/view/{id}", method = {RequestMethod.POST})
    @ResponseBody
    public String increaseViewCount(@PathVariable("id") Integer id) {
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), id);
        Integer articleCount = article.getArticleViewCount() + 1;
        article.setArticleViewCount(articleCount);
        articleService.updateArticle(article);
        return JSON.toJSONString(articleCount);
    }
}
