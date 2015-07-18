package ru.cg.providerCRM.web.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import ru.cg.providerCRM.entity.*;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ProviderForm {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    private String name;

    @NotBlank
    @Size(min = 1, max = 255)
    private String address;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String phoneNumber;

    @NotEmpty
    @Size(min = 0, max = 255)
    private String storageAddress;

    @NotEmpty
    @Size(min = 0, max = 255)
    private String note;

    private List<Tag> tags = new ArrayList<Tag>();

    private List<Product> products = new ArrayList<Product>();

    public ProviderForm() {
    }

    public ProviderForm(Provider provider) {
        this.id = provider.getId();
        this.name = provider.getName();
        this.address = provider.getAddress();
        this.phoneNumber = provider.getPhoneNumber();
        this.storageAddress = provider.getStorageAddress();
        this.note = provider.getNote();
        if (provider.getTags() != null) {
            this.tags = provider.getTags();
        }
        if (provider.getProducts() != null) {
            this.products = provider.getProducts();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStorageAddress() {
        return storageAddress;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void fillProvider(Provider provider) {
        provider.setName(name);
        provider.setAddress(address);
        provider.setPhoneNumber(phoneNumber);
        provider.setStorageAddress(storageAddress);
        provider.setNote(note);
        provider.setTags(tags);
        provider.setProducts(products);
    }



    @Override
    public String toString() {
        return "ProviderForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", storageAddress='" + storageAddress + '\'' +
                ", note='" + note + '\'' +
                ", tags=" + tags +
                ", products=" + products +
                '}';
    }
}
