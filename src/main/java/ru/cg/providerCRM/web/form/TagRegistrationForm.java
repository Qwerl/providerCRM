package ru.cg.providerCRM.web.form;

import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Tag;
import ru.cg.providerCRM.validator.UniqueTag;

public class TagRegistrationForm {

    private Long id;

    @NotBlank(message = "Тег не может быть пустым")
    @UniqueTag(message = "Такой тег уже существует")
    private String tagText;
    
    public Tag getTag() {
        Tag tag = new Tag();
        tag.setTagText(tagText);
        return tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }
}
