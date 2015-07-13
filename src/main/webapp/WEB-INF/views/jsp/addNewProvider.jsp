<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Создание нового поставщика</title>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
    <form class="form-horizontal" method="post" action='' name="employeeForm" id="employeeForm">
        <h2>Создание нового поставщика</h2>
        <table class="table table-striped">
            <tbody>
            <tr>
                <td>Поставщик</td>
                <td>
                    <div class="control-group">
                        <div class="controls">
                            <input type="text" class="form-control" name="name" placeholder="Введите название">
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Адрес офиса</td>
                <td>
                    <div class="control-group">
                        <div class="controls">
                            <input type="text" class="form-control" name="address" placeholder="Введите адрес офиса"
                                   id="address">
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Адрес склада</td>
                <td>
                    <div class="control-group">
                        <div class="controls">
                            <input type="text" class="form-control" name="storageAddress"
                                   placeholder="Введите адрес склада">
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Общий телефон</td>
                <td>
                    <div class="control-group">
                        <div class="controls">
                            <input type="text" class="form-control" name="phoneNumber" placeholder="Введите телефон">
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Примечания</td>
                <td>
                    <div class="control-group">
                        <div class="controls">
                             <textarea rows="5" class="form-control" placeholder="Введите примечания"
                                       name="note"></textarea>
                        </div>
                    </div>
                </td>
            </tr>
            <td>Теги</td>
            <td>
                <div class="control-group">
                    <div class="controls">
                        <c:forEach items="${tags}" var="tag">
                            <input type="checkbox" name="tags" value="${tag.id}"><c:out value="${tag.tagText}"></c:out>
                        </c:forEach>
                    </div>
                </div>
            </td>
            </tr>
            </tbody>
        </table>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Сохранить</button>
            <button type="button" onclick="history.back()" class="btn">Отменить</button>
        </div>
    </form>

</div>
</body>
</html>