package ru.cg.providerCRM.web.response;

import ru.cg.providerCRM.entity.Provider;

public class ProviderResponse extends Response{
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
