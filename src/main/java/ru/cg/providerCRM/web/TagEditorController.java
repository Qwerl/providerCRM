package ru.cg.providerCRM.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.cg.providerCRM.entity.Producer;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.entity.Tag;
import ru.cg.providerCRM.services.ProducerService;
import ru.cg.providerCRM.services.ProviderService;
import ru.cg.providerCRM.services.TagService;
import ru.cg.providerCRM.web.form.TagEditForm;
import ru.cg.providerCRM.web.form.TagRegistrationForm;

import javax.validation.Valid;

@Controller
@Scope("request")
@RequestMapping(value = "/")
public class TagEditorController {

    @Autowired
    private TagService tagService;

    @Autowired
    public ProducerService producerService;

    @Autowired
    public ProviderService providerService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ModelAndView selectAllTags() {
        ModelAndView modelAndView = new ModelAndView("tagEditor");
        addAllTags(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/tags/{tagId}", method = RequestMethod.GET)
    public ModelAndView selectTag(@PathVariable(value = "tagId") Long tagId) {
        ModelAndView modelAndView = selectAllTags();
        modelAndView.addObject("tagInfo", tagService.getById(tagId));
        return modelAndView;
    }

    @RequestMapping(value = "/tags/addTag", method = RequestMethod.GET)
    public ModelAndView addTagForm() {
        ModelAndView modelAndView = selectAllTags();
        modelAndView.addObject("addingMode", true);
        modelAndView.addObject("tagForm", new TagRegistrationForm());
        return modelAndView;
    }

    @RequestMapping(value = "/tags/addTag", method = RequestMethod.POST)
    public ModelAndView addTag(@ModelAttribute(value = "tagForm") @Valid TagRegistrationForm tagForm,
                               BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = selectAllTags();
            modelAndView.addObject("addingMode", true);
            return modelAndView;
        } else {
            Tag tag = tagForm.getTag();
            tagService.addTag(tag);
            return new ModelAndView("redirect:/tags/" + tag.getId());
        }
    }

    @RequestMapping(value = "/tags/edit/update/{tagId}", method = RequestMethod.GET)
    public ModelAndView editTagForm(@PathVariable(value = "tagId") Long tagId) {
        ModelAndView modelAndView = selectAllTags();
        modelAndView.addObject("editingMode", true);
        modelAndView.addObject("tagForm", new TagEditForm(tagService.getById(tagId)));
        return modelAndView;
    }

    @RequestMapping(value = "/tags/edit/update/{tagId}", method = RequestMethod.POST)
    public ModelAndView editTag(@PathVariable(value = "tagId") Long tagId,
                                @ModelAttribute(value = "tagForm") @Valid TagEditForm tagForm,
                                BindingResult result) {
        tagForm.setId(tagId);
        if (result.hasErrors()) {
            ModelAndView modelAndView = selectAllTags();
            modelAndView.addObject("editingMode", true);
            return modelAndView;
        } else {
            Tag tag = tagForm.getTag();
            tagService.updateTag(tag);
            return new ModelAndView("redirect:/tags/" + tag.getId());
        }
    }

    @RequestMapping(value = "/tags/edit/delete/{tagId}", method = RequestMethod.GET)
    public ModelAndView deleteTag(@PathVariable(value = "tagId") Long tagId) {

        Tag tag = tagService.getById(tagId);

        /* ToDo: переписать */
        Iterable<Provider> providers = providerService.getProvidersContainsTag(tag);
        Iterable<Producer> producers = producerService.getProducersContainsTag(tag);
        for (Producer producer : producers) {
            producer.getTags().remove(tag);
            producerService.updateProducer(producer);
        }
        for (Provider provider : providers) {
            provider.getTags().remove(tag);
            providerService.updateProvider(provider);
        }

        tagService.deleteTag(tag);
        return new ModelAndView("redirect:/tags/");
    }


    private void addAllTags(ModelAndView modelAndView) {
        modelAndView.addObject("tags", tagService.getAllTags());
    }
}
