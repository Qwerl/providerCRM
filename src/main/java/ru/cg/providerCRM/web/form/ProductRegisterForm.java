package ru.cg.providerCRM.web.form;

import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Product;

import javax.validation.constraints.NotNull;

public class ProductRegisterForm {

    @NotBlank(message = "Наименование не должно быть пустым")
    private String name;

    @NotNull(message = "Стоймость не должна быть пустой")
    private Long price;

    @NotBlank(message = "Примечания не должны быть пустыми")
    private String note;

    public ProductRegisterForm() {
    }

    public Product getProduct(){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setNote(note);
        return product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
