package ru.cg.providerCRM.web;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.entity.Product;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.services.EmployeeService;
import ru.cg.providerCRM.services.ProductService;
import ru.cg.providerCRM.services.ProviderService;
import ru.cg.providerCRM.validator.ErrorMessage;
import ru.cg.providerCRM.validator.ValidationResponse;
import ru.cg.providerCRM.web.form.EmployeeEditForm;
import ru.cg.providerCRM.web.form.EmployeeRegistrationForm;
import ru.cg.providerCRM.web.form.ProductRegisterForm;
import ru.cg.providerCRM.web.form.ProviderForm;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
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
    public ProductService productService;

    @InitBinder
    public void initBinder(WebDataBinder b) {
        b.registerCustomEditor(Product.class, new ProductEditor());
    }

    @RequestMapping(value = "/provider/add", method = RequestMethod.GET)
    public ModelAndView displayProviderRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("addNewProvider");
        modelAndView.addObject("providerForm", new ProviderForm());
        return modelAndView;
    }

    @RequestMapping(value = "/provider/add", method = RequestMethod.POST)
    public ModelAndView addNewProvider(@Valid ProviderForm providerForm, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("addNewProvider");
            return modelAndView;
        } else {
            Provider provider = new Provider();
            providerForm.fillProvider(provider);
            providerService.addProvider(provider);
            return new ModelAndView("redirect:/provider/" + provider.getId());
        }
    }

    @RequestMapping(value = "/provider/{providerId:.+}", method = RequestMethod.GET)
    public ModelAndView providerFullInfo(@PathVariable("providerId") Long providerId) {
        ModelAndView modelAndView = new ModelAndView("providerEditor");
        Provider provider = providerService.getById(providerId);
        modelAndView.addObject("providerForm", new ProviderForm(provider));
        addEmployees(modelAndView, providerId);
        addProducts(modelAndView, providerId);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit", method = RequestMethod.GET)
    public ModelAndView enableEditingMode(@PathVariable("providerId") Long providerId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        Provider provider = providerService.getById(providerId);
        modelAndView.addObject("providerForm", new ProviderForm(provider));
        modelAndView.addObject("editingMode", true);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/info", method = RequestMethod.GET)
    public ModelAndView editProviderForm(@PathVariable("providerId") Long providerId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        Provider provider = providerService.getById(providerId);
        modelAndView.addObject("providerEditing", true);
        modelAndView.addObject("providerForm", new ProviderForm(provider));
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/info", method = RequestMethod.POST)
    public ModelAndView updateProvider(@PathVariable("providerId") Long providerId,
                                       @ModelAttribute(value = "providerForm") @Valid ProviderForm providerForm,
                                       BindingResult result) {
        providerForm.setId(providerId);
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("providerEditor");
            modelAndView.addObject("providerEditing", true);
            addProducts(modelAndView, providerId);
            addEmployees(modelAndView, providerId);
            return modelAndView;
        } else {
            Provider provider = providerService.getById(providerId);
            providerForm.fillProvider(provider);
            providerService.updateProvider(provider);
            return new ModelAndView("redirect:/provider/" + provider.getId());
        }
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/addEmployee", method = RequestMethod.GET)
    public ModelAndView enableEmployeeAddingMode(@PathVariable("providerId") Long providerId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        modelAndView.addObject("employeeAddingMode", true);
        modelAndView.addObject("employeeForm", new EmployeeRegistrationForm());
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/addEmployee", method = RequestMethod.POST)
    public ModelAndView addEmployeeToProvider(@PathVariable("providerId") Long providerId,
                                              @ModelAttribute("employeeForm") @Valid EmployeeRegistrationForm employeeRegistrationForm,
                                              BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = providerFullInfo(providerId);
            modelAndView.addObject("employeeAddingMode", true);
            return modelAndView;
        } else {
            Employee employee = new Employee();
            employeeRegistrationForm.fillEmployee(employee);
            employee.setProvider(providerService.getById(providerId));
            employeeService.addEmployee(employee);
            return new ModelAndView("redirect:/provider/" + providerId);
        }
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee", method = RequestMethod.GET)
    public ModelAndView enableEmployeesEditing(@PathVariable("providerId") Long providerId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        modelAndView.addObject("employeesEditing", true);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee/{employeeId}", method = RequestMethod.GET)
    public ModelAndView selectEmployeeToEdit(@PathVariable("providerId") Long providerId,
                                             @PathVariable("employeeId") String employeeId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        modelAndView.addObject("employeesEditing", true);
        modelAndView.addObject("editableEmployeeId", employeeId);
        modelAndView.addObject("employeeForm", new EmployeeEditForm(employeeService.getById(Long.parseLong(employeeId))));
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee/{employeeId}/delete", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(@PathVariable("providerId") Long providerId,
                                       @PathVariable("employeeId") Long employeeId) {
        providerService.deleteEmployee(employeeId, providerId);
        return new ModelAndView("redirect:/provider/" + providerId + "/edit/employee");
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/employee/{employeeId:.+}", method = RequestMethod.POST)
    public ModelAndView updateEmployee(@PathVariable("providerId") Long providerId,
                                       @PathVariable("employeeId") Long employeeId,
                                       @ModelAttribute("employeeForm") @Valid EmployeeEditForm employeeForm,
                                       BindingResult result) {
        employeeForm.setId(employeeId);
        if (result.hasErrors()) {
            ModelAndView modelAndView = providerFullInfo(providerId);
            modelAndView.addObject("employeeAddingMode", false);
            modelAndView.addObject("editableEmployeeId", employeeId);
            return modelAndView;
        } else {
            Employee employee = employeeForm.getEmployee();
            employee.setProvider(providerService.getById(providerId));
            employeeService.updateEmployee(employee);
            return new ModelAndView("redirect:/provider/" + providerId);
        }
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/products", method = RequestMethod.GET)
    public ModelAndView enableProductsEditing(@PathVariable("providerId") Long providerId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        modelAndView.addObject("productEditing", true);
        modelAndView.addObject("productForm", new ProductRegisterForm());
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/products/edit", method = RequestMethod.GET)
    public ModelAndView editProduct(@PathVariable("providerId") Long providerId) {
        ModelAndView modelAndView = enableProductsEditing(providerId);
        modelAndView.addObject("productUnbindingMode", true);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/products/edit/{productId}", method = RequestMethod.POST)
    @ResponseBody
    public Object unbindProduct(@PathVariable("providerId") Long providerId,
                                @PathVariable("productId") Long productId) {
        providerService.deleteProduct(productId, providerId);
        return new ErrorMessage("status", "SUCCESS");
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/products/validateNew", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse processProductForm(@PathVariable("providerId") Long providerId,
                                                 @ModelAttribute("productForm") @Valid ProductRegisterForm form,
                                                 BindingResult result) {
        ValidationResponse res = new ValidationResponse();
        if (result.hasErrors()) {
            res.setStatus("FAIL");
            res.setErrorMessageList(getErrorMessages(result.getFieldErrors()));
        } else {
            Product product = form.getProduct();
            productService.addProduct(product);
            res.setStatus("SUCCESS");
        }
        return res;
    }

    private List<ErrorMessage> getErrorMessages(List<FieldError> allErrors) {
        List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
        for (FieldError objectError : allErrors) {
            errorMessages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
        }
        return errorMessages;
    }

    /* ToDo: возможно стоит  */
    @RequestMapping(value = "/provider/{providerId:.+}/edit/products/addProduct", method = RequestMethod.POST)
    public ModelAndView productAddingEnd(@PathVariable("providerId") Long providerId,
                                         @ModelAttribute("productForm") @Valid ProductRegisterForm form,
                                         BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("JS отключен");
            return enableProductsEditing(providerId);
        } else {
            return enableProductsEditing(providerId);
        }
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/products/add", method = RequestMethod.GET)
    public ModelAndView productsAddingForm(@PathVariable("providerId") Long providerId) {
        ModelAndView modelAndView = enableProductsEditing(providerId);
        Provider provider = providerService.getById(providerId);
        modelAndView.addObject("productAddingMode", true);
        modelAndView.addObject("otherProducts", ListUtils.subtract(productService.getAllProduct(), provider.getProducts()));
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/products/add/{productId}", method = RequestMethod.GET)
    public ModelAndView selectProduct(@PathVariable("providerId") Long providerId,
                                      @PathVariable("productId") Long productId) {
        ModelAndView modelAndView = productsAddingForm(providerId);
        Product selectedProduct = productService.getById(productId);
        modelAndView.addObject("selectedProduct", selectedProduct);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId:.+}/edit/products/add/{productId}", method = RequestMethod.POST)
    public ModelAndView selectProductSubmit(@PathVariable("providerId") Long providerId,
                                            @PathVariable("productId") Long productId) {
        Provider provider = providerService.getById(providerId);
        Product selectedProduct = productService.getById(productId);
        provider.getProducts().add(selectedProduct);
        providerService.updateProvider(provider);
        return new ModelAndView("redirect:/provider/" + provider.getId() + "/edit/products/add");
    }


    private void addEmployees(ModelAndView modelAndView, Long providerId) {
        Provider provider = providerService.getById(providerId);
        modelAndView.addObject("employees", provider.getEmployees());
    }

    private void addProducts(ModelAndView modelAndView, Long providerId) {
        Provider provider = providerService.getById(providerId);
        modelAndView.addObject("products", provider.getProducts());
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
