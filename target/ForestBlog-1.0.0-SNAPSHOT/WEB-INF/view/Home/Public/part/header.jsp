<%--
    博客顶部部分
    包括：顶部菜单，主要菜单(包括搜索按钮)，面包屑
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%--导航 start--%>
<header id="masthead" class="site-header">
    <%--主要菜单 satrt--%>
    <div id="menu-box">
        <div id="top-menu" style="width: 1200px">
            <span class="nav-image"   style=" background: transparent;float: right;
                line-height: 26px;
                margin: 28px 10px 0 20px;
                vertical-align: top;
                border: none;
                box-shadow: 0 1px 1px rgba(0, 0, 0, 0);" >
                <c:if test="${sessionScope.user!=null}">
                      <li class="layui-nav-item" lay-unselect="">

                          <a href="user?userId=${sessionScope.user.userId}">
                           <img src="${sessionScope.user.userAvatar}" class="layui-nav-img">${sessionScope.user.userName}
                          </a>
                      </li>

                </c:if>

            </span>
<%--            <span class="nav-search">--%>
<%--                <i class="fa fa-search"></i>--%>
<%--            </span>--%>

            <div class="logo-site"><h1 class="site-title">
                <a href="/" title="${options.optionSiteTitle}">${options.optionSiteTitle}</a>
            </h1>
                <%--                <p class="site-description">${options.optionSiteDescrption}</p>--%>
            </div><!-- .logo-site -->
            <div id="site-nav-wrap">
                <div id="sidr-close">
                    <a href="#sidr-close" class="toggle-sidr-close">×</a>
                </div>
                <nav id="site-nav" class="main-nav">
                    <a href="#sidr-main" id="navigation-toggle" class="bars">
                        <i class="fa fa-bars"></i>
                    </a>
                    <div class="menu-pcmenu-container">
                        <ul id="menu-pcmenu" class="down-menu nav-menu sf-js-enabled sf-arrows">
                            <li style="margin-right: 70px">
                                <form method="get" id="searchform" action="/search" accept-charset="UTF-8">
                                    <div>
                                        <input type="text" name="keywords" id="s"
                                               style="width: 250px;float:left;margin-top: 25px"
                                               placeholder="输入搜索内容" class="layui-input">
                                        <button class="layui-btn" type="submit">搜索</button>
                                    </div>
                                </form>
                            </li>

<%--                            <li>--%>
<%--                                <a href="/">--%>
<%--                                    <i class="fa-home fa"></i>--%>
<%--                                    <span class="font-text">首页</span>--%>
<%--                                </a>--%>
<%--                            </li>--%>

                            <c:forEach items="${allCategoryList}" var="category">
                                <c:if test="${category.categoryPid==0}">
                                    <li>
                                        <a href="/category/${category.categoryId}">
                                            <i class="${category.categoryIcon}"></i>
                                            <span class="font-text">${category.categoryName}&nbsp;</span>
                                        </a>
                                        <ul class="sub-menu">
                                            <c:forEach items="${allCategoryList}" var="cate">
                                                <c:if test="${cate.categoryPid==category.categoryId}">
                                                    <li>
                                                        <a href="/category/${cate.categoryId}"
                                                           target="_blank">${cate.categoryName}</a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <%--主要菜单其余部分--%>
                            <c:forEach items="${menuList}" var="m">
                                <c:if test="${m.menuLevel == 2}">
                                    <li>
                                        <a href="${m.menuUrl}">
                                            <i class="${m.menuIcon}"></i>
                                            <span class="font-text">${m.menuName}&nbsp;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <li>
                                <c:choose>
                                    <c:when test="${sessionScope.user==null}">
                                        <a href="/admin">登录</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/admin">进入后台</a>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                            <li>
                                <a href="javascript:void(0)" onclick="logout()">登出</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="clear"></div>
        </div><!-- #top-menu -->
    </div><!-- #menu-box -->
    <%--主要菜单 satrt--%>

</header>
<!-- #masthead -->
<%--导航 end start--%>

<%--搜索框 start--%>
<%--<div id="search-main">--%>
<%--    <div class="searchbar">--%>
<%--        <form method="get" id="searchform" action="/search" accept-charset="UTF-8">--%>
<%--            <span>--%>
<%--                <input type="text" value="" name="keywords" id="s" placeholder="输入搜索内容"required="">--%>
<%--                <button type="submit" id="searchsubmit">搜索</button>--%>
<%--            </span>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--    <div class="clear"></div>--%>
<%--</div>--%>
<%--搜索框 end--%>

<%--<rapid:block name="breadcrumb"></rapid:block>--%>