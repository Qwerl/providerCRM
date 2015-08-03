package ru.cg.providerCRM.web.form;

import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Product;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
