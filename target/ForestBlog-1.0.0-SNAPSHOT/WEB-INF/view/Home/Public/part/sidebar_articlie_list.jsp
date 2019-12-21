<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2019/12/17
  Time: 下午12:25
  To change this template use File | Settings | File Templates.
--%>
<%--对应sidebar-2.jsp--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="sidebar" class="widget-area all-sidebar"
     style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">
    <aside class="widget ">
        <div class="layui-card">
            <div class="layui-card-header" style="border-bottom-width: 2px">热评文章</div>
            <div class="layui-card-body">
                <ul>
                    <c:forEach items="${mostCommentArticleList}" var="mca">
                        <li>
                            <a href="/article/${mca.articleId}" style="color: #404040;font-size: 15px">${mca.articleTitle}</a>
                            <p style="color: #999;font-size: 12px;">阅读 ${mca.articleViewCount}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </aside>

    <aside  class="widget ">
        <div class="layui-card" >
            <div class="layui-card-header" style="border-bottom-width: 2px">随机推荐</div>
            <div class="layui-card-body">
                <ul>
                    <c:forEach items="${randomArticleList}" var="ra">
                        <li>
                            <a href="/article/${mca.articleId}" style="color: #404040;font-size: 15px">${ra.articleTitle}</a>
                            <p style="color: #999;font-size: 12px;">阅读 ${ra.articleViewCount}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </aside>


</div>
