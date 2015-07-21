package ru.cg.providerCRM.web.form;

import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Tag;

public class TagEditForm {
    private Long id;

    @NotBlank(message = "Тег не может быть пустым")
    private String tagText;

    public TagEditForm() {
    }

    public TagEditForm(Tag tag) {
        id = tag.getId();
        tagText = tag.getTagText();
    }

    public Tag getTag() {
        Tag tag = new Tag();
        tag.setTagText(tagText);
        tag.setId(id);
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
