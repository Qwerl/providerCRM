package ru.cg.providerCRM.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.entity.Product;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.services.*;
import ru.cg.providerCRM.web.response.*;
import ru.cg.providerCRM.web.form.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static ru.cg.providerCRM.services.FormToEntityConverter.EmployeeFormToEmployee;

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

    @Autowired
    public ProducerService producerService;

    @RequestMapping(value = "/provider/add", method = RequestMethod.GET)
    public ModelAndView displayProviderRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("addNewProviderNew");

        ProviderEditingForm form = new ProviderEditingForm();
        form.setEmployees(new ArrayList<EmployeeEditForm>());

        Provider provider = new Provider();
        form.setProvider(new ProviderInfoForm(provider));
        form.setProducts(new ArrayList<Product>());

        modelAndView.addObject("editingMode", true);
        modelAndView.addObject("employeeForm", new EmployeeRegistrationForm());
        modelAndView.addObject("productForm", new ProductRegistrationForm());
        modelAndView.addObject("providerForm", new ProviderEditingForm());
        return modelAndView;
    }

    @RequestMapping(value = "/provider/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addNewProvider(@ModelAttribute("providerForm") @Valid ProviderEditingForm form, BindingResult result) {
        if (result.hasErrors()) {
            ValidationResponse res = new ValidationResponse();
            res.setErrorMessageList(getErrorMessages(result.getFieldErrors()));
            res.setStatus("FAIL");
            return res;
        } else {
            ProviderResponse res = new ProviderResponse();

            Provider provider = new Provider();
            form.getProvider().fillProvider(provider);
            providerService.addProvider(provider);

            List<EmployeeEditForm> employees = form.getEmployees();

            if (employees != null) {
                for (EmployeeEditForm employeeEditForm : employees) {
                    if (!employeeEditForm.getIsUnbound()) {
                        employeeEditForm.setProviderId(provider.getId());
                        employeeService.updateEmployee(
                                EmployeeFormToEmployee(
                                        employeeEditForm, providerService, producerService
                                )
                        );
                    }
                }
            }

            List<Product> products = form.getProducts();
            List<Product> productsProvider = new ArrayList<>();
            if (products != null) {
                for (Product product : products) {
                    if (product.getId() != null) {
                        productsProvider.add(productService.getById(product.getId()));
                    }
                }
            }
            provider.setProducts(productsProvider);
            providerService.updateProvider(provider);

            res.setId(provider.getId().toString());
            res.setStatus("SUCCESS");
            return res;
        }
    }

    @RequestMapping(value = "/provider/{providerId}", method = RequestMethod.GET)
    public ModelAndView providerFullInfo(@PathVariable("providerId") Long providerId) {
        ModelAndView modelAndView = new ModelAndView("providerEditor");
        Provider provider = providerService.getById(providerId);

        ProviderEditingForm form = new ProviderEditingForm();
        form.setEmployees(getEmployeeEditForms(provider));
        form.setProvider(new ProviderInfoForm(provider));
        form.setProducts(provider.getProducts());

        /* ToDo: перенести на AJAX */
        modelAndView.addObject("otherProducts", productService.getProductsNotIn(provider.getProducts()));

        modelAndView.addObject("employeeForm", new EmployeeRegistrationForm());
        modelAndView.addObject("productForm", new ProductRegistrationForm());
        modelAndView.addObject("providerForm", form);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId}/edit", method = RequestMethod.GET)
    public ModelAndView enableEditingMode(@PathVariable("providerId") Long providerId) {
        ModelAndView modelAndView = providerFullInfo(providerId);
        modelAndView.addObject("editingMode", true);
        return modelAndView;
    }

    @RequestMapping(value = "/provider/{providerId}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse ajaxProviderEditingFormValidator(@PathVariable("providerId") Long providerId,
                                                               @ModelAttribute("providerForm") @Valid ProviderEditingForm providerForm,
                                                               BindingResult result) {
        ValidationResponse res = new ValidationResponse();
        if (result.hasErrors()) {
            res.setStatus("FAIL");
            res.setErrorMessageList(getErrorMessages(result.getFieldErrors()));
        } else {
            Provider provider = providerService.getById(providerId);
            providerForm.getProvider().fillProvider(provider);
            providerService.updateProvider(provider);

            List<EmployeeEditForm> employeeEditForms = providerForm.getEmployees();
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
        }
        return res;
    }

    @RequestMapping(value = "/provider/{providerId}/edit/employees/validateNew", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse validateEmployeeForm(@PathVariable("providerId") Long providerId,
                                                   @ModelAttribute("employeeForm") @Valid EmployeeRegistrationForm form,
                                                   BindingResult result) {
        ValidationResponse res = new ValidationResponse();
        if (result.hasErrors()) {
            res.setStatus("FAIL");
            res.setErrorMessageList(getErrorMessages(result.getFieldErrors()));
        } else {
            Employee employee = new Employee();
            form.fillEmployee(employee);
            employee.setProvider(providerService.getById(providerId));
            employeeService.addEmployee(employee);
            res = new EmployeeResponse(employee);
            res.setStatus("SUCCESS");
        }
        return res;
    }

    @RequestMapping(value = "/provider/{providerId}/edit/employees/{employeeId}/unbind", method = RequestMethod.POST)
    @ResponseBody
    public Response unbindEmployee(@PathVariable("providerId") Long providerId,
                                   @PathVariable("employeeId") Long employeeId) {
        Response res = new Response();
        Employee employee = employeeService.getById(employeeId);
        employee.setProvider(null);
        employeeService.updateEmployee(employee);
        res.setStatus("SUCCESS");
        return res;
    }

    @RequestMapping(value = "/provider/{providerId}/edit/products/edit/{productId}", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse unbindProduct(@PathVariable("providerId") Long providerId,
                                            @PathVariable("productId") Long productId) {
        ValidationResponse res = new ValidationResponse();
        providerService.deleteProduct(productId, providerId);
        res.setStatus("SUCCESS");
        return res;
    }

    @RequestMapping(value = "/provider/{providerId}/edit/products/validateNew", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse validateProductForm(@PathVariable("providerId") Long providerId,
                                                  @ModelAttribute("productForm") @Valid ProductRegistrationForm form,
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

    @RequestMapping(value = "/provider/{providerId}/edit/products/{productId}", method = RequestMethod.POST)
    @ResponseBody
    public ProductResponse selectProduct(@PathVariable("providerId") Long providerId,
                                         @PathVariable("productId") Long productId) {
        Product selectedProduct = productService.getById(productId);
        ProductResponse res = new ProductResponse(selectedProduct);
        res.setStatus("SUCCESS");
        return res;
    }

    @RequestMapping(value = "/provider/{providerId}/edit/products/add/{productId}", method = RequestMethod.POST)
    @ResponseBody
    public ProductResponse bindProduct(@PathVariable("providerId") Long providerId,
                                       @PathVariable("productId") Long productId) {
        Provider provider = providerService.getById(providerId);
        Product selectedProduct = productService.getById(productId);
        provider.getProducts().add(selectedProduct);
        providerService.updateProvider(provider);

        ProductResponse res = new ProductResponse(selectedProduct);
        res.setStatus("SUCCESS");
        return res;
    }

    @RequestMapping(value = "/provider/{providerId}/edit/getOtherProducts", method = RequestMethod.POST)
    @ResponseBody
    public List<Product> getProducts(@PathVariable("providerId") Long providerId) {
        Provider provider = providerService.getById(providerId);
        List<Product> products = productService.getProductsNotIn(provider.getProducts());
        return products;
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

    private List<EmployeeEditForm> getEmployeeEditForms(Provider provider) {
        List<EmployeeEditForm> employeeForms = new ArrayList<>();
        List<Employee> employees = provider.getEmployees();

        for (Employee employee : employees) {
            employeeForms.add(new EmployeeEditForm(employee));
        }

        return employeeForms;
    }
}
