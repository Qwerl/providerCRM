<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <span>Теги</span>
            </div>

            <div class="info">
                <div class="button-container">
                    <a href="/tags/addTag" id="special">
                        Добавить
                    </a>
                    <c:forEach items="${tags}" var="tag">
                        <a href="/tags/${tag.id}" <c:if test="${tag.id == tagInfo.id}">id="selected"</c:if>>
                            <c:out value="${tag.tagText}"/>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="infoBlock" id="block2">
            <div class="header"></div>
            <div class="info">
                <c:if test="${not empty tagInfo}">
                    <div class="textInfo">
                        <div class="text-area">
                        <span>Содержание тега:</span><br/>
                        <span>${tagInfo.tagText}</span>
                        </div>
                        <div class="button-container">
                            <a href="/tags/edit/delete/${tagInfo.id}" id="delete">
                                Удалить
                            </a>
                            <a href="/tags/edit/update/${tagInfo.id}">
                                Изменить
                            </a>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <div class="infoBlock" id="block3">
            <div class="header">
                <c:choose>
                    <c:when test="${addingMode}">
                        <span>Добавление</span>
                    </c:when>
                    <c:when test="${editingMode}">
                        <span>Редактирование</span>
                    </c:when>
                </c:choose>
            </div>
            <div class="info">
                <c:choose>
                    <c:when test="${addingMode}">
                        <form:form action="/tags/addTag" method="post" commandName="tagForm">
                            <form:input path="tagText"/><br/>
                            <form:errors path="tagText"/><br/>
                            <button type="submit">Сохранить</button>
                        </form:form>
                    </c:when>
                    <c:when test="${editingMode}">
                        <form:form action="/tags/edit/update/${tagForm.id}" method="post" commandName="tagForm">
                            <form:input path="tagText"/><br/>
                            <form:errors path="tagText"/><br/>
                            <button type="submit">сохранить изменения</button>
                        </form:form>
                    </c:when>
                </c:choose>
                <c:if test="${addingMode}">

                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
