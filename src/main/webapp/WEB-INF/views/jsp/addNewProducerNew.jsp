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
        <div class="form-actions">
          <button type="submit" class="btn btn-primary">Сохранить</button>
          <button type="button" onclick="location.href = '/'" class="btn">
            Отменить
          </button>
        </div>
      </ul>
      <h2>Создание нового производителя</h2>
    </div>

    <div class="employee table">
      <table id="employeeTable" class="table table-striped">
        <ul class="nav nav-pills navbar-right">
          <li class="active">
            <a data-toggle="modal" data-target="#employeeRegistrationModal">
              Добавить сотрудника
            </a>
          </li>
        </ul>
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
        <c:forEach items="${producerForm.employees}" var="employee" varStatus="x">
          <tr id="tr${employee.id}">
            <form:input path="employees[${x.index}].isUnbound" type="hidden"/>
            <form:input path="employees[${x.index}].id" type="hidden"/>
            <form:input path="employees[${x.index}].providerId" type="hidden"/>
            <form:input path="employees[${x.index}].producerId" type="hidden"/>
            <td>
              <form:input class="form-control" path="employees[${x.index}].position"/>
              <span class="help-inline" id="employees[${x.index}].position">
                    <form:errors path="employees[${x.index}].position" cssClass="error"/>
                </span>
            </td>
            <td>
              <form:input class="form-control" path="employees[${x.index}].fullName"/>
              <span class="help-inline" id="employees[${x.index}].fullName">
                    <form:errors path="employees[${x.index}].fullName" cssClass="error"/>
                </span>
            </td>
            <td>
              <form:input class="form-control" path="employees[${x.index}].email"/>
              <span class="help-inline" id="employees[${x.index}].email">
                    <form:errors path="employees[${x.index}].email" cssClass="error"/>
                </span>
            </td>
            <td>
              <form:input class="form-control" path="employees[${x.index}].workPhoneNumber"/>
              <span class="help-inline" id="employees[${x.index}].workPhoneNumber">
                    <form:errors path="employees[${x.index}].workPhoneNumber" cssClass="error"/>
                </span>
            </td>
            <td>
              <form:input class="form-control" path="employees[${x.index}].homePhoneNumber"/>
              <span class="help-inline" id="employees[${x.index}].homePhoneNumber">
                    <form:errors path="employees[${x.index}].homePhoneNumber" cssClass="error"/>
                </span>
            </td>
            <td>
              <ul class="nav nav-pills navbar-right">
                <li class="default">
                  <a href="javascript:unbindEmployee('${employee.id}', '${x.index}')">
                    Отвязать
                  </a>
                </li>
              </ul>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <div class="producer table">
      <table class="table table-striped">
        <h3>Информация о производителе</h3>
        <tbody>
        <form:input path="producer.id" type="hidden"/>
        <tr>
          <td>Производитель</td>
          <td><form:input class="form-control" path="producer.name"/></td>
          <td>
              <span class="help-inline" id="producer.name">
                  <form:errors path="producer.name" cssClass="error"/>
              </span>
          </td>
        </tr>
        <tr>
          <td>Адрес офиса</td>
          <td><form:input class="form-control" path="producer.address"/></td>
          <td>
              <span class="help-inline" id="producer.address">
                  <form:errors path="producer.address" cssClass="error"/>
              </span>
          </td>
        </tr>
        <tr>
          <td>Общий телефон</td>
          <td><form:input class="form-control" path="producer.phoneNumber"/></td>
          <td>
              <span class="help-inline" id="producer.phoneNumber">
                  <form:errors path="producer.phoneNumber" cssClass="error"/>
              </span>
          </td>
        </tr>
        <tr>
          <td>Примечания</td>
          <td><form:input class="form-control" path="producer.note"/></td>
          <td>
              <span class="help-inline" id="producer.note">
                  <form:errors path="producer.note" cssClass="error"/>
              </span>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="provider table">
      <table id="providerTable" class="table table-striped">
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
        <tr id="selector" style="border: solid 2px #ddd">
          <td>
            <div class="btn-group">
              <button type="button" onclick="getOtherProviders()" class="btn btn-default dropdown-toggle"
                      data-toggle="dropdown">
                Существующие поставщики <span class="caret"/>
              </button>
              <ul id="providerCombobox" class="dropdown-menu" role="menu"></ul>
            </div>
          </td>
          <td id="providerName"></td>
          <td id="providerAddress"></td>
          <td id="providerId">
            <a href="javascript: bindProvider()"></a>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

  </form:form>
