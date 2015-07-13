<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>testTask</title>
    <spring:url value="/resources/core/css/styles.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" href="${coreCss}" rel="stylesheet"/>
</head>

<body>
<div class="sidebar">
    <form action="/selectProvider" method="get">
        <input type="submit" value="Поставщики"/>
    </form>
    <br/>

    <form action="/selectProducer" method="get">
        <input type="submit" value="Производители"/>
    </form>
    <br/>

    <form action="/search" method="post">
        <p><input type="submit" value="Поиск"></p>

        <p><input type="text" size="10" name="tag"></p>
    </form>
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

</body>
</html>