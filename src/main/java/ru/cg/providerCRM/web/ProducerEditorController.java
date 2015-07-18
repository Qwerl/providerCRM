package ru.cg.providerCRM.web;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.entity.Producer;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.entity.Tag;
import ru.cg.providerCRM.services.EmployeeService;
import ru.cg.providerCRM.services.ProducerService;
import ru.cg.providerCRM.services.ProviderService;
import ru.cg.providerCRM.services.TagService;
import ru.cg.providerCRM.web.form.EmployeeForm;
import ru.cg.providerCRM.web.form.ProducerForm;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;

@Controller
@Scope("request")
@RequestMapping(value = "/")
public class ProducerEditorController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private EmployeeService employeeService;

    @InitBinder
    public void initBinder(WebDataBinder b) {
        b.registerCustomEditor(Tag.class, new TagEditor());
        b.registerCustomEditor(Provider.class, new ProviderEditor());
    }

    @RequestMapping(value = "/producer/add", method = RequestMethod.GET)
    public ModelAndView displayProducerRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("addNewProducer");
        modelAndView.addObject("tags", tagService.getAllTags());
        modelAndView.addObject("producer", new Producer());
        return modelAndView;
    }

    @RequestMapping(value = "/producer/add", method = RequestMethod.POST)
    public ModelAndView addNewProducer(@Valid Producer validProducer, BindingResult result) {

        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("addNewProducer");
            modelAndView.addObject("tags", tagService.getAllTags());
            return modelAndView;
        } else {
            Producer producer = validProducer;
            producerService.addProducer(producer);
            return new ModelAndView("redirect:/producer/" + producer.getId());
        }
    }

    @RequestMapping(value = "/producer/{producerId:.+}", method = RequestMethod.GET)
    public ModelAndView producerFullInfo(@PathVariable("producerId") String producerId) {
        ModelAndView modelAndView = new ModelAndView("producerEditor");
        Producer producer = producerService.getById(Long.parseLong(producerId));
        modelAndView.addObject("producerForm", new ProducerForm(producer));
        addEmployees(modelAndView, producerId);
        return modelAndView;
    }

    @RequestMapping(value = "/producer/{producerId:.+}/edit", method = RequestMethod.GET)
    public ModelAndView displayProducerForm(@PathVariable("producerId") String producerId) {
        ModelAndView modelAndView = new ModelAndView("producerEditor");
        Producer producer = producerService.getById(Long.parseLong(producerId));
        modelAndView.addObject("producerForm", new ProducerForm(producer));
        modelAndView.addObject("producerEditing", true);
        modelAndView.addObject("otherTags", ListUtils.subtract(tagService.getAllTags(), producer.getTags()));
        modelAndView.addObject("otherProviders", ListUtils.subtract(providerService.getAllProviders(), producer.getProviders()));
        addEmployees(modelAndView, producerId);
        return modelAndView;
    }

    @RequestMapping(value = "/producer/{producerId:.+}/edit", method = RequestMethod.POST)
    public ModelAndView updateProducer(@PathVariable("producerId") String producerId,
                                       @Valid ProducerForm producerForm,
                                       BindingResult result) {
        producerForm.setId(Long.parseLong(producerId));
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("producerEditor");
            modelAndView.addObject("producerEditing", true);
            if (producerForm.getProviders() == null) {
                producerForm.setProviders(ListUtils.EMPTY_LIST);
            }
            modelAndView.addObject("otherProviders", ListUtils.subtract(providerService.getAllProviders(), producerForm.getProviders()));
            if (producerForm.getTags() == null) {
                producerForm.setTags(ListUtils.EMPTY_LIST);
            }
            modelAndView.addObject("otherTags", ListUtils.subtract(tagService.getAllTags(), producerForm.getTags()));
            addEmployees(modelAndView, producerId);
            return modelAndView;
        } else {
            Producer producer = producerService.getById(Long.parseLong(producerId));
            producerForm.fillProducer(producer);
            producerService.updateProducer(producer);
            return new ModelAndView("redirect:/producer/" + producer.getId());
        }
    }

    @RequestMapping(value = "/producer/{producerId:.+}/edit/addEmployee", method = RequestMethod.GET)
    public ModelAndView getPermissionToAddEmployeeToProducer(@PathVariable("producerId") String producerId) {
        ModelAndView modelAndView = producerFullInfo(producerId);
        modelAndView.addObject("employeeWritePermission", true);
        modelAndView.addObject("employeeForm", new EmployeeForm());
        return modelAndView;
    }

    @RequestMapping(value = "producer/{producerId:.+}/edit/addEmployee", method = RequestMethod.POST)
    public ModelAndView addEmployeeToProducer(@PathVariable("producerId") String producerId,
                                              @Valid EmployeeForm employeeForm,
                                              BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = producerFullInfo(producerId);
            modelAndView.addObject("employeeWritePermission", true);
            return modelAndView;
        } else {
            Employee employee = new Employee();
            employeeForm.fillEmployee(employee);
            employee.setProducer(producerService.getById(Long.parseLong(producerId)));
            employeeService.addEmployee(employee);
            return new ModelAndView("redirect:/producer/" + producerId);
        }
    }

    @RequestMapping(value = "/producer/{producerId:.+}/edit/employee", method = RequestMethod.GET)
    public ModelAndView enableEditing(@PathVariable("producerId") String producerId) {
        ModelAndView modelAndView = producerFullInfo(producerId);
        modelAndView.addObject("employeeWritePermission", false);
        modelAndView.addObject("employeesEditing", true);
        return modelAndView;
    }

    @RequestMapping(value = "/producer/{producerId:.+}/edit/employee/{employeeId}", method = RequestMethod.GET)
    public ModelAndView selectEmployeeToEdit(@PathVariable("producerId") String producerId,
                                             @PathVariable("employeeId") String employeeId) {
        ModelAndView modelAndView = producerFullInfo(producerId);
        modelAndView.addObject("employeeWritePermission", false);
        modelAndView.addObject("editableEmployeeId", employeeId);
        modelAndView.addObject("employeeForm", new EmployeeForm(employeeService.getById(Long.parseLong(employeeId))));
        return modelAndView;
    }

    @RequestMapping(value = "/producer/{producerId:.+}/edit/employee/{employeeId}/delete", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(@PathVariable("producerId") String producerId,
                                       @PathVariable("employeeId") String employeeId) {
        producerService.deleteEmployee(Long.parseLong(employeeId), Long.parseLong(producerId));
        return new ModelAndView("redirect:/producer/" + producerId + "/edit/employee");
    }

    @RequestMapping(value = "/producer/{producerId:.+}/edit/employee/{employeeId}", method = RequestMethod.POST)
    public ModelAndView updateEmployee(@PathVariable("producerId") String producerId,
                                       @PathVariable("employeeId") String employeeId,
                                       @Valid EmployeeForm employeeForm,
                                       BindingResult result) {
        employeeForm.setId(Long.parseLong(employeeId));
        if (result.hasErrors()) {
            ModelAndView modelAndView = producerFullInfo(producerId);
            modelAndView.addObject("employeeWritePermission", false);
            modelAndView.addObject("editableEmployeeId", employeeId);
            return modelAndView;
        } else {
            Employee employee = employeeService.getById(Long.parseLong(employeeId));
            employeeForm.fillEmployee(employee);
            employee.setId(Long.parseLong(employeeId));
            employee.setProducer(producerService.getById(Long.parseLong(producerId)));
            employeeService.updateEmployee(employee);
            return new ModelAndView("redirect:/producer/" + producerId);
        }
    }

    private void addEmployees(ModelAndView modelAndView, String producerId) {
        Producer producer = producerService.getById(Long.parseLong(producerId));
        modelAndView.addObject("employees", producer.getEmployees());
    }

    //todo
    private class TagEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(tagService.getByName(text));
        }

        @Override
        public String getAsText() {
            return ((Tag) getValue()).getTagText();
        }

    }

    private class ProviderEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            System.out.println("sat");
            setValue(providerService.getByName(text));
        }

        @Override
        public String getAsText() {
            System.out.println("gat");
            return ((Provider) getValue()).getName();
        }

    }
}
