package ru.cg.providerCRM.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.services.ProviderService;
import ru.cg.providerCRM.web.response.ProviderResponse;

import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("request")
@RequestMapping(value = "/")
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @RequestMapping(value = "/provider/{providerId}", method = RequestMethod.POST)
    @ResponseBody
    public ProviderResponse getProvider(@PathVariable("providerId") Long providerId) {
        ProviderResponse provider = new ProviderResponse(providerService.getById(providerId));
        return provider;
    }

    @RequestMapping(value = "/provider/getAll", method = RequestMethod.POST)
    @ResponseBody
    public List<ProviderResponse> getAllProviders() {
        List<Provider> providers = providerService.getAllProviders();
        List<ProviderResponse> providerResponses = new ArrayList<>();
        for (Provider provider : providers) {
            providerResponses.add(new ProviderResponse(provider));
        }
        return providerResponses;
    }

}