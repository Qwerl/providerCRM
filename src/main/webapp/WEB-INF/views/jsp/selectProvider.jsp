<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>testTask</title>
    <spring:url value="/resources/core/css/newStyles.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" href="${coreCss}" rel="stylesheet"/>
</head>

<body>
<div class="container">

    <div class="sidebar">
        <div class="button-container">
            <a href="/selectProvider" id="selected">
                <span>Поставщики</span>
            </a>
            <a href="/selectProducer">
                <span>Производители</span>
            </a>
            <a href="/search">
                <span>Поиск</span>
            </a>

            <form class="search" action="/search" method="post">
                <input name="tag" required>
                <button type="submit">O</button>
            </form>
        </div>
    </div>

    <div class="infoTable">
        <div class="infoBlock" id="block1">
            <div class="header">Список поставщиков</div>
            <div class="info">
                <div class="button-container">
                    <form action="/provider/add" method="get">
                        <input type="submit" value="Добавить"/>
                    </form>
                    <br/>

                    <c:forEach items="${providers}" var="provider" varStatus="providerStatus">
                        <a href="/selectProvider/<c:out value="${provider.id}"/>"
                           <c:if test="${provider.id == providerInfo.id}">id="selected"</c:if> >
                            <span><c:out value="${provider.name}"/></span>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="infoBlock" id="block2">
            <div class="header">Реквизиты</div>
            <div class="info">

                <c:if test="${not empty providerInfo}">
                    <div class="textInfo">

                        <div class="text-area">
                            <p>Данные по поставщику:</p><br/>

                            <p>Название:<br/>
                                <c:out value="${providerInfo.name}"/>
                            </p><br/>

                            <p>Адрес офиса:<br/>
                                <c:out value="${providerInfo.address}"/>
                            </p><br/>

                            <p>Адрес склада:<br/>
                                <c:out value="${providerInfo.storageAddress}"/>
                            </p><br/>

                            <p>Контактные данные:<br/>
                                <c:out value="${providerInfo.phoneNumber}"/>
                            </p><br/>
                        </div>

                        <div class="button-container">
                            <a href="/selectProvider/<c:out value="${providerInfo.id}"/>/documents">
                                <span>К списку учредительных документов</span>
                            </a>
                            <a href="/provider/<c:out value="${providerInfo.id}"/>">
                                <span>Показать полные данные</span>
                            </a>
                        </div>

                    </div>
                </c:if>
            </div>
        </div>

        <div class="infoBlock" id="block3">
            <div class="header">Поставляемое оборудование</div>
            <div class="info">
                <div class="button-container">
                    <c:forEach items="${productList}" var="product" varStatus="productStatus">
                        <a><span><c:out value="${product.name}"/></span></a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>