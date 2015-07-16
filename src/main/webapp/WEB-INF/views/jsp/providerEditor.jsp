<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
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

            <c:forEach items="${providerInfo.employees}" var="currentEmployee">
                <tr>
                    <c:choose>
                        <%-- Изменяемая ли строка --%>
                        <c:when test="${editableEmployeeId == currentEmployee.id}">
                            <form:form action="/provider/${providerInfo.id}/edit/employee/${employee.id}" method="post"
                                       commandName="employee">
                                <%--action=""--%>
                                <td>
                                    <form:input class="form-control"
                                                path="position"/>
                                    <form:errors path="position" cssClass="error"/>
                                </td>
                                <td>
                                    <form:input class="form-control"
                                                path="fullName"/>
                                    <form:errors path="fullName" cssClass="error"/>
                                </td>
                                <td>
                                    <form:input class="form-control"
                                                path="email"/>
                                    <form:errors path="email" cssClass="error"/>
                                </td>
                                <td>
                                    <form:input class="form-control"
                                                path="workPhoneNumber"/>
                                    <form:errors path="workPhoneNumber" cssClass="error"/>
                                </td>

                                <td>
                                    <form:input class="form-control"
                                                path="homePhoneNumber"/>
                                    <form:errors path="homePhoneNumber" cssClass="error"/>
                                </td>
                                <td>
                                    <div class="form-actions">
                                        <button type="submit" class="btn btn-primary">Сохранить</button>
                                        <button type="button" onclick="history.back()" class="btn">Отменить</button>
                                    </div>
                                </td>
                            </form:form>
                        </c:when>
                        <%-- Режим редактирования сотрудников --%>
                        <c:when test="${employeesEditing}">
                            <td>${currentEmployee.position}</td>
                            <td>${currentEmployee.fullName}</td>
                            <td>${currentEmployee.email}</td>
                            <td>${currentEmployee.workPhoneNumber}</td>
                            <td>${currentEmployee.homePhoneNumber}</td>
                            <td>
                                <div>
                                    <form action="${pageContext.request.contextPath}/provider/${providerInfo.id}/edit/employee/${currentEmployee.id}"
                                          method="get">
                                        <button type="submit" class="btn btn-primary">Изменить</button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/provider/${providerInfo.id}/edit/employee/${currentEmployee.id}/delete"
                                          method="post">
                                        <button type="submit" class="btn btn-primary">Удалить</button>
                                    </form>
                                </div>
                            </td>
                        </c:when>
                        <%-- Обычный просмотр --%>
                        <c:otherwise>
                            <td>${currentEmployee.position}</td>
                            <td>${currentEmployee.fullName}</td>
                            <td>${currentEmployee.email}</td>
                            <td>${currentEmployee.workPhoneNumber}</td>
                            <td>${currentEmployee.homePhoneNumber}</td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>

            <%-- Добавление нового сотрудника --%>
            <c:if test="${employeeWritePermission}">

                <form:form class="form-horizontal" method="post" action='' commandName="employee">
                    <tr>
                        <td>
                            <form:input class="form-control" path="position" placeholder="Введите должность"/>
                            <form:errors path="position" cssClass="error"/>
                        </td>
                        <td>
                            <form:input class="form-control" path="fullName" placeholder="Введите ФИО"/>
                            <form:errors path="fullName" cssClass="error"/>
                        </td>
                        <td>
                            <form:input class="form-control" path="email" placeholder="Введите Email"/>
                            <form:errors path="email" cssClass="error"/>
                        </td>
                        <td>
                            <form:input class="form-control" path="workPhoneNumber" placeholder="Введите Раб. телефон"/>
                            <form:errors path="workPhoneNumber" cssClass="error"/>

                        </td>
                        <td>
                            <form:input class="form-control" path="homePhoneNumber" placeholder="Введите Моб. телефон"/>
                            <form:errors path="homePhoneNumber" cssClass="error"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                            <button type="button" onclick="history.back()" class="btn">Отменить</button>
                        </td>
                    </tr>
                </form:form>

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