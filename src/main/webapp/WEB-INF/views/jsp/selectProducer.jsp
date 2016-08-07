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
      <a href="/selectProducer" id="selected">
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
        <p>Список производителей</p>
      </div>
      <div class="info">

        <div class="button-container">
          <form action="/producer/add" method="get">
            <input type="submit" value="Добавить"/>
          </form>
          <br/>

          <c:forEach items="${producers}" var="producer">
            <a href="/selectProducer/<c:out value="${producer.id}"/>"
               <c:if test="${producer.id == producerInfo.id}">id="selected"</c:if>>
              <span><c:out value="${producer.name}"/></span>
            </a>
          </c:forEach>
        </div>
      </div>
    </div>

    <div class="infoBlock" id="block2">
      <div class="header">
        <p>Реквизиты</p>
      </div>
      <div class="info">
        <c:if test="${not empty producerInfo}">
          <div class="textInfo">
            <div class="text-area">
              <p>Данные по производителю:</p><br/>

              <p>Название:<br/>
                <c:out value="${producerInfo.name}"/>
              </p><br/>

              <p>Адрес офиса:<br/>
                <c:out value="${producerInfo.address}"/>
              </p><br/>

              <p>Адрес склада:<br/>
                <c:out value="${producerInfo.phoneNumber}"/>
              </p><br/>
            </div>
            <div class="button-container">
              <a href="/producer/<c:out value="${producerInfo.id}"/>">
                <span>Показать полные данные</span>
              </a>
            </div>
          </div>
        </c:if>
      </div>
    </div>

    <div class="infoBlock" id="block3">
      <div class="header">Список поставщиков</div>
      <div class="info">
        <div class="button-container">
          <c:forEach items="${providerList}" var="provider" varStatus="productStatus">
            <a href="/selectProvider/<c:out value="${provider.id}"/>">
              <span><c:out value="${provider.name}"/></span>
            </a>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>