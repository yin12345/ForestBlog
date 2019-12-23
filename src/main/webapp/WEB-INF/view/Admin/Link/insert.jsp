<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
        - 添加横幅
    </rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-input-block {
            margin:0px 10px;
        }
        .layui-table {
            margin-top: 0;
        }
        .layui-col-md4 {
            padding:10px;
        }
        .layui-col-md8 {
            padding:10px;
        }
        .layui-btn {
            margin: 2px 0!important;
        }
    </style>
</rapid:override>

<rapid:override name="content">


    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/link">横幅列表</a>
              <a><cite>添加横幅</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4">
            <form class="layui-form" method="post" id="myForm" action="/admin/link/insertSubmit">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <strong>编辑横幅</strong>
                    </div>
                    <input type="hidden" name="banerId" value="">
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="bannerName" value="" autocomplete="off" class="layui-input" required>
                    </div>
                    <div class="layui-input-inline" style="margin: 10px 0 10px 10px; float: none">
                        <div class="layui-upload">
                               横幅图片 <span style="color: #FF5722; ">*</span></label>

                            <div class="layui-upload-list" >
                                <img class="layui-upload-img"  id="demo1" width="300"
                                     height="150" src=""/>
                                <p id="demoText"></p>
                                <button type="button" class="layui-btn" id="test1">上传图片</button>
                            </div>
                        </div>
                        <input type="hidden" name="bannerImg" id="articleImg" value="">
                    </div>
                    <br>

                    <div class="layui-input-block">
                        URL <span style="color: #FF5722; ">*</span>
                        <input type="text" name="bannerUrl" value="" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>

                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">添加</button>
                    </div>
                </div>
            </form>

        </div>
<%--        <div class="layui-col-md8">--%>
<%--            <table class="layui-table" >--%>
<%--                <colgroup>--%>
<%--                    <col width="50">--%>
<%--                    <col width="300">--%>
<%--                    <col width="100">--%>
<%--                    <col width="50">--%>
<%--                    <col width="100">--%>
<%--                </colgroup>--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>id</th>--%>
<%--                    <th>名称</th>--%>
<%--                    <th>URL</th>--%>

<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach items="${linkList}" var="l">--%>

<%--                    <tr>--%>
<%--                        <td>${l.bannerId}</td>--%>
<%--                        <td>--%>
<%--                            ${l.bannerName}--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                             ${l.bannerUrl}--%>
<%--                        </td>--%>

<%--                    </tr>--%>

<%--                </c:forEach>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
    </div>






</rapid:override>
<rapid:override name="footer-script">

    <script>
        //上传图片
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
            var uploadInst = upload.render({
                elem: '#test1',
                url: '/admin/upload/img',
                before: function (obj) {
                    console.log(obj);
                    obj.preview(function (index, file, result) {
                        $('#demo1').attr('src', result);
                    });
                },
                done: function (res) {
                    $("#articleImg").attr("value", res.data.src);
                    if (res.code > 0) {
                        return layer.msg('上传失败');
                    }
                },
                error: function () {
                    var demoText = $('#demoText');
                    demoText.html('' +
                        '<span style="color: #FF5722;">上传失败</span>' +
                        ' <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function () {
                        uploadInst.upload();
                    });
                }
            });

        });

    </script>

</rapid:override>


<%@ include file="../Public/framework.jsp"%>
