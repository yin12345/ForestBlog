<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2019/12/17
  Time: 下午3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="layui-carousel" id="banner" style="margin:0 auto ;padding-bottom: 10px">
    <div carousel-item="">
        <c:forEach var="ll" items="${linkList}">
            <div>
                <a href="${ll.bannerUrl}">
                    <img src="${ll.bannerImg}" style="height: 300px;width: 810px">
                </a>
            </div>
        </c:forEach>
    </div>
</div>