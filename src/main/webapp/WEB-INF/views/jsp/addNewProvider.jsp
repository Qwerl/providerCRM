<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Создание нового поставщика</title>
    <spring:url value="/resources/core/css/styles2.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/resources/core/js/jquery.js" var="jqueryJs"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="${jqueryJs}"></script>
    <link type="text/css" href="${coreCss}" rel="stylesheet"/>
    <link rel="stylesheet" href="${bootstrapCss}">
    <script src="${bootstrapJs}"></script>
</head>

<body>

<div class="container">
    <form:form class="form-horizontal" method="post" commandName="providerForm" action=''>
        <h2>Создание нового поставщика</h2>
        <table class="table table-striped">
            <tbody>
            <tr>
                <td>Поставщик</td>
                <td><form:input class="form-control" placeholder="Введите название" path="name" required="required"/></td>
                <td><form:errors path="name" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Адрес офиса</td>
                <td><form:input class="form-control" placeholder="Введите адрес офиса" path="address" required="required"/></td>
                <td><form:errors path="address" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Адрес склада</td>
                <td><form:input class="form-control" placeholder="Введите адрес склада" path="storageAddress" required="required"/></td>
                <td><form:errors path="storageAddress" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Общий телефон</td>
                <td><form:input class="form-control" placeholder="Введите телефон" path="phoneNumber" required="required"/></td>
                <td><form:errors path="phoneNumber" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Примечания</td>
                <td><form:input class="form-control" placeholder="Введите примечания" path="note" required="required"/></td>
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