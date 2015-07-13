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
        <div class="header">
            <p>Список производителей</p>
        </div>
        <div class="info">
            <p>

            <form action="/producer/add" method="get">
                <input type="submit" value="Добавить"/>
            </form>
            </p>
            <c:forEach items="${producers}" var="producer" varStatus="producerStatus">
                <p>
                <form action="/selectProducer/<c:out value="${producer.id}"/>" method="get">
                    <input type="submit" value="<c:out value="${producer.name}"/>">
                </form>
                </p>
            </c:forEach>
        </div>
    </div>

    <div class="infoBlock" id="block2">
        <div class="header">
            <p>Реквизиты</p>
        </div>
        <div class="info">
            <c:if test="${not empty producerInfo}">
                <p>Данные по производителю:</p>

                <p><c:out value="${producerInfo.name}"/></p>

                <p><c:out value="${producerInfo.address}"/></p>

                <p><c:out value="${producerInfo.phoneNumber}"/></p>

                <p>

                <form action="/producer/<c:out value="${producerInfo.id}"/>" method="get">
                    <input type="submit" value="Показать полные данные">
                </form>
                </p>
            </c:if>
        </div>
    </div>

    <div class="infoBlock" id="block3">
        <div class="header">
            <p>Список поставщиков</p>
        </div>
        <div class="info">
            <c:forEach items="${providerList}" var="provider" varStatus="productStatus">
                <p>

                <form action="/selectProvider/<c:out value="${provider.id}"/>" method="get">
                    <input type="submit" value="<c:out value="${provider.name}"/>">
                </form>
                </p>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>