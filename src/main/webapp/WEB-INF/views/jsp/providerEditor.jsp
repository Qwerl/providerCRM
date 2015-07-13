<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>testTask</title>
    <spring:url value="/resources/core/css/styles2.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" href="${coreCss}" rel="stylesheet"/>
    <link rel="stylesheet" href="${bootstrapCss}">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="${bootstrapJs}"></script>
</head>
<body>

<div class="container">

    <div class="page-header">
        <h2>${providerInfo.name}</h2>
    </div>

    <div class="employee table">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Должность</th>
                <th>Фио</th>
                <th>Email</th>
                <th>Рабочий телефон</th>
                <th>Мобильный телефон</th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${providerInfo.employees}" var="employee">
                <tr>
                    <c:choose>
                        <%-- Изменяемая ли строка --%>
                        <c:when test="${editableEmployeeId == employee.id}">
                            <form action="${pageContext.request.contextPath}/provider/${providerInfo.id}/edit/employee/${employee.id}"
                                  method="post">
                                <td>
                                    <input type="text"
                                           class="form-control"
                                           value="${employee.position}"
                                           name="position">
                                </td>
                                <td>
                                    <input type="text"
                                           class="form-control"
                                           value="${employee.fullName}"
                                           name="fullName">
                                </td>
                                <td>
                                    <input type="text"
                                           class="form-control"
                                           value="${employee.email}"
                                           name="email">
                                </td>
                                <td>
                                    <input type="text"
                                           class="form-control"
                                           value="${employee.workPhoneNumber}"
                                           name="workPhoneNumber">
                                </td>
                                <td>
                                    <input type="text"
                                           class="form-control"
                                           value="${employee.homePhoneNumber}"
                                           name="homePhoneNumber"></td>
                                <td>
                                    <div class="form-actions">
                                        <button type="submit" class="btn btn-primary">Сохранить</button>
                                        <button type="button" onclick="history.back()" class="btn">Отменить</button>
                                    </div>
                                </td>
                            </form>
                        </c:when>
                        <%-- Режим редактирования сотрудников --%>
                        <c:when test="${employeesEditing}">
                            <td>${employee.position}</td>
                            <td>${employee.fullName}</td>
                            <td>${employee.email}</td>
                            <td>${employee.workPhoneNumber}</td>
                            <td>${employee.homePhoneNumber}</td>
                            <td>
                                <div>
                                    <form action="${pageContext.request.contextPath}/provider/${providerInfo.id}/edit/employee/${employee.id}"
                                          method="get">
                                        <button class="btn btn-primary" name="delete">Изменить</button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/provider/${providerInfo.id}/edit/employee/${employee.id}/delete"
                                          method="post">
                                        <button class="btn btn-primary" name="delete">Удалить</button>
                                    </form>
                                </div>
                            </td>
                        </c:when>
                        <%-- Обычный просмотр --%>
                        <c:otherwise>
                            <td>${employee.position}</td>
                            <td>${employee.fullName}</td>
                            <td>${employee.email}</td>
                            <td>${employee.workPhoneNumber}</td>
                            <td>${employee.homePhoneNumber}</td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>

            <%-- Добавление нового сотрудника --%>
            <c:if test="${employeeWritePermission}">
                <tr>
                    <form class="form-horizontal" method="post" action='' name="employeeForm" id="employeeForm">
                        <td>
                            <div class="control-group">
                                <div class="controls">
                                    <input type="text" class="form-control" name="position"
                                           placeholder="Введите должность">
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="control-group">
                                <div class="controls">
                                    <input type="text" class="form-control" name="fullName" placeholder="Введите ФИО">
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="control-group">
                                <div class="controls">
                                    <input type="text" class="form-control" name="email" placeholder="Введите Email">
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="control-group">
                                <div class="controls">
                                    <input type="text" class="form-control" name="workPhoneNumber"
                                           placeholder="Введите Раб. телефон">
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="control-group">
                                <div class="controls">
                                    <input type="text" class="form-control" name="homePhoneNumber"
                                           placeholder="Введите Моб. телефон">
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="form-actions">
                                <button type="submit" class="btn btn-primary">Сохранить</button>
                                <button type="button" onclick="history.back()" class="btn">Отменить</button>
                            </div>
                        </td>
                    </form>
                </tr>
            </c:if>
            </tbody>
        </table>
        <%-- Выводить ли кнопку "добавить сотрудника"--%>
        <c:if test="${!employeeWritePermission}">
            <ul class="nav nav-pills navbar-left">
                <li class="active">
                    <a href="/provider/${providerInfo.id}/edit/addEmployee">
                        Добавить сотрудника
                    </a>
                </li>
                <c:if test="${!employeesEditing}">
                    <li class="active">
                        <a href="/provider/${providerInfo.id}/edit/employee">
                            Редактировать сотрудников
                        </a>
                    </li>
                </c:if>
            </ul>
        </c:if>
        <br/>
    </div>

    <div class="provider table">
        <br/>

        <form action="${pageContext.request.contextPath}/provider/${providerInfo.id}/edit" method="post">
            <table class="table table-striped">
                <tbody>
                <tr>
                    <td>Поставщик</td>
                    <td>
                        <c:choose>
                            <c:when test="${providerEditing}">
                                <input type="text" class="form-control" value="${providerInfo.name}"
                                       name="providerName">
                            </c:when>
                            <c:otherwise>
                                ${providerInfo.name}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>Адрес офиса</td>
                    <td>
                        <c:choose>
                            <c:when test="${providerEditing}">
                                <input type="text" class="form-control" value="${providerInfo.address}"
                                       name="providerAddress">
                            </c:when>
                            <c:otherwise>
                                ${providerInfo.address}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>Адрес склада</td>
                    <td>
                        <c:choose>
                            <c:when test="${providerEditing}">
                                <input type="text" class="form-control" value="${providerInfo.storageAddress}"
                                       name="providerStorageAddress">
                            </c:when>
                            <c:otherwise>
                                ${providerInfo.storageAddress}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>Общий телефон</td>
                    <td>
                        <c:choose>
                            <c:when test="${providerEditing}">
                                <input type="text" class="form-control" value="${providerInfo.phoneNumber}"
                                       name="providerPhoneNumber">
                            </c:when>
                            <c:otherwise>
                                ${providerInfo.phoneNumber}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>Примечания</td>
                    <td>
                        <c:choose>
                            <c:when test="${providerEditing}">
                                <textarea rows="5" class="form-control"
                                          name="providerNote">${providerInfo.note}</textarea>
                            </c:when>
                            <c:otherwise>
                                ${providerInfo.note}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>Поставляемое оборудование</td>
                    <td>
                        <c:choose>
                            <c:when test="${providerEditing}">
                                <c:forEach items="${providerInfo.products}" var="product">
                                    <input type="checkbox" name="products" value="${product.id}" checked>
                                    <c:out value="${product.name}"></c:out>
                                </c:forEach>
                                <c:forEach items="${otherProducts}" var="product">
                                    <input type="checkbox" name="products" value="${product.id}">
                                    <c:out value="${product.name}"></c:out>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${providerInfo.products}" var="product">
                                    <li <c:if test="${not empty product}">class="active"</c:if>>
                                        <a href="${pageContext.request.contextPath}/product/${product.name}">
                                            <c:out value="${product.name}"/></a>
                                    </li>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>Теги</td>
                    <td>
                        <c:choose>
                            <c:when test="${providerEditing}">
                                <c:forEach items="${providerInfo.tags}" var="tag">
                                    <input type="checkbox" name="tags" value="${tag.id}" checked>
                                    <c:out value="${tag.tagText}"></c:out>
                                </c:forEach>
                                <c:forEach items="${otherTags}" var="tag">
                                    <input type="checkbox" name="tags" value="${tag.id}">
                                    <c:out value="${tag.tagText}"></c:out>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${providerInfo.tags}" var="tag">
                                    <li class="active">
                                        <a href="${pageContext.request.contextPath}/search/${tag.tagText}">
                                            <c:out value="${tag.tagText}"/></a>
                                    </li>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tbody>
            </table>
            <ul class="nav nav-pills navbar-right">
                <c:choose>
                    <c:when test="${providerEditing}">
                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                            <button type="button" onclick="history.back()" class="btn">Отменить</button>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/provider/${providerInfo.id}/edit">
                                Редактировать
                            </a>

                        </li>
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/selectProvider/${providerInfo.id}">
                                Отмена
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </form>
    </div>

</div>

</body>
</html>