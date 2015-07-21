package ru.cg.providerCRM.web;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.cg.providerCRM.entity.*;
import ru.cg.providerCRM.services.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@Scope("request")
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    public ProviderService providerService;
    @Autowired
    private ProducerService producerService;
    @Autowired
    private TagService tagService;
    @Autowired
    public DocumentService documentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        System.out.println("ok");
        return "index";
    }

    @RequestMapping(value = "/selectProvider", method = RequestMethod.GET)
    public ModelAndView selectAllProviders() {
        ModelAndView modelAndView = new ModelAndView("selectProvider");

        Iterable<Provider> providers = providerService.getAllProviders();
        modelAndView.addObject("providers", providers);

        return modelAndView;
    }

    @RequestMapping(value = "/selectProvider/{providerId:.+}", method = RequestMethod.GET)
    public ModelAndView selectProviderByName(@PathVariable("providerId") String providerId) {
        ModelAndView modelAndView = selectAllProviders();

        Provider provider = providerService.getById(Long.parseLong(providerId));
        addProviderInfo(modelAndView, provider);

        addProductsProvided(modelAndView, provider);

        return modelAndView;
    }

    @RequestMapping(value = "/selectProvider/{providerId:.+}/documents", method = RequestMethod.GET)
    public ModelAndView showAllDocuments(@PathVariable("providerId") String providerId) {
        ModelAndView modelAndView = new ModelAndView("documents");

        Provider provider = providerService.getById(Long.parseLong(providerId));
        addProviderInfo(modelAndView, provider);

        return modelAndView;
    }

    @RequestMapping(value = "/selectProvider/{providerId:.+}/documents/{documentId:.+}", method = RequestMethod.GET)
    public void downloadDocument(HttpServletResponse response,
                                 @PathVariable(value = "providerId") String providerId,
                                 @PathVariable(value = "documentId") String documentId) {

        Document document = documentService.getById(Long.parseLong(documentId));
        response.setHeader("Content-Disposition", "attachment; filename=" + document.getName() + "." + document.getExtension());
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(document.getFile());
            IOUtils.copy(bis, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @RequestMapping(value = "/selectProvider/{providerId:.+}/edit/addDocument", method = RequestMethod.POST)
    public ModelAndView addDocument(@PathVariable("providerId") String providerId,
                                    @RequestParam(value = "name") String name,
                                    @RequestParam(value = "file") MultipartFile file) {
        String readName;
        try {//почему-то приходит не в utf-8
            readName = new String(name.getBytes("iso-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            readName = name;
        }
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        Document document = new Document();
        document.setName(readName);
        document.setExtension(extension);
        try {
            document.setFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        providerService.addDocument(document, Long.parseLong(providerId));
        return new ModelAndView("redirect:/selectProvider/" + providerId + "/documents");
    }

    private void addProviderInfo(ModelAndView modelAndView, Provider provider) {
        modelAndView.addObject("providerInfo", provider);
    }

    private void addProductsProvided(ModelAndView modelAndView, Provider provider) {
        Iterable<Product> productList = provider.getProducts();
        modelAndView.addObject("productList", productList);
    }

    @RequestMapping(value = "/selectProducer", method = RequestMethod.GET)
    public ModelAndView selectAllProducers() {
        ModelAndView modelAndView = new ModelAndView("selectProducer");

        modelAndView.addObject("producers", producerService.getAllProducers());

        return modelAndView;
    }

    @RequestMapping(value = "/selectProducer/{producerId:.+}", method = RequestMethod.GET)
    public ModelAndView selectProducerByName(@PathVariable("producerId") String producerId) {
        ModelAndView modelAndView = selectAllProducers();
        Producer producer = producerService.getById(Long.parseLong(producerId));
        modelAndView.addObject("producerInfo", producer);
        addProvidersWithLinksToProducer(modelAndView, producer);
        return modelAndView;
    }

    private void addProvidersWithLinksToProducer(ModelAndView modelAndView, Producer producer) {
        Iterable<Provider> providerList = producer.getProviders();
        modelAndView.addObject("providerList", providerList);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView displaySearch() {
       return new ModelAndView("search");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(value = "tag") String tagText) {
        ModelAndView modelAndView = new ModelAndView("search");
        Tag tag;
        try {
            tag = tagService.getByName(tagText);
        } catch (Exception e) {
            /* todo: обработать корректно */
            return new ModelAndView("search");
        }

        Iterable<Provider> providers = providerService.getProvidersContainsTag(tag);
        modelAndView.addObject("providers", providers);

        producerService.getAllProducers();
        Iterable<Producer> producers = producerService.getProducersContainsTag(tag);
        modelAndView.addObject("producers", producers);

        modelAndView.addObject("currentTag", tagText);
        return modelAndView;
    }

    @RequestMapping(value = "/search/{tagText:.+}", method = RequestMethod.GET)
    public ModelAndView searchByTag(@PathVariable(value = "tagText") String tagText) {
        return search(tagText);
    }

}
