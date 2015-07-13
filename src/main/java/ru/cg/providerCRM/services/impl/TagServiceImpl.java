package ru.cg.providerCRM.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cg.providerCRM.entity.Tag;
import ru.cg.providerCRM.services.TagService;
import ru.cg.providerCRM.repository.TagRepository;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    public TagRepository tagRepository;

    public void addTag(Tag tag) {
        tagRepository.saveAndFlush(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        tagRepository.saveAndFlush(tag);
    }

    @Override
    public Tag getById(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Tag getByName(String name) {
        return tagRepository.findByTagText(name);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
