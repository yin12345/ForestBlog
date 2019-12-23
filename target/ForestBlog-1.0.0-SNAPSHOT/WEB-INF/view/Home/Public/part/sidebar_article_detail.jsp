<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2019/12/18
  Time: 下午9:08
  To change this template use File | Settings | File Templates.
--%>
<%--对应sidebar-2.jsp--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="sidebar" class="widget-area all-sidebar"
     style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">

    <aside class="widget">
        <nav style="height: 75px; ">
            <div style="display: inline;">
                <div style="float: left; margin: 10px 0px 0px 15px">
                    <a href="/user?userId=${article.user.userId}">
                        <img style="width: 60px; height: 60px"
                             src="${article.user.userAvatar}" class="layui-nav-img">
                    </a>
                </div>

                <div style="float: left; margin: 15px 0px 0px 10px;">
                    <h1 style="font-weight: 900;font-size: 18px;">${article.user.userName}</h1>
                    <div style=" color: #999">
                        <a href="javascript:void(0);">文章 ${requestScope.articleNum}</a> <span class="shu">|</span>
                        <a href="#javascript:void(0);">关注 ${requestScope.watchNum}</a>
                    </div>
                </div>

                <div style="float: right;margin-top: 25px;margin-right: 20px ">
                    <c:if test="${sessionScope.watch==1&&sessionScope.id!=null}">
                        <form action="/watch1?articleId=${article.articleId}" method="post">
                            <button type="submit" class="layui-btn">关注</button>
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.watch==0&&sessionScope.id!=null}">
                        <form action="/unwatch1?articleId=${article.articleId}" method="post">
                            <button type="submit" class="layui-btn">取消关注</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </nav>
        <hr/>
        <div style="margin-left: 10px">
            <ul>
                <c:forEach items="${userArticleList}" var="ua">
                    <li>
                        <a href="/article/${ua.articleId}" style="color: #404040; font-size: 15px">${ua.articleTitle}</a>
                        <p style="color: #999;font-size: 12px;">阅读 ${ua.articleViewCount}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </aside>

    <aside class="widget ">
        <div class="layui-card">
            <div class="layui-card-header" style="border-bottom-width: 2px">相关链接</div>
            <div class="layui-card-body">
                <ul>
                    <c:forEach items="${similarArticleList}" var="sa">
                        <li>
                            <a href="/article/${sa.articleId}" style="color: #404040;font-size: 15px">${sa.articleTitle}</a>
                            <p style="color: #999;font-size: 12px;">阅读 ${sa.articleViewCount}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </aside>

</div>
