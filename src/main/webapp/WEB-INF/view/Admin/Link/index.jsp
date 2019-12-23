<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
        - 链接列表
    </rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/

        .layui-table {
            margin-top: 0;
        }

        .layui-btn {
            margin: 2px 0!important;
        }
        table td {
                vertical-align:middle;
                text-align:center;
        }
    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a><cite>横幅列表</cite></a>
        </span>
    </blockquote>

    <table class="layui-table" >
        <colgroup>
            <col width="10%">
            <col width="20%">
            <col width="25%">
            <col width="20%">
            <col width="25%">
        </colgroup>
        <thead>
        <tr>
            <th>ID</th>
            <th>名称</th>
            <th>URL</th>
            <th>图片</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${linkList}" var="l">
            <tr>
                <td>${l.bannerId}</td>
                <td>
                    ${l.bannerName}
                </td>
                <td >
                    <a href="${l.bannerUrl}" target="_blank">${l.bannerUrl}</a>
                </td>
                <td>
                    <img src="${l.bannerImg}">
                </td>
                <td>
                    <a href="/admin/link/edit/${l.bannerId}" class="layui-btn layui-btn-mini">编辑</a>
                    <a href="/admin/link/delete/${l.bannerId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>





</rapid:override>
<rapid:override name="footer-script">
    <script>

    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp"%>
