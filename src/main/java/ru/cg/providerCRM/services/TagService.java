package ru.cg.providerCRM.services;


import ru.cg.providerCRM.entity.Tag;

import java.util.List;

public interface TagService {
    void addTag(Tag tag);
    void deleteTag(Tag tag);
    void updateTag(Tag tag);
    Tag getById(Long id);
    Tag getByName(String name);
    List<Tag> getAllTags();
}
