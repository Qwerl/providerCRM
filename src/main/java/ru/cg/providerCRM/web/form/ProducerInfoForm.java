package ru.cg.providerCRM.web.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Producer;

import javax.validation.constraints.Size;

@Data
public class ProducerInfoForm {

    private Long id;

    @NotBlank(message = "Название не должно быть пустым")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String name;

    @NotBlank(message = "Адрес не должно быть пустым")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String address;

    @NotBlank(message = "Общий телефон не должен быть пустым")
    @Size(min = 2, max = 20, message = "Длина должна быть больше 2 символов, но не больше 20")
    private String phoneNumber;

    @NotBlank(message = "Общий телефон не должен быть пустым")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String note;

    public ProducerInfoForm() {
    }

    public ProducerInfoForm(Producer producer) {
        this.id = producer.getId();
        this.name = producer.getName();
        this.address = producer.getAddress();
        this.phoneNumber = producer.getPhoneNumber();
        this.note = producer.getNote();
    }

    public void fillProducer(Producer producer) {
        producer.setId(id);
        producer.setName(name);
        producer.setAddress(address);
        producer.setPhoneNumber(phoneNumber);
        producer.setNote(note);
    }

}