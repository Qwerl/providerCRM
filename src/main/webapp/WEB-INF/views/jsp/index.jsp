<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>testTask</title>
    <spring:url value="/resources/core/css/newStyles.css" var="coreCss"/>
    <spring:url value="/resources/core/img/search.png" var="searchPng"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" href="${coreCss}" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="sidebar">
        <div class="button-container">
            <a href="/selectProvider">
                <span>Поставщики</span>
            </a>
            <a href="/selectProducer">
                <span>Производители</span>
            </a>
            <form class="search" action="/search" method="post">
                <input name="tag" required>
                <button type="submit"><img src="${searchPng}" width="16"/></button>
            </form>
        </div>
    </div>


    <div class="infoTable">
        <div class="infoBlock" id="block1">
            <div class="header"></div>
            <div class="info"></div>
        </div>
        <div class="infoBlock" id="block2">
            <div class="header"></div>
            <div class="info"></div>
        </div>
        <div class="infoBlock" id="block3">
            <div class="header"></div>
        </div>
    </div>
</div>
</body>
</html>