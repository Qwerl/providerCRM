package ru.cg.providerCRM.web.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Product;

@Data
public class ProductRegistrationForm {

    @NotBlank(message = "Наименование не должно быть пустым")
    private String name;

    @NotBlank(message = "Примечания не должны быть пустыми")
    private String note;

    public ProductRegistrationForm() {
    }

    public Product getProduct(){
        Product product = new Product();
        product.setName(name);
        product.setNote(note);
        return product;
    }

}