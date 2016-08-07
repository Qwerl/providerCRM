package ru.cg.providerCRM.web.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import ru.cg.providerCRM.entity.Provider;

import javax.validation.constraints.Size;

@Data
public class ProviderInfoForm {

    private Long id;

    @NotBlank(message = "Название не должно быть пустым")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String name;

    @NotBlank(message = "Адрес офиса не должен быть пустым")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String address;

    @NotEmpty(message = "Общий телефон не должен быть пустым")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String phoneNumber;

    @NotEmpty(message = "Адрес склада не должен быть пустым")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String storageAddress;

    @NotEmpty(message = "Примечания не должны быть пустыми")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String note;


    public ProviderInfoForm() {
    }

    public ProviderInfoForm(Provider provider) {
        this.id = provider.getId();
        this.name = provider.getName();
        this.address = provider.getAddress();
        this.phoneNumber = provider.getPhoneNumber();
        this.storageAddress = provider.getStorageAddress();
        this.note = provider.getNote();
    }

    public void fillProvider(Provider provider) {
        provider.setName(name);
        provider.setAddress(address);
        provider.setPhoneNumber(phoneNumber);
        provider.setStorageAddress(storageAddress);
        provider.setNote(note);
    }

}