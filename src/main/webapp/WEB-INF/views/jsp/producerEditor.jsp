<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="${bootstrapJs}"></script>
</head>

<body>

<div class="container">

    <div class="page-header">
        <h2>${producerForm.name}</h2>
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
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <c:choose>
                        <%-- Изменяемая ли строка --%>
                        <c:when test="${employeeForm.id == employee.id}">
                            <form:form
                                    action="${pageContext.request.contextPath}/producer/${producerForm.id}/edit/employee/${employeeForm.id}"
                                    method="post" commandName="employeeForm">
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
                                <td>
                                    <div class="form-actions">
                                        <button type="submit" class="btn btn-primary">Сохранить</button>
                                        <button type="button" onclick="history.back()" class="btn">Отменить</button>
                                    </div>
                                </td>
                            </form:form>
                        </c:when>
                        <%-- Режим выбора редактируемого сотрудника --%>
                        <c:when test="${employeesEditing}">
                            <td>${employee.position}</td>
                            <td>${employee.fullName}</td>
                            <td>${employee.email}</td>
                            <td>${employee.workPhoneNumber}</td>
                            <td>${employee.homePhoneNumber}</td>
                            <td>
                                <div>
                                    <form action="${pageContext.request.contextPath}/producer/${producerForm.id}/edit/employee/${employee.id}"
                                          method="get">
                                        <button class="btn btn-primary">Изменить</button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/producer/${producerForm.id}/edit/employee/${employee.id}/delete"
                                          method="post">
                                        <button class="btn btn-primary">Удалить</button>
                                    </form>
                                </div>
                            </td>
                        </c:when>
                        <%-- Режим просмотра --%>
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
                    <form:form class="form-horizontal" method="post" action='' commandName="employeeForm">
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
                        <td>
                            <div class="form-actions">
                                <button type="submit" class="btn btn-primary">Сохранить</button>
                                <button type="button" onclick="history.back()" class="btn">Отменить</button>
                            </div>
                        </td>
                    </form:form>
                </tr>
            </c:if>
            </tbody>
        </table>

        <c:if test="${!employeeWritePermission}">
            <ul class="nav nav-pills navbar-left">
                <li class="active">
                    <a href="/producer/${producerForm.id}/edit/addEmployee">
                        Добавить сотрудника
                    </a>
                </li>
                <c:if test="${!employeesEditing}">
                    <li class="active">
                        <a href="/producer/${producerForm.id}/edit/employee">
                            Редактировать сотрудников
                        </a>
                    </li>
                </c:if>
            </ul>
        </c:if>
        <br/>
    </div>

    <div class="producer table">
        <br/>

        <form:form action="${pageContext.request.contextPath}/producer/${producerForm.id}/edit" method="post" commandName="producerForm">
            <table class="table table-striped">
                <tbody>

                <c:choose>
                    <%-- Режим редактирования--%>
                    <c:when test="${producerEditing}">
                        <tr>
                            <td>Поставщик</td>
                            <td><form:input class="form-control" path="name"/></td>
                            <td>
                                <form:errors path="name" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Адрес офиса</td>
                            <td><form:input class="form-control" path="address"/></td>
                            <td>
                                <form:errors path="address" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Общий телефон</td>
                            <td><form:input class="form-control" path="phoneNumber"/></td>
                            <td>
                                <form:errors path="phoneNumber" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Примечания</td>
                            <td><form:textarea rows="5" class="form-control" path="note"></form:textarea></td>
                            <td>
                                <form:errors path="note" cssClass="error"/>
                            </td>
                        </tr>

                        <tr>
                            <td>Поставщики</td>
                            <td>
                                <form:checkboxes path="providers" items="${producerForm.providers}" checked="checked"/>
                                <form:checkboxes path="providers" items="${otherProviders}"/>
                            </td>
                            <td>
                                <form:errors path="providers" cssClass="error"/>
                            </td>
                        </tr>
                    </c:when>

                    <c:otherwise>
                        <tr>
                            <td>Поставщик</td>
                            <td>${producerForm.name}</td>
                        </tr>
                        <tr>
                            <td>Адрес офиса</td>
                            <td>${producerForm.address}</td>
                        </tr>
                        <tr>
                            <td>Общий телефон</td>
                            <td>${producerForm.phoneNumber}</td>
                        </tr>
                        <tr>
                            <td>Примечания</td>
                            <td>${producerForm.note}</td>
                        </tr>

                        <tr>
                            <td>Поставщики</td>
                            <td>
                                <c:forEach items="${producerForm.providers}" var="provider">
                                    <li class="active">
                                        <a href="${pageContext.request.contextPath}/provider/${provider.id}">
                                            <c:out value="${provider.name}"/></a>
                                    </li>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
            <ul class="nav nav-pills navbar-right">
                <c:choose>
                    <c:when test="${producerEditing}">
                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                            <button type="button" onclick="history.back()" class="btn">Отменить</button>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/producer/${producerForm.id}/edit">
                                Редактировать
                            </a>
                        </li>
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/selectProducer/${producerForm.id}">
                                Отмена
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </form:form>
    </div>


</body>
</html>