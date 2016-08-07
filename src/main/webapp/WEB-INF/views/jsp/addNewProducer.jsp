<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <title>Создание нового производителя</title>
  <spring:url value="/resources/core/css/styles2.css" var="coreCss"/>
  <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
  <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" href="${coreCss}">
  <link rel="stylesheet" href="${bootstrapCss}">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="${bootstrapJs}"></script>
</head>

<body>

<div class="container">
  <form:form class="form-horizontal" method="post" commandName="producerForm" action=''>
    <h2>Создание нового производителя</h2>
    <table class="table table-striped">
      <tbody>
      <tr>
        <td>Производитель</td>
        <td><form:input class="form-control" placeholder="Введите название" path="name" required="required"/></td>
        <td><form:errors path="name" cssClass="error"/></td>
      </tr>
      <tr>
        <td>Адрес офиса</td>
        <td><form:input class="form-control" placeholder="Введите адрес офиса" path="address" required="required"/></td>
        <td><form:errors path="address" cssClass="error"/></td>
      </tr>
      <tr>
        <td>Общий телефон</td>
        <td><form:input class="form-control" placeholder="Введите телефон" path="phoneNumber" required="required"/></td>
        <td><form:errors path="phoneNumber" cssClass="error"/></td>
      <tr>
        <td>Примечания</td>
        <td><form:textarea rows="5" class="form-control" placeholder="Введите примечания" path="note" required="required"/></td>
        <td><form:errors path="note" cssClass="error"/></td>
      </tr>
      </tbody>
    </table>
    <div class="form-actions">
      <button type="submit" class="btn btn-primary">Сохранить</button>
      <button type="button" onclick="history.back()" class="btn">Отменить</button>
    </div>
  </form:form>
</div>
</body>
</html>