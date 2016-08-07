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

<%-- Employee Modal --%>
<div class="modal fade" id="employeeRegistrationModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Добавление нового сотрудника</h4>
      </div>
      <div class="modal-body">
        <form:form commandName="employeeForm" id="employeeRegistrationForm">
          <div class="control-group" id="positionControlGroup">
            <div class="controls">
              <form:input class="form-control" placeholder="Введите должность" required="required"
                          path="position"/>
              <span class="help-inline"><form:errors path="position" cssClass="error"/></span>
            </div>
          </div>
          <div class="control-group" id="fullNameControlGroup">
            <div class="controls">
              <form:input class="form-control" placeholder="Введите ФИО" required="required"
                          path="fullName"/>
              <span class="help-inline"><form:errors path="fullName" cssClass="error"/></span>
            </div>
          </div>
          <div class="control-group" id="emailControlGroup">
            <div class="controls">
              <form:input class="form-control" placeholder="Введите email" required="required"
                          path="email"/>
              <span class="help-inline"><form:errors path="email" cssClass="error"/></span>
            </div>
          </div>
          <div class="control-group" id="homePhoneNumberControlGroup">
            <div class="controls">
              <form:input class="form-control" placeholder="Введите мобильный телефон" required="required"
                          path="homePhoneNumber"/>
              <span class="help-inline"><form:errors path="homePhoneNumber" cssClass="error"/></span>
            </div>
          </div>
          <div class="control-group" id="workPhoneNumberControlGroup">
            <div class="controls">
              <form:input class="form-control" placeholder="Введите рабочий телефон" required="required"
                          path="workPhoneNumber"/>
              <span class="help-inline"><form:errors path="workPhoneNumber" cssClass="error"/></span>
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

<div class="container">
  <form:form commandName="producerForm">
  <div class="page-header">
    <ul class="nav nav-pills navbar-right">
      <li class="active">
        <a href="/producer/${producerForm.producer.id}/edit">Редактировать</a>
      </li>
      <li class="active">
        <a href="/selectProducer/${producerForm.producer.id}">Вернуться</a>
      </li>
    </ul>
    <h2>${producerForm.producer.name}</h2>
  </div>

  <div class="employee table">
    <table id="employeeTable" class="table table-striped">
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
      <c:forEach items="${producerForm.employees}" var="employee">
        <tr>
          <td>${employee.position}</td>
          <td>${employee.fullName}</td>
          <td>${employee.email}</td>
          <td>${employee.workPhoneNumber}</td>
          <td>${employee.homePhoneNumber}</td>
          <td></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

  <div class="producer table">
    <table class="table table-striped">
      <h3>Информация о производителе</h3>
      <tbody>
      <tr>
        <td>Производитель</td>
        <td>${producerForm.producer.name}</td>
      </tr>
      <tr>
        <td>Адрес офиса</td>
        <td>${producerForm.producer.address}</td>
      </tr>
      <tr>
        <td>Общий телефон</td>
        <td>${producerForm.producer.phoneNumber}</td>
      </tr>
      <tr>
        <td>Примечания</td>
        <td>${producerForm.producer.note}</td>
      </tr>
      </tbody>
    </table>
  </div>

  <div class="producer table">
    <table class="table table-striped">
      <h3>Поставщики</h3>
      <thead>
      <tr>
        <th>#</th>
        <th>Наименование</th>
        <th>Адрес</th>
        <th/>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${producerForm.providers}" var="provider" varStatus="providerStatus">
        <tr id="tr${provider.id}">
          <td><a href="/provider/${provider.id}">${providerStatus.index + 1}</a></td>
          <td>${provider.name}</td>
          <td>${provider.address}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  </form:form>
  <script type="text/javascript">

    var unbindEmployee = function (employeeId, employeeTableId) {
      $.ajax({
        type: 'POST',
        url: '/producer/${producerForm.producer.id}/edit/employees/' + employeeId + '/unbind',
        dataType: 'json',
        async: true,
        success: function () {
          //помечаем, что сотрудник отвязан
          document.getElementById('employees' + employeeTableId + '.isUnbound').value = true;
          //помечаем, что сотрудник больше не связан с фирмой-поставщиком
          document.getElementById('employees' + employeeTableId + '.producerId').value = "0";
          //скрываем строку в таблице
          document.getElementById('tr' + employeeId).style.display = 'none';
        },
        error: function (jqXHR, textStatus, errorThrown) {
          alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
      });
    };

    var addRowToEmployeeTable = function (result) {
      $('#employeeTable').find('tbody')
          .append(
              "\<tr id=\"tr" + result.id + "\"\>" +
              "\<td\>" +
              result.position +
              "\<\/td\>" +
              "\<td\>" +
              result.fullName +
              "\<\/td\>" +
              "\<td\>" +
              result.email +
              "\<\/td\>" +
              "\<td\>" +
              result.homePhoneNumber +
              "\<\/td\>" +
              "\<td\>" +
              result.workPhoneNumber +
              "\<\/td\>" +
              "\<td\>" +
              "\<ul class=\"nav nav-pills\"\>" +
              "\<li class=\"default\">" +
              "\<a href=\"javascript:unbindEmployee(" + result.id + ")\"\>" +
              "Отвязать" +
              "\<\/a\>" +
              "\<\/li\>" +
              "\<\/ul\>" +
              "\<\/td\>" +
              "\<\/tr\>");
    };

    function collectFormData(fields) {
      var data = {};
      for (var i = 0; i < fields.length; i++) {
        var $item = $(fields[i]);
        data[$item.attr('name')] = $item.val();
      }
      return data;
    }

    $(document).ready(function () {
      var $employeeForm = $('#employeeRegistrationForm');
      $employeeForm.bind('submit', function (e) {
        var $inputs = $employeeForm.find('input');
        var data = collectFormData($inputs);
        $.post('/producer/${producerForm.producer.id}/edit/employees/validateNew', data, function (response) {
          $employeeForm.find('.control-group').removeClass('error');
          $employeeForm.find('.help-inline').empty();
          $employeeForm.find('.alert').remove();
          if (response.status == 'FAIL') {
            for (var i = 0; i < response.errorMessageList.length; i++) {
              var item = response.errorMessageList[i];
              var $controlGroup = $('#' + item.fieldName + 'ControlGroup');
              $controlGroup.addClass('error');
              $controlGroup.find('.help-inline').html(item.message);
            }
          } else {
            $('#employeeRegistrationModal').modal('hide');
            $employeeForm.trigger('reset');
            addRowToEmployeeTable(response);
          }
        }, 'json');
        e.preventDefault();
        return false;
      });

      var $producerForm = $('#producerForm');
      $producerForm.bind('submit', function (e) {
        var $inputs = $producerForm.find('input');
        var data = collectFormData($inputs);
        $.post('/producer/${producerForm.producer.id}/edit', data, function (response) {
          $producerForm.find('.control-group').removeClass('error');
          $producerForm.find('.help-inline').empty();
          $producerForm.find('.alert').remove();
          if (response.status == 'FAIL') {
            for (var i = 0; i < response.errorMessageList.length; i++) {
              var item = response.errorMessageList[i];
              var str = "#" + item.fieldName;
              var $spanP = $(str);
              $spanP.html(item.message);
            }
          } else {
            $(location).attr('href', "/producer/${producerForm.producer.id}");
          }
        }, 'json');
        e.preventDefault();
        return false;
      });
    });
  </script>
</body>
</html>