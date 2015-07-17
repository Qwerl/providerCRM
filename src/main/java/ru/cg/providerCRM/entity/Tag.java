package ru.cg.providerCRM.entity;

import javax.persistence.*;

@Entity(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private Long id;

    @Column(name = "TAG")
    private String tagText;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (!id.equals(tag.id)) return false;
        return tagText.equals(tag.tagText);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + tagText.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag='" + tagText + '\'' +
                '}';
    }
}
