package net.podolanski.validator;

import net.podolanski.dao.Doctype;
import net.podolanski.dao.Role;
import net.podolanski.dao.repository.DoctypeRepository;
import net.podolanski.dto.DocumentPathForm;
import net.podolanski.dto.PathElement;
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

    @Autowired
    DoctypeRepository doctypeRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return DocumentPathForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DocumentPathForm documentPathForm = (DocumentPathForm) o;
        List<PathElement> pathElementList = documentPathForm.getPathElementList();
        Set<String> pathNameSet = pathElementList
                .stream()
                .map(n -> n.getTransactionName())
                .collect(Collectors.toSet());
        Set<Role> roleSet = pathElementList
                .stream()
                .map(n -> n.getAssingnedRole())
                .collect(Collectors.toSet());
        if(pathElementList.size() != pathNameSet.size()) {
            errors.rejectValue("pathElementList", "uniqueElementName");
        }

        Doctype documentName = doctypeRepository.findByName(documentPathForm.getDocumentName());
        if(documentName != null) {
            errors.rejectValue("documentName", "uniqueDocumentName");
        }
        if(roleSet.size() != pathElementList.size()) {
            errors.rejectValue("pathElementList", "uniqueElementRole");
        }
    }
}
