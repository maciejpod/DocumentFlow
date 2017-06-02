package net.podolanski.service;

import net.podolanski.dao.Doctype;
import net.podolanski.dao.Transaction;
import net.podolanski.dao.repository.DoctypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by maciej on 02.06.17.
 */

@Service
public class DoctypeService {
    @Autowired DoctypeRepository doctypeRepository;

    public void createNewDocType(String name, Transaction beginingTransaction) {
        Doctype doctype = new Doctype();
        doctype.setName(name);
        doctype.setTransactionId(beginingTransaction);
        doctypeRepository.save(doctype);
    }

    public Doctype findOne(Integer id) {
        return doctypeRepository.findOne(id);
    }

    public Iterable<Doctype> findAll() {
        return doctypeRepository.findAll();
    }
}
