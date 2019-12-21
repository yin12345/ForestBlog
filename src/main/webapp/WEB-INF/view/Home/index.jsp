<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>


<%--<rapid:override name="breadcrumb">--%>
<%--<nav class="breadcrumb">--%>
<%--&lt;%&ndash;<div class="bull"><i class="fa fa-volume-up"></i></div>&ndash;%&gt;--%>
<%--<div id="scrolldiv">--%>
<%--&lt;%&ndash;<div class="scrolltext">&ndash;%&gt;--%>
<%--&lt;%&ndash;<ul style="margin-top: 0px;">&ndash;%&gt;--%>
<%--&lt;%&ndash;<c:forEach items="${noticeList}" var="n">&ndash;%&gt;--%>
<%--&lt;%&ndash;<li class="scrolltext-title">&ndash;%&gt;--%>
<%--&lt;%&ndash;<a href="/notice/${n.noticeId}" rel="bookmark">${n.noticeTitle}</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;</li>&ndash;%&gt;--%>
<%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--</div>--%>
<%--</nav>--%>
<%--</rapid:override>--%>


<rapid:override name="left">
    <div id="primary" style="width: 70%" class="content-area">

        <main id="main" class="site-main" role="main">
            <%@ include file="Public/part/banner.jsp" %>
            <c:forEach items="${pageInfo.list}" var="a">
                <article class="post type-post">

                    <figure class="thumbnail" style="max-width: 220px;">
                        <a href="/article/${a.articleId}">
                            <img width="480" height="210"
                                 src="/img/thumbnail/random/img_${a.articleId%15}.jpg"
                                 class="attachment-content size-content wp-post-image"
                                 alt="${a.articleTitle}">
                        </a>
                    </figure>

                    <header class="entry-header">
                        <h2 class="entry-title">
                            <a href="/article/${a.articleId}"
                               rel="bookmark">
                                    ${a.articleTitle}
                            </a>
                        </h2>
                    </header>


                    <div class="entry-content">

                        <div class="archive-content">
                                ${a.articleSummary}...
                        </div>
                            <%--                        <span class="title-l"></span>--%>
                            <%--                        <span class="new-icon">--%>
                            <%--                            <c:choose>--%>
                            <%--                                <c:when test="${a.articleStatus == 2}">--%>
                            <%--                                    <i class="fa fa-bookmark-o"></i>--%>
                            <%--                                </c:when>--%>
                            <%--                                <c:otherwise>--%>
                            <%--                                    <jsp:useBean id="nowDate" class="java.util.Date"/>--%>
                            <%--                                    <c:set var="interval"--%>
                            <%--                                           value="${nowDate.time - a.articleCreateTime.time}"/>&lt;%&ndash;时间差毫秒数&ndash;%&gt;--%>
                            <%--                                    <fmt:formatNumber value="${interval/1000/60/60/24}" pattern="#0"--%>
                            <%--                                                      var="days"/>--%>
                            <%--                                    <c:if test="${days <= 7}">NEW</c:if>--%>
                            <%--                                </c:otherwise>--%>
                            <%--                            </c:choose>--%>
                            <%--                        </span>--%>

                        <span class="entry-meta">
                            <span class="avatar">
                                  <a href="/user?userId=${a.user.userId}">
                                    <img src="${a.user.userAvatar}"
                                         style="width : 40px; height: 40px;vertical-align: middle;border-radius: 50%">
                                </a>
                            </span>
                            <span style="margin-left: 3px;margin-right: 20px;font-size: 120%;">
                                  <a href="/user?userId=${a.user.userId}">
                                    ${a.user.userName}
                                </a>
                            </span>
                            <span class="date">
                                <fmt:formatDate value="${a.articleCreateTime}" pattern="yyyy年MM月dd日"/>
                            </span>
                            <span class="views">
                                <i class="fa fa-eye"></i>
                                    ${a.articleViewCount} views
                            </span>
                            <span class="cat" style="margin-left: 10px">
                                <a href="/category/${a.categoryList[a.categoryList.size()-1].categoryId}">
                                        ${a.categoryList[a.categoryList.size()-1].categoryName}
                                </a>
                            </span>
                            <span class="comment">&nbsp;&nbsp;
                                <a href="/article/${a.articleId}#comments" rel="external nofollow">
                                  <i class="fa fa-comment-o"></i>
                                    <c:choose>
                                        <c:when test="${a.articleCommentCount == 0}">
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

                        <%--                    <span class="entry-more">--%>
                        <%--                        <a href="/article/${a.articleId}"--%>
                        <%--                           rel="bookmark">--%>
                        <%--                            阅读全文--%>
                        <%--                        </a>--%>
                        <%--                    </span>--%>
                </article>
            </c:forEach>
        </main>
        <%@ include file="Public/part/paging.jsp" %>

    </div>
</rapid:override>
<%--左侧区域 end--%>

<%--侧边栏 start--%>
<rapid:override name="right">
<%--<%@include file="Public/part/sidebar-2.jsp" %>--%>
<%@include file="Public/part/sidebar_articlie_list.jsp" %>
</rapid:override>
<%--侧边栏 end--%>


<%@ include file="Public/framework.jsp" %>