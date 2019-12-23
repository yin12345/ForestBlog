<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2019/12/22
  Time: 上午10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>注册页面</title>
    <script src="/plugin/layui/layui.all.js"></script>
    <link rel="stylesheet" href="/plugin/layui/css/layui.css" media="all">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <style>
        html, body {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
            background: #f1f1f1;
        }

        #userForm {
            background: #fff;
            background: rgba(255, 255, 255, 0.6);
            border-radius: 2px;
            border: 1px solid #fff;
        }
        #userForm input{
            width: 240px;
        }
        #userForm p{
            padding-left: 48px;
        }
        #userForm h1 a {
            background-size: 220px 50px;
            width: 220px;
            height: 50px;
            padding: 0;
            margin: 0 auto 1em;
        }
        #userForm form {
            background: #fff;
            background: rgba(255, 255, 255, 0.6);
            border-radius: 2px;
            border: 1px solid #fff;
        }
        #userForm label {
            color: #000;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div style="width:100%;height:100%;
            border:0 ;
            position:relative;">
    <p id="backtoblog" style="padding:30px 0 0 100px"><a style="color: black;font-size: 20px;;" href="/">博客管理系统</a></p>
    <div style="width: 340px;height: 440px;
                 margin:auto;
                 border:0 ;
                 position:absolute;
                 top:0;left:0;bottom:0;right:0;">
        <form class="layui-form" action="/registerUser" id="userForm"
              method="post" style="padding-top: 20px;padding-bottom: 20px">
            <p>
                <label for="userName">用户名<br/>
                    <input type="text" name="userName" id="userName" required
                           lay-verify="userName"
                           autocomplete="off" class="layui-input" onblur="checkUserName()" >
                    <div class="layui-form-mid layui-word-aux" id="userNameTips"></div>
                </label>
            </p>
            <p>
                <label for="userPass">密码<br/>
                    <input type="password" name="userPass" id="userPass" required
                           lay-verify="userPass"
                           autocomplete="off" class="layui-input" min="3" max="20">
                    <div class="layui-form-mid layui-word-aux"></div>
                </label>
            </p>
            <p>
                <label for="userNickname">昵称<br/>
                    <input type="text" name="userNickname" id="userNickname" required
                           placeholder="" autocomplete="off" min="2" max="10"
                           class="layui-input">
                    <div class="layui-form-mid layui-word-aux"></div>
                </label>
            </p>
            <p>
                <label for="userEmail">Email<br/>
                    <input type="email" name="userEmail" id="userEmail" required
                           lay-verify="userEmail"
                           class="layui-input" onblur="checkUserEmail()">
                    <div class="layui-form-mid layui-word-aux" id="userEmailTips"></div>
                </label>
            </p>
            <p>
                <label for="input-block">用户类型<br/>
                    <div class="layui-input-block" id="input-block">
                        <input type="radio" name="userPermission" value="1" title="管理员" checked="">
                        <input type="radio" name="userPermission" value="0" title="普通用户">
                    </div>
                </label>
            </p>

            <p>
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="demo1" id="submit-btn">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </p>
        </form>
    </div>
</div>

<script src="/js/jquery.min.js"></script>
<script src="/plugin/layui/layui.all.js"></script>
<script src="/js/back.js"></script>
</body>
</html>
