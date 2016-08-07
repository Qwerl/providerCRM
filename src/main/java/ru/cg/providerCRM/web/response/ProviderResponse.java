package ru.cg.providerCRM.web.response;

import lombok.Data;
import ru.cg.providerCRM.entity.Provider;

@Data
public class ProviderResponse extends Response {

    private String id;
    private String name;
    private String address;

    public ProviderResponse() {
    }

    public ProviderResponse(Provider provider) {
        this.id = provider.getId().toString();
        this.name = provider.getName();
        this.address = provider.getAddress();
    }

}