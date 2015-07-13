<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>testTask</title>
    <spring:url value="/resources/core/css/styles.css" var="coreCss"/>
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

        <p><input type="text" size="10" name="tag" value="${currentTag}"></p>
    </form>

</div>
<div class="infoTable">
    <div class="infoBlock" id="block1">
        <div class="header">
            <p>Поставщик</p>
        </div>
        <div class="info">
            <c:if test="${not empty providerInfo}">
                <p>Данные по поставщику:</p>

                <p><c:out value="${providerInfo.name}"/></p>

                <p><c:out value="${providerInfo.address}"/></p>

                <p><c:out value="${providerInfo.storageAddress}"/></p>

                <p><c:out value="${providerInfo.phoneNumber}"/></p>

                <form action="${pageContext.request.contextPath}/provider/<c:out value="${providerInfo.id}"/>"
                      method="get">
                    <input type="submit" value="Показать полные данные">
                </form>
            </c:if>
        </div>
    </div>

    <div class="infoBlock" id="block2">
        <div class="header">
            <p>Учредительные документы</p>
        </div>
        <div class="info">
            <c:forEach items="${providerInfo.documents}" var="document">
                <p>
                    <a href="${pageContext.request.contextPath}/selectProvider/${providerInfo.id}/documents/<c:out value="${document.id}"/>">
                        <c:out value="${document.name}"/>
                    </a>
                </p>
            </c:forEach>
        </div>
    </div>

    <div class="infoBlock" id="block3">
        <div class="header">
            <p>Добавить</p>
        </div>
        <div class="info">
            <p>

            <form method="POST"
                  action="${pageContext.request.contextPath}/selectProvider/${providerInfo.id}/edit/addDocument"
                  enctype="multipart/form-data"
                  accept-charset="UTF-8">
                Файл для загрузки: <input style="width: 178px;" type="file" name="file">
                <br/><br/>
                Название файла: <input type="text" name="name" >
                <br/><br/>
                <input type="submit" value="Загрузить">
            </form>
            </p>
        </div>
    </div>
</div>
</body>
</html>
