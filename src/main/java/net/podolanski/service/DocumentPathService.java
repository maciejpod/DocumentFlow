package net.podolanski.service;

import net.podolanski.dao.Role;
import net.podolanski.dto.DocumentPathForm;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by maciej on 06.06.17.
 */
@Service
public class DocumentPathService {

    public Set<String> getPathElementNamesSet(DocumentPathForm documentPathForm) {
         return documentPathForm.getPathElementList()
                 .stream()
                 .map(n -> n.getTransactionName())
                 .collect(Collectors.toSet());
    }

    public Set<Role> getPathElementRoleSet(DocumentPathForm documentPathForm) {
        return documentPathForm.getPathElementList()
                .stream()
                .map(n -> n.getAssingnedRole())
                .collect(Collectors.toSet());
    }
}
