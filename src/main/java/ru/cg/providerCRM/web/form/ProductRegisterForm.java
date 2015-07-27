package ru.cg.providerCRM.web.form;

import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ProductRegisterForm {

    @NotBlank(message = "Наименование не должно быть пустым")
    private String name;

    @NotNull(message = "Стоймость не должна быть пустой")
    @Pattern(regexp = "^[0-9]+", message = "Стоимость должна выражаться числом")
    private String price;

    @NotBlank(message = "Примечания не должны быть пустыми")
    private String note;

    public ProductRegisterForm() {
    }

    public Product getProduct(){
        Product product = new Product();
        product.setName(name);
        product.setPrice(Long.parseLong(price));
        product.setNote(note);
        return product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
