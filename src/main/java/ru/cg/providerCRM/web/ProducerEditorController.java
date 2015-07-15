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

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

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
        modelAndView.addObject("producerInfo", producer);
        return modelAndView;
    }

    @RequestMapping(value = "/producer/{producerId:.+}/edit", method = RequestMethod.GET)
    public ModelAndView editProducerForm(@PathVariable("producerId") String producerId) {
        ModelAndView modelAndView = new ModelAndView("producerEditor");
        Producer producer = producerService.getById(Long.parseLong(producerId));
        modelAndView.addObject("producerInfo", producer);
        modelAndView.addObject("producerEditing", true);
        modelAndView.addObject("otherTags", ListUtils.subtract(tagService.getAllTags(), producer.getTags()));
        modelAndView.addObject("otherProviders", ListUtils.subtract(providerService.getAllProviders(), producer.getProviders()));
        return modelAndView;
    }

    @RequestMapping(value = "/producer/{producerId:.+}/edit", method = RequestMethod.POST)
    public ModelAndView editProducer(@PathVariable("producerId") String producerId,
                                     @RequestParam(value = "producerName") String name,
                                     @RequestParam(value = "producerAddress") String address,
                                     @RequestParam(value = "producerPhoneNumber") String phoneNumber,
                                     @RequestParam(value = "producerNote") String note,
                                     @RequestParam(value = "providers") List<String> providers,
                                     @RequestParam(value = "tags") List<String> tags) {
        List<Provider> providerList = new ArrayList<>(providers.size());
        for (String provider : providers) {
            providerList.add(providerService.getById(Long.parseLong(provider)));
        }
        List<Tag> tagList = new ArrayList<>(tags.size());
        for (String tag : tags) {
            tagList.add(tagService.getById(Long.parseLong(tag)));
        }
        Producer producer = producerService.getById(Long.parseLong(producerId));
        producer.setName(name);
        producer.setAddress(address);
        producer.setPhoneNumber(phoneNumber);
        producer.setNote(note);
        producer.setProviders(providerList);
        producer.setTags(tagList);
        producerService.updateProducer(producer);
        return new ModelAndView("redirect:/producer/" + producer.getId());
    }

    @RequestMapping(value = "/producer/{producerId:.+}/edit/addEmployee", method = RequestMethod.GET)
    public ModelAndView getPermissionToAddEmployeeToProducer(@PathVariable("producerId") String producerId) {
        ModelAndView modelAndView = producerFullInfo(producerId);
        modelAndView.addObject("employeeWritePermission", true);
        return modelAndView;
    }

    @RequestMapping(value = "producer/{producerId:.+}/edit/addEmployee", method = RequestMethod.POST)
    public ModelAndView addEmployeeToProducer(@PathVariable("producerId") String producerId,
                                              @RequestParam(value = "position") String position,
                                              @RequestParam(value = "fullName") String fullName,
                                              @RequestParam(value = "email") String email,
                                              @RequestParam(value = "workPhoneNumber") String workPhoneNumber,
                                              @RequestParam(value = "homePhoneNumber") String homePhoneNumber) {
        Employee employee = new Employee();
        employee.setPosition(position);
        employee.setFullName(fullName);
        employee.setEmail(email);
        employee.setWorkPhoneNumber(workPhoneNumber);
        employee.setHomePhoneNumber(homePhoneNumber);
        employee.setProducer(producerService.getById(Long.parseLong(producerId)));
        employeeService.addEmployee(employee);

        return new ModelAndView("redirect:/producer/" + producerId);
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
                                       @RequestParam(value = "position") String position,
                                       @RequestParam(value = "fullName") String fullName,
                                       @RequestParam(value = "email") String email,
                                       @RequestParam(value = "workPhoneNumber") String workPhoneNumber,
                                       @RequestParam(value = "homePhoneNumber") String homePhoneNumber) {
        Employee employee = employeeService.getById(Long.parseLong(employeeId));
        employee.setPosition(position);
        employee.setFullName(fullName);
        employee.setEmail(email);
        employee.setWorkPhoneNumber(workPhoneNumber);
        employee.setHomePhoneNumber(homePhoneNumber);
        employeeService.updateEmployee(employee);
        return new ModelAndView("redirect:/producer/" + producerId);
    }

    //todo
    private class TagEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            System.out.println(tagService);
            setValue(tagService.getByName(text));
        }

        @Override
        public String getAsText() {
            return ((Tag) getValue()).getTagText();
        }

    }

}