</div>

<script type="text/javascript">
  function getOtherProviders() {
    $.ajax({
      type: 'POST',
      url: '/provider/getAll',
      dataType: 'json',
      async: true,

      success: function (response) {
        var html = '';
        var len = response.length;

        for (var i = 0; i < len; i++) {
          html += '\<li\>' +
              '\<a href=\"javascript: selectProvider(' + response[i].id + ')">' +
              response[i].name +
              '<\/a>' +
              '<\/li>';
        }
        $('#providerCombobox').html(html);
      },
      error: function (e) {
        alert('Error: ' + e);
      }
    });
  }

  var selectProvider = function (providerId) {
    $.ajax({
      type: 'POST',
      url: '/provider/' + providerId,
      dataType: 'json',
      async: true,
      success: function (result) {
        var $selector = $('#selector');
        $selector.find('#providerName').html(result.name);
        $selector.find('#providerAddress').html(result.address);
        $selector.find('#providerIdInput').val(result.id);
        $selector.find('#providerId').find('a').html('Привязать');
        $selector.find('#providerId').find('a').attr("href", "javascript:bindProvider(" + result.id + ")");
      },
      error: function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.status + ' ' + jqXHR.responseText);
      }
    });
  };

  var bindProvider = function (providerId) {
    var $selector = $('#selector');
    var $providerName = $('#providerName');
    var $providerAddress = $('#providerAddress');

    var providerName = $providerName.html();
    var providerAddress = $providerAddress.html();

    $providerName.empty();
    $providerAddress.empty();
    $('#providerId').find('a').empty();

    var table = document.getElementById("providerTable");//получаем элемент таблицы
    var providerCount = table.rows.length - 2;//количество строк
    $selector.before(
        "\<tr id=\"tr" + providerId + "\"\>" +
        "<input id=\"providers" + providerCount + ".id\" name=\"providers[" + providerCount + "].id\" type=\"hidden\" value=\"" + providerId + "\">" +
        "\<td\>" +
        "\<\/td\>" +
        "\<td\>" +
        providerName +
        "\<\/td\>" +
        "\<td\>" +
        providerAddress +
        "\<\/td\>" +
        "\<td\>" +
        "\<ul class=\"nav nav-pills\"\>" +
        "\<li class=\"default\">" +
        "\<a href=\"javascript:unbindProvider(" + providerId + ")\"\>" +
        "Отвязать" +
        "\<\/a\>" +
        "\<\/li\>" +
        "\<\/ul\>" +
        "\<\/td\>" +
        "\<\/tr\>");
  };

  var unbindProvider = function (providerId) {
    document.getElementById('tr' + providerId).style.display = 'none';
    $('#tr' + providerId).find('input').val("");
  };

  var unbindEmployee = function (employeeId, employeeTableId) {
    $.ajax({
      type: 'POST',
      url: '/provider/0/edit/employees/' + employeeId + '/unbind',
      dataType: 'json',
      async: true,
      success: function () {
        //помечаем, что сотрудник отвязан
        document.getElementById('employees' + employeeTableId + '.isUnbound').value = true;
        //помечаем, что сотрудник больше не связан с фирмой
        document.getElementById('employees' + employeeTableId + '.producerId').value = "0";
        //скрываем строку в таблице
        document.getElementById('tr' + employeeId).style.display = 'none';
      },
      error: function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.status + ' ' + jqXHR.responseText);
      }
    });
  };

  var addNewEmployee = function (employeeCount, id, position, fullName, email, homePhoneNumber, workPhoneNumber) {
    $('#employeeTable').find('tbody')
        .append(
            "\<tr id=\"tr" + id + "\"\>" +

            "\<input id=\"employees" + employeeCount + ".isUnbound\"  name=\"employees[" + employeeCount + "].isUnbound\"   type=\"hidden\" value=\"false\"\/\>" +
            "\<input id=\"employees" + employeeCount + ".id\"         name=\"employees[" + employeeCount + "].id\"          type=\"hidden\" value=\"" + id + "\"\/\>" +
            "\<input id=\"employees" + employeeCount + ".providerId\" name=\"employees[" + employeeCount + "].providerId\"  type=\"hidden\" value=\"0\"\/\>" +
            "\<input id=\"employees" + employeeCount + ".producerId\" name=\"employees[" + employeeCount + "].producerId\"  type=\"hidden\" value=\"0\"\/\>" +

            "\<td>" +
            "\<input id=\"employees" + employeeCount + ".position\" name=\"employees[" + employeeCount + "].position\" class=\"form-control\" type=\"text\" value=\"" + position + "\"\/\>" +
            "\<span class=\"help-inline\" id=\"employees[" + "+++++" + "].position\"\>\<\/span\>" +
            "\</td\>" +

            "\<td\>" +
            "\<input id=\"employees" + employeeCount + ".fullName\" name=\"employees[" + employeeCount + "].fullName\" class=\"form-control\" type=\"text\" value=\"" + fullName + "\"\/\>" +
            "\<span class=\"help-inline\" id=\"employees[" + "+++++" + "].fullName\"\>\<\/span\>" +
            "\<\/td>" +

            "\<td>" +
            "\<input id=\"employees" + employeeCount + ".email\" name=\"employees[" + employeeCount + "].email\" class=\"form-control\" type=\"text\" value=\"" + email + "\"\/\>" +
            "\<span class=\"help-inline\" id=\"employees[" + "+++++" + "].email\"\>\<\/span\>" +
            "\<\/td\>" +

            "\<td\>" +
            "\<input id=\"employees" + employeeCount + ".workPhoneNumber\" name=\"employees[" + employeeCount + "].workPhoneNumber\" class=\"form-control\" type=\"text\" value=\"" + workPhoneNumber + "\"\/\>" +
            "\<span class=\"help-inline\" id=\"employees[" + "+++++" + "].workPhoneNumber\"\>\</span>" +
            "\</td>" +

            "\<td>" +
            "\<input id=\"employees" + employeeCount + ".homePhoneNumber\" name=\"employees[" + employeeCount + "].homePhoneNumber\" class=\"form-control\" type=\"text\" value=\"" + homePhoneNumber + "\"\/\>" +
            "\<span class=\"help-inline\" id=\"employees[" + "+++++" + "].homePhoneNumber\"\>\<\/span\>" +
            "\<\/td\>" +

            "\<td\>" +
            "\<ul class=\"nav nav-pills navbar-right\"\>" +
            "\<li class=\"default\"\>" +
            "\<a href=\"javascript:unbindEmployee('" + id + "', '" + employeeCount + "')\"\>" +
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
      $.post('/provider/' + '1' + '/edit/employees/validateNew', data, function (response) {
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
          var table = document.getElementById("employeeTable");//получаем элемент таблицы
          var employeeCount = table.rows.length - 1;//количество строк
          addNewEmployee(employeeCount, response.id, response.position, response.fullName, response.email, response.homePhoneNumber, response.workPhoneNumber);
        }
      }, 'json');
      e.preventDefault();
      return false;
    });

    var $producerForm = $('#producerForm');
    $producerForm.bind('submit', function (e) {
      var $inputs = $producerForm.find('input');
      var data = collectFormData($inputs);
      $.post('/producer/add', data, function (response) {
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
          $(location).attr('href', "/producer/" + response.id);
        }
      }, 'json');
      e.preventDefault();
      return false;
    });
  });

</script>
</body>
</html>