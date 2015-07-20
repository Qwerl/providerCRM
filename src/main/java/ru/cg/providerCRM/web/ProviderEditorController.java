package ru.cg.providerCRM.web;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.cg.providerCRM.entity.*;
import ru.cg.providerCRM.services.EmployeeService;
import ru.cg.providerCRM.services.ProductService;
import ru.cg.providerCRM.services.ProviderService;
import ru.cg.providerCRM.services.TagService;
import ru.cg.providerCRM.web.form.EmployeeForm;
import ru.cg.providerCRM.web.form.ProviderForm;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;

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

    @InitBinder
    public void initBinder(WebDataBinder b) {
        b.registerCustomEditor(Tag.class, new TagEditor());
        b.registerCustomEditor(Product.class, new ProductEditor());
    }

    @RequestMapping(value = "/provider/add", method = RequestMethod.GET)
    public ModelAndView displayProviderRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("addNewProvider");
        modelAndView.addObject("tags", tagService.getAllTags());
        modelAndView.addObject("provider", new Provider());
        return modelAndView;
    }

    @RequestMapping(value = "/provider/add", method = RequestMethod.POST)
    public ModelAndView addNewProvider(@Valid Provider validProvider, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("addNewProvider");
            modelAndView.addObject("tags", tagService.getAllTags());
            return modelAndView;
        } else {
            Provider provider = validProvider;
            providerService.addProvider(provider);
            return new ModelAndView("redirect:/provider/" + provider.getId());
        }
    }

    @RequestMapping(value = "/provider/{providerId:.+}", method = RequestMethod.GET)
    public ModelAndView providerFullInfo(@PathVariable("providerId") String providerId) {
        ModelAndView modelAndView = new ModelAndView("providerEditor");
        Provider provider = providerService.getById(Long.parseLong(providerId));
        modelAndView.addObject("providerForm", new ProviderForm(provider));
        addEmployees(modelAndView, providerId);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit", method = RequestMethod.GET)
    public ModelAndView editProviderForm(@PathVariable("providerId") String providerId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        Provider provider = providerService.getById(Long.parseLong(providerId));
        modelAndView.addObject("providerForm", new ProviderForm(provider));
        modelAndView.addObject("providerEditing", true);
        modelAndView.addObject("otherProducts", ListUtils.subtract(productService.getAllProduct(), provider.getProducts()));
        modelAndView.addObject("otherTags", ListUtils.subtract(tagService.getAllTags(), provider.getTags()));
        addEmployees(modelAndView, providerId);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit", method = RequestMethod.POST)
    public ModelAndView updateProvider(@PathVariable("providerId") String providerId,
                                       @Valid ProviderForm providerForm,
                                       BindingResult result) {
        providerForm.setId(Long.parseLong(providerId));
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("providerEditor");
            modelAndView.addObject("providerEditing", true);
            if (providerForm.getProducts() == null){
                providerForm.setProducts(ListUtils.EMPTY_LIST);
            }
            modelAndView.addObject("otherProducts", ListUtils.subtract(productService.getAllProduct(), providerForm.getProducts()));            if (providerForm.getTags() == null){
                providerForm.setTags(ListUtils.EMPTY_LIST);
            }
            modelAndView.addObject("otherTags", ListUtils.subtract(tagService.getAllTags(), providerForm.getTags()));
            addEmployees(modelAndView, providerId);
            return modelAndView;
        } else {
            Provider provider = providerService.getById(Long.parseLong(providerId));
            providerForm.fillProvider(provider);
            providerService.updateProvider(provider);
            return new ModelAndView("redirect:/provider/" + provider.getId());
        }
    }


    @RequestMapping(value = "/provider/{providerId:.+}/edit/addEmployee", method = RequestMethod.GET)
    public ModelAndView getPermissionToAddEmployeeToProvider(@PathVariable("providerId") String providerId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        modelAndView.addObject("employeeWritePermission", true);
        modelAndView.addObject("employeeForm", new EmployeeForm());
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/addEmployee", method = RequestMethod.POST)
    public ModelAndView addEmployeeToProvider(@PathVariable("providerId") String providerId,
                                              @Valid EmployeeForm employeeForm,
                                              BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = providerFullInfo(providerId);
            modelAndView.addObject("employeeWritePermission", true);
            return modelAndView;
        } else {
            Employee employee = new Employee();
            employeeForm.fillEmployee(employee);
            employee.setProvider(providerService.getById(Long.parseLong(providerId)));
            employeeService.addEmployee(employee);
            return new ModelAndView("redirect:/provider/" + providerId);
        }
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee", method = RequestMethod.GET)
    public ModelAndView enableEmployeesEditing(@PathVariable("providerId") String providerId) {
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
        modelAndView.addObject("employeeForm", new EmployeeForm(employeeService.getById(Long.parseLong(employeeId))));
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee/{employeeId}/delete", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(@PathVariable("providerId") String providerId,
                                       @PathVariable("employeeId") String employeeId) {
        providerService.deleteEmployee(Long.parseLong(employeeId), Long.parseLong(providerId));
        return new ModelAndView("redirect:/provider/" + providerId + "/edit/employee");
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee/{employeeId:.+}", method = RequestMethod.POST)
    public ModelAndView updateEmployee(@PathVariable("providerId") String providerId,
                                       @PathVariable("employeeId") String employeeId,
                                       @Valid EmployeeForm employeeForm,
                                       BindingResult result) {
        employeeForm.setId(Long.parseLong(employeeId));
        if (result.hasErrors()) {
            ModelAndView modelAndView = providerFullInfo(providerId);
            modelAndView.addObject("employeeWritePermission", false);
            modelAndView.addObject("editableEmployeeId", employeeId);
            return modelAndView;
        } else {
            Employee employee = employeeService.getById(Long.parseLong(employeeId));
            employeeForm.fillEmployee(employee);
            employee.setId(Long.parseLong(employeeId));
            employee.setProvider(providerService.getById(Long.parseLong(providerId)));
            employeeService.updateEmployee(employee);
            return new ModelAndView("redirect:/provider/" + providerId);
        }
    }

    private void addEmployees(ModelAndView modelAndView, String providerId) {
        Provider provider = providerService.getById(Long.parseLong(providerId));
        modelAndView.addObject("employees", provider.getEmployees());
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

    private class ProductEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(productService.getByName(text));
        }

        @Override
        public String getAsText() {
            return ((Product) getValue()).getName();
        }

    }
}
