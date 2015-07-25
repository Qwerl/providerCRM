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

<c:if test="${productEditing}">
    <%-- Modal --%>
    <div class="modal fade" id="productRegistrationModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Добавление нового оборудования</h4>
                </div>
                <div class="modal-body">
                    <form:form method="post" action="/provider/${providerForm.id}/edit/products/addProduct"
                               name="testForm" commandName="productForm">
                        <form:errors path="*">

                            <script type="text/javascript">
                                $(document).ready(function () {
                                    $("#productRegistrationModal").modal('show');
                                });
                            </script>
                   </form:errors>
                        <form:input class="form-control" placeholder="Введите назнание оборудования" required="required"
                                    path="name"/>
                        <form:errors path="name" cssClass="error"/>

                        <form:input class="form-control" placeholder="Введите цену" required="required" path="price"/>
                        <form:errors path="price" cssClass="error"/>

                        <form:input class="form-control" placeholder="Введите примечание" required="required"
                                    path="note"/>
                        <form:errors path="note" cssClass="error"/>

                        <form:button type="submit" class="btn btn-success">Сохранить</form:button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</c:if>

<div class="container">

    <div class="page-header">


        <ul class="nav nav-pills navbar-right">
            <c:choose>
                <c:when test="${editingMode}">
                    <li class="active">
                        <a href="/provider/${providerForm.id}">Отмена</a>
                    </li>
                </c:when>
                <c:when test="${providerEditing or employeesEditing or employeeAddingMode or productEditing or (editableEmployeeId!=null)}">
                    <li class="active">
                        <a href="/provider/${providerForm.id}/edit">Отмена</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="active">
                        <a href="/provider/${providerForm.id}/edit">Редактировать</a>
                    </li>
                    <li class="active">
                        <a href="/selectProvider/${providerForm.id}">Вернуться</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
        <h2>${providerForm.name}</h2>
    </div>

    <div class="employee table">
        <table class="table table-striped">
            <c:if test="${editingMode}">
                <ul class="nav nav-pills navbar-right">
                    <li class="active">
                        <a href="/provider/${providerForm.id}/edit/addEmployee">
                            Добавить сотрудника
                        </a>
                    </li>
                    <c:if test="${not empty employees}">
                        <li class="active">
                            <a href="/provider/${providerForm.id}/edit/employee">
                                Редактировать сотрудников
                            </a>
                        </li>
                    </c:if>
                </ul>
            </c:if>
            <h3>Информация о сотрудниках</h3>
            <thead>
            <tr>
                <th>Должность</th>
                <th>ФИО</th>
                <th>Email</th>
                <th>Рабочий телефон</th>
                <th>Мобильный телефон</th>
                <th/>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${employees}" var="currentEmployee">
                <tr>
                    <c:choose>
                        <%-- Режим редактирования содрудника с заданным ID --%>
                        <c:when test="${employeeForm.id == currentEmployee.id}">
                            <form:form action="/provider/${providerForm.id}/edit/employee/${currentEmployee.id}"
                                       method="post" commandName="employeeForm">
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
                        <%-- Режим выбора редактируемого сотрудника --%>
                        <c:when test="${employeesEditing}">
                            <td>${currentEmployee.position}</td>
                            <td>${currentEmployee.fullName}</td>
                            <td>${currentEmployee.email}</td>
                            <td>${currentEmployee.workPhoneNumber}</td>
                            <td>${currentEmployee.homePhoneNumber}</td>
                            <td>
                                <div>
                                    <form action="/provider/${providerForm.id}/edit/employee/${currentEmployee.id}"
                                          method="get">
                                        <button type="submit" class="btn btn-primary">Изменить</button>
                                    </form>
                                    <form action="/provider/${providerForm.id}/edit/employee/${currentEmployee.id}/delete"
                                          method="post">
                                        <button type="submit" class="btn btn-primary">Удалить</button>
                                    </form>
                                </div>
                            </td>
                        </c:when>
                        <%-- Режим просмотра --%>
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
            <c:if test="${employeeAddingMode}">
                <form:form class="form-horizontal" method="post" action='' commandName="employeeForm">
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
                        <td/>
                    </tr>
                    <tr>
                        <td colspan="6" align="right">
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                            <button type="button" onclick="history.back()" class="btn">Отменить</button>
                        </td>
                    </tr>
                </form:form>
            </c:if>
            </tbody>
        </table>
    </div>

    <div class="provider table">
        <form:form action="/provider/${providerForm.id}/edit/info" method="post" commandName="providerForm">
            <ul class="nav nav-pills navbar-right">
                <c:choose>
                    <c:when test="${editingMode}">
                        <li class="active">
                            <a href="/provider/${providerForm.id}/edit/info">
                                Редактировать информацию о поставщике
                            </a>
                        </li>
                    </c:when>
                    <c:when test="${providerEditing}">
                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                            <button type="button" onclick="history.back()" class="btn">Отменить</button>
                        </div>
                    </c:when>
                </c:choose>
            </ul>
            <table class="table table-striped">
                <h3>Информация о поставщике</h3>
                <tbody>
                <c:choose>
                    <%-- Режим редактирования --%>
                    <c:when test="${providerEditing}">
                        <tr>
                            <td>Поставщик</td>
                            <td><form:input class="form-control" path="name"/></td>
                            <td><form:errors path="name" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <td>Адрес офиса</td>
                            <td><form:input class="form-control" path="address"/></td>
                            <td><form:errors path="address" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <td>Адрес склада</td>
                            <td><form:input class="form-control" path="storageAddress"/></td>
                            <td><form:errors path="storageAddress" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <td>Общий телефон</td>
                            <td><form:input class="form-control" path="phoneNumber"/></td>
                            <td><form:errors path="phoneNumber" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <td>Примечания</td>
                            <td><form:textarea rows="5" class="form-control" path="note"/></td>
                            <td><form:errors path="note" cssClass="error"/></td>
                        </tr>
                    </c:when>
                    <%-- Режим просмотра --%>
                    <c:otherwise>
                        <tr>
                            <td>Поставщик</td>
                            <td>${providerForm.name}</td>
                        </tr>
                        <tr>
                            <td>Адрес офиса</td>
                            <td>${providerForm.address}</td>
                        </tr>
                        <tr>
                            <td>Адрес склада</td>
                            <td>${providerForm.storageAddress}</td>
                        </tr>
                        <tr>
                            <td>Общий телефон</td>
                            <td>${providerForm.phoneNumber}</td>
                        </tr>
                        <tr>
                            <td>Примечания</td>
                            <td>${providerForm.note}</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </form:form>
    </div>

    <div class="product table">

        <table class="table table-striped">
            <%-- Добавить кнопки, если в режиме редактирования --%>
            <c:choose>
                <c:when test="${productEditing}">
                    <ul class="nav nav-pills navbar-right">
                        <li class="active">
                            <a href="/provider/${providerForm.id}/edit/products/add">
                                Привязать оборудование
                            </a>
                        </li>
                        <li class="active">
                            <a data-toggle="modal" data-target="#productRegistrationModal">
                                Новое оборудование
                            </a>
                        </li>
                    </ul>
                </c:when>
                <c:when test="${editingMode}">
                    <ul class="nav nav-pills navbar-right">
                        <li class="active">
                            <a href="/provider/${providerForm.id}/edit/products">
                                Редактировать оборудование
                            </a>
                        </li>
                    </ul>
                </c:when>
            </c:choose>
            <h3>Поставляемое оборудование</h3>
            <thead>
            <tr>
                <th>#</th>
                <th>Наименование</th>
                <th>Стоимость</th>
                <th>Комментарий</th>
                <th/>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td><a href="/product/${product.id}"><c:out value="${product.id}"/></a></td>
                    <td><c:out value="${product.name}"/></td>
                    <td><c:out value="${product.price}"/></td>
                    <td><c:out value="${product.note}"/></td>
                    <td/>
                </tr>
            </c:forEach>
            <%-- Выдать список всех продуктов --%>
            <c:if test="${productAddingMode}">
                <tr style="border: solid 2px #ddd">
                    <td>
                        <div class="btn-group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                Существующее оборудование <span class="caret"/>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <c:forEach items="${otherProducts}" var="product">
                                    <li>
                                        <a href="/provider/${providerForm.id}/edit/products/add/${product.id}">
                                                ${product.name}
                                        </a>
                                    </li>
                                </c:forEach>
                                <li class="divider"></li>
                                <li><a data-toggle="modal" data-target="#productRegistrationModal">Добавить новое
                                    оборудование</a></li>
                            </ul>
                        </div>
                    </td>
                    <c:if test="${not empty selectedProduct}">
                        <td><c:out value="${selectedProduct.name}"/></td>
                        <td><c:out value="${selectedProduct.price}"/></td>
                        <td><c:out value="${selectedProduct.note}"/></td>
                        <td>
                            <form action="/provider/${providerForm.id}/edit/products/add/${selectedProduct.id}"
                                  method="post">
                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary">Привязать</button>
                                    <button type="button"
                                            onclick="location.href = '/provider/${providerForm.id}/edit/products/add/'"
                                            class="btn">Отменить
                                    </button>
                                </div>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>

    <div class="controls-pane">
        <ul class="nav nav-pills navbar-right">
            <c:choose>
                <c:when test="${editingMode}">
                    <li class="active">
                        <a href="/provider/${providerForm.id}">Отмена</a>
                    </li>
                </c:when>
                <c:when test="${providerEditing or employeesEditing or employeeAddingMode or productEditing or (editableEmployeeId!=null)}">
                    <li class="active">
                        <a href="/provider/${providerForm.id}/edit">Отмена</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="active">
                        <a href="/provider/${providerForm.id}/edit">Редактировать</a>
                    </li>
                    <li class="active">
                        <a href="/selectProvider/${providerForm.id}">Вернуться</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>

</body>
</html>