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
                    <a href="javascript:;">
                        <img style="width: 60px; height: 60px"
                             src="${article.user.userAvatar}" class="layui-nav-img">
                    </a>
                </div>

                <div style="float: left; margin: 15px 0px 0px 10px;">
                    <h1 style="font-weight: 900;font-size: 18px;">${article.user.userName}</h1>
                    <div style=" color: #999">
                        <a href="#">文章 100</a> <span class="shu">|</span>
                        <a href="#">关注 100</a>
                    </div>
                </div>

                <div style="float: right;margin-top: 25px;margin-right: 20px ">
                    <form action="watch" method="post">
                        <button type="submit" class="layui-btn layui-btn-normal layui-btn-radius">关注</button>
                    </form>
                </div>
            </div>
        </nav>
        <hr/>
        <div style="margin-left: 10px">
            <ul>
                <c:forEach items="${recentArticleList}" var="ra">
                    <li>
                        <a href="/article/${ra.articleId}" style="color: #404040; font-size: 15px">${ra.articleTitle}</a>
                        <p style="color: #999;font-size: 12px;">阅读 ${ra.articleViewCount}</p>
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
