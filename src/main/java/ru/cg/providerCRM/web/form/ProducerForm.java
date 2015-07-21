package ru.cg.providerCRM.web.form;

import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Producer;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.entity.Tag;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ProducerForm {

    private Long id;

    @NotBlank(message = "Название не должно быть пустым")
    @Size(min = 2, max = 255, message = "Длинна должна быть больше 2 символов, но не больше 255")
    private String name;

    @NotBlank(message = "Адрес не должно быть пустым")
    @Size(min = 2, max = 255, message = "Длинна должна быть больше 2 символов, но не больше 255")
    private String address;

    @NotBlank(message = "Общий телефон не должен быть пустым")
    @Size(min = 2, max = 20, message = "Длинна должна быть больше 2 символов, но не больше 20")
    private String phoneNumber;

    @NotBlank(message = "Общий телефон не должен быть пустым")
    @Size(min = 2, max = 255, message = "Длинна должна быть больше 2 символов, но не больше 255")
    private String note;

    private List<Provider> providers = new ArrayList<Provider>();

    private List<Tag> tags = new ArrayList<Tag>();

    public ProducerForm() {
    }

    public ProducerForm(Producer producer) {
        this.id = producer.getId();
        this.name = producer.getName();
        this.address = producer.getAddress();
        this.phoneNumber = producer.getPhoneNumber();
        this.note = producer.getNote();
        this.providers = producer.getProviders();
        this.tags = producer.getTags();
    }

    public void fillProducer(Producer producer) {
        producer.setName(name);
        producer.setAddress(address);
        producer.setPhoneNumber(phoneNumber);
        producer.setNote(note);
        producer.setProviders(providers);
        producer.setTags(tags);
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ProducerForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", note='" + note + '\'' +
                ", providers=" + providers +
                ", tags=" + tags +
                '}';
    }

}
