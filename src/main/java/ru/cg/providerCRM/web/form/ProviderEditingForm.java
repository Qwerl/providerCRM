package ru.cg.providerCRM.web.form;

import lombok.Data;
import ru.cg.providerCRM.entity.Product;

import javax.validation.Valid;
import java.util.List;

@Data
public class ProviderEditingForm {

    @Valid
    private List<EmployeeEditForm> employees;

    @Valid
    private ProviderInfoForm provider;

    private List<Product> products;

}