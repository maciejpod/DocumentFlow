package net.podolanski.converter;

import net.podolanski.dao.Role;
import net.podolanski.dao.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by maciej on 21.05.17.
 */
@Component
public class RoleConverter implements Converter<String, Role> {
    @Autowired RoleRepository roleRepository;

    @Override
    public Role convert(String s) {
        return roleRepository.findRoleByName(s);
    }
}