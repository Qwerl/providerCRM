package ru.cg.providerCRM.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cg.providerCRM.services.TagService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueTagValidator implements ConstraintValidator<UniqueTag, String> {


    @Autowired
    private TagService tagService;

    @Override
    public void initialize(UniqueTag constraintAnnotation) {
    }

    @Override
    public boolean isValid(String tagText, ConstraintValidatorContext context) {
        return tagService.getByName(tagText) == null;
    }
}
