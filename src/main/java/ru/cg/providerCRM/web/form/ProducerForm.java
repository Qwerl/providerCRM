package ru.cg.providerCRM.web.form;

import lombok.Data;
import ru.cg.providerCRM.entity.Provider;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProducerForm {

    @Valid
    private ProducerInfoForm producer;

    @Valid
    private List<EmployeeEditForm> employees;

    private List<Provider> providers = new ArrayList<Provider>();

}