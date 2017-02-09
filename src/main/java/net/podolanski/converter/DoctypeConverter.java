/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.converter;

import net.podolanski.dao.Doctype;
import net.podolanski.dao.repository.DoctypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author maciej
 */
@Component
public class DoctypeConverter implements Converter<String, Doctype>{

    @Autowired
    DoctypeRepository doctypeRepository;
    
    @Override
    public Doctype convert(String s) {
        return doctypeRepository.findByName(s);
    }
    
}
