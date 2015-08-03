package ru.cg.providerCRM.web.form;

import ru.cg.providerCRM.entity.Product;

import javax.validation.Valid;
import java.util.List;

public class ProviderEditingForm {

    @Valid
    private List<EmployeeEditForm> employees;

    @Valid
    private ProviderInfoForm provider;

    private List<Product> products;

    public List<EmployeeEditForm> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEditForm> employees) {
        this.employees = employees;
    }

    public ProviderInfoForm getProvider() {
        return provider;
    }

    public void setProvider(ProviderInfoForm provider) {
        this.provider = provider;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProviderEditingForm{" +
                "employees=" + employees +
                ", provider=" + provider +
                ", products=" + products +
                '}';
    }
}
