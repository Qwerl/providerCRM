package ru.cg.providerCRM.web.form;

import ru.cg.providerCRM.entity.Provider;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class ProducerForm {

    @Valid
    private ProducerInfoForm producer;

    @Valid
    private List<EmployeeEditForm> employees;

    private List<Provider> providers = new ArrayList<Provider>();

    public ProducerInfoForm getProducer() {
        return producer;
    }

    public void setProducer(ProducerInfoForm producer) {
        this.producer = producer;
    }

    public List<EmployeeEditForm> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEditForm> employees) {
        this.employees = employees;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
}
