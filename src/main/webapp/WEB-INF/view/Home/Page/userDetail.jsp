<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<link rel="stylesheet" href="/plugin/layui/css/layui.css"  media="all">

<rapid:override name="title">
    <title>搜索结果</title>
</rapid:override>

<rapid:override name="breadcrumb">
    <%--面包屑导航 start--%>
    <nav class="breadcrumb" style="height: 100px">
        <div style="display: inline">
            <div style="float: left; margin: 10px 0px 0px 10px">
                <a href="javascript:;"><img style="width: 60px; height: 60px" src="//t.cn/RCzsdCq" class="layui-nav-img"></a>

            </div>

            <div style="float: left; margin: 10px 0px 0px 20px">
                <h1 style="margin: 0px 0px 0px 5px;font-weight: 900;font-size: 18px;">小巷</h1>
                <a href="#">文章</a>  <span class="shu">|</span>
                <a href="#">关注</a>  <span class="shu">|</span>
                <a href="#">粉丝</a>  <span class="shu">|</span>
                <a href="#">收获的喜欢</a>  <span class="shu">|</span>
            </div>


            <div style="float: left; margin: 10px 0px 00px 200px">

                <c:if test="${sessionScope.watch==1}">
                    <form action="watch?user2_id=2" method="post">
                        <button type="submit" class="layui-btn">关注</button>
                    </form>
                </c:if>


                <c:if test="${sessionScope.watch==0}">
                    <form action="/unwatch?user2_id=2" method="post">
                        <button type="submit" class="layui-btn">取消关注</button>
                    </form>
                </c:if>
            </div>

        </div>

    </nav>
    <%--面包屑导航 end--%>







    <nav class="breadcrumb" style=" height:1000px; padding: 0px">
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">

            <ul class="layui-tab-title">
                <li class="layui-this">文章</li>
                <li>关注</li>
                <li>粉丝</li>

            </ul>
            <div class="layui-tab-content" style=" height:auto; padding: 0px ">

                <div class="layui-tab-item layui-show" style=" height:auto;">
                        <%--博客主体 start--%>
                    <section id="content" class="site-content shadow" style=" height:auto;">
                            <%--博客主体-左侧正文 start--%>
                        <section id="primary" class="content-area" style=" height:auto;">
                            <main id="main" class="site-main" style=" height:auto;">
                                <c:choose>
                                    <c:when test="${pageInfo.list.size() != 0}">
                                        <%--文章列表-start--%>
                                        <c:forEach items="${pageInfo.list}" var="a">

                                            <article class="post">

                                                <figure class="thumbnail">
                                                    <a href="/article/${a.articleId}">
                                                        <img width="280" height="210"
                                                             src="/img/thumbnail/random/img_${a.articleId%15}.jpg"
                                                             class="attachment-content size-content wp-post-image"
                                                             alt="${a.articleTitle}">
                                                    </a>
                                                    <span class="cat">
                                              <a href="/category/${a.categoryList[0].categoryId}">${a.categoryList[0].categoryName}</a>
                                        </span>
                                                </figure>

                                                <header class="entry-header">
                                                    <h2 class="entry-title">
                                                        <a href="/article/${a.articleId}" rel="bookmark">
                                                                ${a.articleTitle}
                                                        </a>
                                                    </h2>
                                                </header><!-- .entry-header -->

                                                <div class="entry-content">
                                                    <div class="archive-content">
                                                            ${a.articleSummary}...
                                                    </div>
                                                    <span class="title-l"></span>
                                                    <span class="new-icon">
                                                    <c:choose>
                                                        <c:when test="${a.articleStatus == 2}">
                                                            <i class="fa fa-bookmark-o"></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <jsp:useBean id="nowDate"
                                                                         class="java.util.Date"/> <%--当前时间--%>
                                                            <c:set var="interval"
                                                                   value="${nowDate.time - a.articleCreateTime.time}"/><%--时间差毫秒数--%>
                                                            <fmt:formatNumber value="${interval/1000/60/60/24}"
                                                                              pattern="#0"
                                                                              var="days"/><%--取天数整数--%>
                                                            <c:if test="${days <= 7}">NEW</c:if>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </span>
                                                    <span class="entry-meta">
                                                    <span class="date">
                                                         <fmt:formatDate value="${a.articleCreateTime}"
                                                                         pattern="yyyy年MM月dd日"/>
                                                        &nbsp;&nbsp;
                                                    </span>
                                                    <span class="views">
                                                        <i class="fa fa-eye"></i>
                                                            ${a.articleViewCount} views
                                                    </span>
                                                    <span class="comment">&nbsp;&nbsp;
                                                        <a href="/article/${a.articleId}#comments"
                                                           rel="external nofollow">
                                                          <i class="fa fa-comment-o"></i>
                                                            <c:choose>
                                                                <c:when test="${a.articleCommentCount==0}">
                                                                    发表评论
                                                                </c:when>
                                                                <c:otherwise>
                                                                    ${a.articleCommentCount}
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </a>
                                                    </span>
                                                </span>
                                                    <div class="clear"></div>
                                                </div><!-- .entry-content -->
                                                <span class="entry-more">
                                            <a href="/article/${a.articleId}"
                                               rel="bookmark">阅读全文
                                            </a>
                                        </span>
                                            </article>
                                        </c:forEach>
                                        <%--文章列表-end--%>
                                    </c:when>
                                </c:choose>
                            </main>
                            <%@ include file="../Public/part/paging.jsp" %>

                        </section>
                    </section>
                </div>
                <div class="layui-tab-item">


                        <%--关注的用户列表-start--%>
                    <c:forEach items="${requestScope.userList}" var="user">

                    <div style="padding: 20px; margin: 10px 0 0 0; background-color: #F2F2F2;">
                        <div class="layui-row layui-col-space15">
                            <div class="layui-col-md6" style="width: 100%;">
                                <div class="layui-card">

                                    <div class="layui-card-body">
                                        <div style="float: left; margin: 10px 0px 0px 10px">
                                            <a href="javascript:;"><img style="width: 60px; height: 60px" src="//t.cn/RCzsdCq" class="layui-nav-img"></a>
                                        </div>

                                        <div style="float: left; margin: 10px 0px 0px 20px">
                                            <h1 style="margin: 0px 0px 0px 5px;font-weight: 900;font-size: 18px;">${user.userName}</h1>

                                        </div>


                                        <div style="float: right; margin: 10px 50px 0px 200px">
                                            <form action="/unwatch?user2_id=2" method="post">

                                                <button type="submit" class="layui-btn">取消关注</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    </c:forEach>


                </div>
                <div class="layui-tab-item">
                        <%--关注的用户列表-start--%>
                    <c:forEach items="${requestScope.fanList}" var="fan">

                    <div style="padding: 20px; margin: 10px 0 0 0; background-color: #F2F2F2;">
                        <div class="layui-row layui-col-space15">
                            <div class="layui-col-md6" style="width: 100%;">
                                <div class="layui-card">

                                    <div class="layui-card-body">
                                        <div style="float: left; margin: 10px 0px 0px 10px">
                                            <a href="javascript:;"><img style="width: 60px; height: 60px" src="//t.cn/RCzsdCq" class="layui-nav-img"></a>
                                        </div>

                                        <div style="float: left; margin: 10px 0px 0px 20px">
                                            <h1 style="margin: 0px 0px 0px 5px;font-weight: 900;font-size: 18px;">${fan.userName}</h1>

                                        </div>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    </c:forEach>
                </div>

            </div>

        </div>
    </nav>



</rapid:override>



<script src="/plugin/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('element', function(){
        var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {


            tabChange: function(){
                //切换到指定Tab项
                element.tabChange('demo', '22'); //切换到：用户管理
            }
        };

        $('.site-demo-active').on('click', function(){
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });


    });
</script>


<%@ include file="../Public/framework.jsp" %>