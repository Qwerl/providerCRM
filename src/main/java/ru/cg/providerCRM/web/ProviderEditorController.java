package ru.cg.providerCRM.web;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.entity.Product;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.entity.Tag;
import ru.cg.providerCRM.services.EmployeeService;
import ru.cg.providerCRM.services.ProductService;
import ru.cg.providerCRM.services.ProviderService;
import ru.cg.providerCRM.services.TagService;

import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("request")
@RequestMapping(value = "/")
public class ProviderEditorController {

    @Autowired
    public ProviderService providerService;

    @Autowired
    public EmployeeService employeeService;

    @Autowired
    public TagService tagService;

    @Autowired
    public ProductService productService;

    @RequestMapping(value = "/provider/add", method = RequestMethod.GET)
    public ModelAndView newProviderForm() {
        ModelAndView modelAndView = new ModelAndView("addNewProvider");
        modelAndView.addObject("tags", tagService.getAllTags());
        return modelAndView;
    }

    @RequestMapping(value = "/provider/add", method = RequestMethod.POST)
    public ModelAndView addNewProvider(@RequestParam(value = "name") String name,
                                       @RequestParam(value = "address") String address,
                                       @RequestParam(value = "storageAddress") String storageAddress,
                                       @RequestParam(value = "phoneNumber") String phoneNumber,
                                       @RequestParam(value = "note") String note,
                                       @RequestParam(value = "tags") List<String> tags) {
        List<Tag> tagsList = new ArrayList<>();
        for (String tag : tags) {
            tagsList.add(tagService.getById(Long.parseLong(tag)));
        }
        Provider provider = new Provider();
        provider.setName(name);
        provider.setAddress(address);
        provider.setStorageAddress(storageAddress);
        provider.setPhoneNumber(phoneNumber);
        provider.setNote(note);
        provider.setTags(tagsList);
        providerService.addProvider(provider);
        return new ModelAndView("redirect:/provider/" + provider.getId());
    }

    @RequestMapping(value = "/provider/{providerId:.+}", method = RequestMethod.GET)
    public ModelAndView providerFullInfo(@PathVariable("providerId") String providerId) {
        ModelAndView modelAndView = new ModelAndView("providerEditor");
        Provider provider = providerService.getById(Long.parseLong(providerId));
        modelAndView.addObject("employees", provider.getEmployees());
        modelAndView.addObject("providerInfo", provider);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit", method = RequestMethod.GET)
    public ModelAndView editProviderForm(@PathVariable("providerId") String providerId) {
        ModelAndView modelAndView = new ModelAndView("providerEditor");
        Provider provider = providerService.getById(Long.parseLong(providerId));
        modelAndView.addObject("providerInfo", provider);
        modelAndView.addObject("providerEditing", true);
        modelAndView.addObject("otherTags", ListUtils.subtract(tagService.getAllTags(), provider.getTags()));
        modelAndView.addObject("otherProducts", ListUtils.subtract(productService.getAllProduct(), provider.getProducts()));
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit", method = RequestMethod.POST)
    public ModelAndView editProvider(@PathVariable("providerId") String providerId,
                                     @RequestParam(value = "providerName") String name,
                                     @RequestParam(value = "providerAddress") String address,
                                     @RequestParam(value = "providerStorageAddress") String storageAddress,
                                     @RequestParam(value = "providerPhoneNumber") String phoneNumber,
                                     @RequestParam(value = "providerNote") String note,
                                     @RequestParam(value = "tags") List<String> tags,
                                     @RequestParam(value = "products") List<String> products) {
        List<Product> productList = new ArrayList<>(products.size());
        for (String product : products) {
            productList.add(productService.getById(Long.parseLong(product)));
        }
        List<Tag> tagList = new ArrayList<>(tags.size());
        for (String tag : tags) {
            tagList.add(tagService.getById(Long.parseLong(tag)));
        }
        Provider provider = providerService.getById(Long.parseLong(providerId));
        provider.setName(name);
        provider.setAddress(address);
        provider.setStorageAddress(storageAddress);
        provider.setPhoneNumber(phoneNumber);
        provider.setNote(note);
        provider.setTags(tagList);
        provider.setProducts(productList);
        providerService.updateProvider(provider);
        return new ModelAndView("redirect:/provider/" + provider.getId());
    }


    @RequestMapping(value = "/provider/{providerId:.+}/edit/addEmployee", method = RequestMethod.GET)
    public ModelAndView getPermissionToAddEmployeeToProvider(@PathVariable("providerId") String providerId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        modelAndView.addObject("employeeWritePermission", true);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/addEmployee", method = RequestMethod.POST)
    public ModelAndView addEmployeeToProvider(@PathVariable("providerId") String providerId,
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
        employee.setProvider(providerService.getById(Long.parseLong(providerId)));
        employeeService.addEmployee(employee);

        return new ModelAndView("redirect:/provider/" + providerId);
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee", method = RequestMethod.GET)
    public ModelAndView enableEditing(@PathVariable("providerId") String providerId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        modelAndView.addObject("employeeWritePermission", false);
        modelAndView.addObject("employeesEditing", true);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee/{employeeId}", method = RequestMethod.GET)
    public ModelAndView selectEmployeeToEdit(@PathVariable("providerId") String providerId,
                                             @PathVariable("employeeId") String employeeId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        modelAndView.addObject("employeeWritePermission", false);
        modelAndView.addObject("editableEmployeeId", employeeId);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee/{employeeId}/delete", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(@PathVariable("providerId") String providerId,
                                       @PathVariable("employeeId") String employeeId) {
        providerService.deleteEmployee(Long.parseLong(employeeId), Long.parseLong(providerId));
        return new ModelAndView("redirect:/provider/" + providerId + "/edit/employee");
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee/{employeeId}", method = RequestMethod.POST)
    public ModelAndView updateEmployee(@PathVariable("providerId") String providerId,
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
        return new ModelAndView("redirect:/provider/" + providerId);
    }


}
