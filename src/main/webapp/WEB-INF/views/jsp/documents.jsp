<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            <div class="header">
                <p>Поставщик</p>
            </div>

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
                            <a href="${pageContext.request.contextPath}/provider/<c:out value="${providerInfo.id}"/>">
                                <span>Показать полные данные</span>
                            </a>
                        </div>
                    </div>
                </c:if>
            </div>

        </div>

        <div class="infoBlock" id="block2">
            <div class="header">
                <p>Учредительные документы</p>
            </div>
            <div class="info">
                <div class="button-container">
                    <c:forEach items="${providerInfo.documents}" var="document">
                        <a href="${pageContext.request.contextPath}/selectProvider/${providerInfo.id}/documents/<c:out value="${document.id}"/>">
                            <c:out value="${document.name}"/>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="infoBlock" id="block3">
            <div class="header">
                <p>Добавить</p>
            </div>
            <div class="info">
                <form method="POST"
                      action="${pageContext.request.contextPath}/selectProvider/${providerInfo.id}/edit/addDocument"
                      enctype="multipart/form-data"
                      accept-charset="UTF-8">
                    <p>
                        Файл для загрузки: <input style="width: 178px;" type="file" name="file">
                        <br/><br/>
                        Название файла: <input type="text" name="name" required>
                        <br/><br/>
                        <input type="submit" value="Загрузить">
                    </p>
                </form>

            </div>
        </div>

    </div>
</div>
</body>
</html>
