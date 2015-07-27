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
    <spring:url value="/resources/core/js/jquery.js" var="jqueryJs"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="${jqueryJs}"></script>
    <link type="text/css" href="${coreCss}" rel="stylesheet"/>
    <link rel="stylesheet" href="${bootstrapCss}">
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
                               name="testForm" commandName="productForm" id="productRegistrationForm">

                        <div class="control-group" id="nameControlGroup">
                            <div class="controls">
                                <form:input class="form-control" placeholder="Введите назнание оборудования"
                                            required="required" path="name"/>
                                <span class="help-inline"><form:errors path="name" cssClass="error"/></span>
                            </div>
                        </div>
                        <div class="control-group" id="priceControlGroup">
                            <div class="controls">
                                <form:input class="form-control" placeholder="Введите цену" required="required"
                                            path="price"/>
                                <span class="help-inline"><form:errors path="price" cssClass="error"/></span>
                            </div>
                        </div>
                        <div class="control-group" id="noteControlGroup">
                            <div class="controls">
                                <form:input class="form-control" placeholder="Введите примечание" required="required"
                                            path="note"/>
                                <span class="help-inline"><form:errors path="note" cssClass="error"/></span>
                            </div>
                        </div>
                        <div class="form-actions">
                            <form:button type="submit" class="btn btn-success">Сохранить</form:button>
                        </div>

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
                            <a href="" data-toggle="modal" data-target="#productRegistrationModal">
                                Новое оборудование
                            </a>
                        </li>
                        <li class="active">
                            <a href="/provider/${providerForm.id}/edit/products/edit">
                                Отвязать оборудование
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
            <c:forEach items="${products}" var="product" varStatus="productStatus">
                <tr id="tr${product.id}">
                    <td><a href="/product/${product.id}">${productStatus.index + 1}</a></td>
                    <td><c:out value="${product.name}"/></td>
                    <td><c:out value="${product.price}"/></td>
                    <td><c:out value="${product.note}"/></td>
                    <c:choose>
                        <c:when test="${productUnbindingMode}">
                            <td>
                                <ul class="nav nav-pills navbar-right">
                                    <li class="default">
                                        <a href="javascript:unbindProduct(${product.id})">
                                            Отвязать
                                        </a>
                                    </li>
                                </ul>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td/>
                        </c:otherwise>
                    </c:choose>

                </tr>
            </c:forEach>
            <%-- Выдать список всех продуктов, которых нет у поставщика --%>
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
                        <%-- Показать информацию о выбранном оборудовании --%>
                    <td><c:out value="${selectedProduct.name}"/></td>
                    <td><c:out value="${selectedProduct.price}"/></td>
                    <td><c:out value="${selectedProduct.note}"/></td>
                    <td>
                        <c:if test="${not empty selectedProduct}">
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
                        </c:if>
                    </td>
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

<script type="text/javascript">
    var unbindProduct = function (productId) {
        $.ajax({
            type: 'POST',
            url: '/provider/${providerForm.id}/edit/products/edit/' + productId,
            dataType: 'json',
            async: true,
            success: function () {
                $('#tr' + productId).remove();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    }

    function collectFormData(fields) {
        var data = {};
        for (var i = 0; i < fields.length; i++) {
            var $item = $(fields[i]);
            data[$item.attr('name')] = $item.val();
        }
        return data;
    }
    $(document).ready(function () {
        var $form = $('#productRegistrationForm');
        $form.bind('submit', function (e) {
            var $inputs = $form.find('input');
            var data = collectFormData($inputs);
            $.post('/provider/${providerForm.id}/edit/products/validateNew', data, function (response) {
                $form.find('.control-group').removeClass('error');
                $form.find('.help-inline').empty();
                $form.find('.alert').remove();
                if (response.status == 'FAIL') {
                    for (var i = 0; i < response.errorMessageList.length; i++) {
                        var item = response.errorMessageList[i];
                        var $controlGroup = $('#' + item.fieldName + 'ControlGroup');
                        $controlGroup.addClass('error');
                        $controlGroup.find('.help-inline').html(item.message);
                    }
                } else {
                    $form.unbind('submit');
                    $form.submit();
                }
            }, 'json');
            e.preventDefault();
            return false;
        });
    });
</script>
</body>
</html>