package net.podolanski.validator;

import net.podolanski.dao.Doctype;
import net.podolanski.dao.Role;
import net.podolanski.dao.repository.DoctypeRepository;
import net.podolanski.dto.DocumentPathForm;
import net.podolanski.dto.PathElement;
import net.podolanski.service.DoctypeService;
import net.podolanski.service.DocumentPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by maciej on 22.05.17.
 */
@Component
public class DocumentPathFormValidator implements Validator {

    @Autowired DoctypeService doctypeService;
    @Autowired DocumentPathService documentPathService;

    @Override
    public boolean supports(Class<?> aClass) {
        return DocumentPathForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DocumentPathForm documentPathForm = (DocumentPathForm) o;
        List<PathElement> pathElementList = documentPathForm.getPathElementList();
        Set<String> pathNameSet = documentPathService.getPathElementNamesSet(documentPathForm);
        Set<Role> roleSet = documentPathService.getPathElementRoleSet(documentPathForm);

        if (pathElementList.size() != pathNameSet.size()) {
            errors.rejectValue("pathElementList", "uniqueElementName");
        }

        Doctype documentName = doctypeService.findByName(documentPathForm.getDocumentName());
        if (documentName != null) {
            errors.rejectValue("documentName", "uniqueDocumentName");
        }
        if (roleSet.size() != pathElementList.size()) {
            errors.rejectValue("pathElementList", "uniqueElementRole");
        }
    }
}
