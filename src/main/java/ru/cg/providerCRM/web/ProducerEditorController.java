package ru.cg.providerCRM.web;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.entity.Producer;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.services.EmployeeService;
import ru.cg.providerCRM.services.ProducerService;
import ru.cg.providerCRM.services.ProviderService;
import ru.cg.providerCRM.web.form.*;
import ru.cg.providerCRM.web.response.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static ru.cg.providerCRM.services.FormToEntityConverter.EmployeeFormToEmployee;

@Controller
@Scope("request")
@RequestMapping(value = "/")
public class ProducerEditorController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/producer/add", method = RequestMethod.GET)
    public ModelAndView displayProducerRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("addNewProducerNew");
        ProducerForm form = new ProducerForm();

        form.setProducer(new ProducerInfoForm());
        form.setProviders(providerService.getAllProviders());


        modelAndView.addObject("producerForm", form);
        modelAndView.addObject("employeeForm", new EmployeeRegistrationForm());

        return modelAndView;
    }

    @RequestMapping(value = "/producer/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addNewProducer(@Valid ProducerForm form, BindingResult result) {
        if (result.hasErrors()) {
            ValidationResponse res = new ValidationResponse();
            res.setErrorMessageList(getErrorMessages(result.getFieldErrors()));
            res.setStatus("FAIL");
            return res;
        } else {
            ProducerResponse res = new ProducerResponse();

            Producer producer = new Producer();
            form.getProducer().fillProducer(producer);
            producerService.addProducer(producer);

            List<EmployeeEditForm> employees = form.getEmployees();
            if (employees != null) {
                for (EmployeeEditForm employeeEditForm : employees) {
                    if (!employeeEditForm.getIsUnbound()) {
                        employeeEditForm.setProducerId(producer.getId());
                        employeeService.updateEmployee(
                                EmployeeFormToEmployee(
                                        employeeEditForm, providerService, producerService
                                )
                        );
                    }
                }
            }

            List<Provider> providers = form.getProviders();
            List<Provider> providersProducer = new ArrayList<>();
            if (providers != null) {
                for (Provider provider : providers) {
                    if (provider.getId() != null) {
                        providersProducer.add(providerService.getById(provider.getId()));
                    }
                }
            }

            producer.setProviders(providersProducer);
            producerService.updateProducer(producer);

            res.setId(producer.getId().toString());
            res.setStatus("SUCCESS");
            return res;
        }
    }

    @RequestMapping(value = "/producer/{producerId}", method = RequestMethod.GET)
    public ModelAndView producerFullInfo(@PathVariable("producerId") Long producerId) {
        ModelAndView modelAndView = new ModelAndView("producerInfo");

        Producer producer = producerService.getById(producerId);

        ProducerForm form = new ProducerForm();
        form.setEmployees(getEmployeeEditForms(producer));
        form.setProducer(new ProducerInfoForm(producer));
        form.setProviders(producer.getProviders());

        modelAndView.addObject("employeeForm", new EmployeeRegistrationForm());
        modelAndView.addObject("producerForm", form);
        return modelAndView;
    }

    @RequestMapping(value = "/producer/{producerId}/edit", method = RequestMethod.GET)
    public ModelAndView enableEditingMode(@PathVariable("producerId") Long producerId) {
        ModelAndView modelAndView = new ModelAndView("producerEditor");
        Producer producer = producerService.getById(producerId);

        ProducerForm form = new ProducerForm();
        form.setEmployees(getEmployeeEditForms(producer));
        form.setProducer(new ProducerInfoForm(producer));
        form.setProviders(producer.getProviders());

        modelAndView.addObject("employeeForm", new EmployeeRegistrationForm());
        modelAndView.addObject("producerForm", form);

        modelAndView.addObject("otherProviders", ListUtils.subtract(providerService.getAllProviders(), producer.getProviders()));
        return modelAndView;
    }

    @RequestMapping(value = "/producer/{producerId}/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response ajaxProducerEditingFormValidator(@PathVariable("producerId") Long producerId,
                                                     @ModelAttribute("producerForm") @Valid ProducerForm form,
                                                     BindingResult result) {
        if (result.hasErrors()) {
            ValidationResponse res = new ValidationResponse();
            res.setErrorMessageList(getErrorMessages(result.getFieldErrors()));
            return res;
        } else {
            Response res = new Response();

            Producer producer = producerService.getById(producerId);
            form.getProducer().fillProducer(producer);
            producerService.updateProducer(producer);

            List<EmployeeEditForm> employeeEditForms = form.getEmployees();
            if (employeeEditForms != null) {
                for (EmployeeEditForm employeeEditForm : employeeEditForms) {
                    if (!employeeEditForm.getIsUnbound()) {
                        employeeService.updateEmployee(
                                EmployeeFormToEmployee(
                                        employeeEditForm, providerService, producerService
                                )
                        );
                    }
                }
            }

            res.setStatus("SUCCESS");
            return res;
        }
    }

    @RequestMapping(value = "producer/{producerId}/edit/employees/validateNew", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse validateEmployeeForm(@PathVariable("producerId") Long producerId,
                                                   @ModelAttribute("employeeForm") @Valid EmployeeRegistrationForm form,
                                                   BindingResult result) {
        ValidationResponse res = new ValidationResponse();
        if (result.hasErrors()) {
            res.setStatus("FAIL");
            res.setErrorMessageList(getErrorMessages(result.getFieldErrors()));
        } else {
            Employee employee = new Employee();
            form.fillEmployee(employee);
            employee.setProducer(producerService.getById(producerId));
            employeeService.addEmployee(employee);
            res = new EmployeeResponse(employee);
            res.setStatus("SUCCESS");
        }
        return res;
    }

    @RequestMapping(value = "/producer/{producerId}/edit/employees/{employeeId}/unbind", method = RequestMethod.POST)
    @ResponseBody
    public Response unbindEmployee(@PathVariable("producerId") Long producerId,
                                   @PathVariable("employeeId") Long employeeId) {
        Response res = new Response();
        Employee employee = employeeService.getById(employeeId);
        employee.setProducer(null);
        employeeService.updateEmployee(employee);
        res.setStatus("SUCCESS");
        return res;
    }

    @RequestMapping(value = "/producer/{producerId}/edit/getOtherProviders", method = RequestMethod.POST)
    @ResponseBody
    public List<ProviderInfoForm> getProviders(@PathVariable("producerId") Long producerId) {
        Producer producer = producerService.getById(producerId);

        List<Provider> allProviders = providerService.getAllProviders();
        List<ProviderInfoForm> allProvidersInfo = new ArrayList<ProviderInfoForm>();

        for (Provider provider : allProviders) {
            allProvidersInfo.add(new ProviderInfoForm(provider));
        }

        List<Provider> producerAllProviders = producer.getProviders();
        List<ProviderInfoForm> producerAllProvidersInfo = new ArrayList<ProviderInfoForm>();

        for (Provider provider : producerAllProviders) {
            producerAllProvidersInfo.add(new ProviderInfoForm(provider));
        }

        List<ProviderInfoForm> providers = ListUtils.subtract(allProvidersInfo, producerAllProvidersInfo);
        return providers;
    }

    @RequestMapping(value = "/producer/{producerId}/edit/providers/{providerId}", method = RequestMethod.POST)
    @ResponseBody
    public ProviderResponse selectProvider(@PathVariable("producerId") Long producerId,
                                         @PathVariable("providerId") Long providerId) {
        Provider selectedProvider = providerService.getById(providerId);
        ProviderResponse res = new ProviderResponse(selectedProvider);
        res.setStatus("SUCCESS");
        return res;
    }

    @RequestMapping(value = "/producer/{producerId}/edit/providers/add/{providerId}", method = RequestMethod.POST)
    @ResponseBody
    public ProviderResponse bindProduct(@PathVariable("producerId") Long producerId ,
                                       @PathVariable("providerId") Long providerId) {
        Provider provider = providerService.getById(providerId);
        Producer producer = producerService.getById(producerId);
        producer.getProviders().add(provider);
        producerService.updateProducer(producer);

        ProviderResponse res = new ProviderResponse(provider);
        res.setStatus("SUCCESS");
        return res;
    }

    @RequestMapping(value = "/producer/{producerId}/edit/providers/edit/{providerId}/unbind", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse unbindProduct(@PathVariable("producerId") Long producerId,
                                            @PathVariable("providerId") Long providerId) {
        ValidationResponse res = new ValidationResponse();
        Producer producer = producerService.getById(producerId);
        Provider provider = providerService.getById(providerId);
        producer.getProviders().remove(provider);
        producerService.updateProducer(producer);
        res.setStatus("SUCCESS");
        return res;
    }


    private List<ErrorMessage> getErrorMessages(List<FieldError> allErrors) {
        List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
        for (FieldError objectError : allErrors) {
            errorMessages.add(
                    new ErrorMessage(
                            objectError.getField().replace("[", "\\[")
                                    .replace("]", "\\]")
                                    .replace(".", "\\."),
                            objectError.getDefaultMessage()
                    )
            );
        }
        return errorMessages;
    }

    private List<EmployeeEditForm> getEmployeeEditForms(Producer producer) {
        List<EmployeeEditForm> employeeForms = new ArrayList<>();
        List<Employee> employees = producer.getEmployees();

        for (Employee employee : employees) {
            employeeForms.add(new EmployeeEditForm(employee));
        }

        return employeeForms;
    }
}
