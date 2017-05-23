package net.podolanski.controller;

import net.podolanski.dao.Role;
import net.podolanski.dao.repository.ConnectionRepository;
import net.podolanski.dao.repository.DoctypeRepository;
import net.podolanski.dao.repository.RoleRepository;
import net.podolanski.dao.repository.TransactionRepository;
import net.podolanski.dto.DocumentPathForm;
import net.podolanski.dto.PathElement;
import net.podolanski.service.ConnectionService;
import net.podolanski.service.TransactionService;
import net.podolanski.validator.DocumentPathFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * Created by maciej on 19.05.17.
 */
@Controller
@RequestMapping("/path/")
public class DocumentPathController
{

    @Autowired TransactionService transactionService;
    @Autowired DoctypeRepository doctypeRepository;
    @Autowired TransactionRepository transactionRepository;
    @Autowired ConnectionRepository connectionRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired DocumentPathFormValidator documentPathFormValidator;
    @Autowired ConnectionService connectionService;

    Logger logger = LoggerFactory.getLogger(DocumentPathController.class);

    @ModelAttribute("documentPathForm")
    private DocumentPathForm documentPathForm() {
        return new DocumentPathForm();
    }

    @ModelAttribute("roleList")
    private Iterable<Role> getRoleList() {
        return roleRepository.findAll();
    }

    @GetMapping
    ModelAndView initDocumentPathForm() {
        ModelAndView mav = new ModelAndView("document-path");
        DocumentPathForm documentPathForm = new DocumentPathForm();
        mav.addObject("documentPath", documentPathForm);
        mav.addObject("roleList", roleRepository.findAll());
        return mav;
    }

    @PostMapping("/new")
    String processDocumentPathForm(@Valid DocumentPathForm documentPathForm, BindingResult bindingResult) {
        documentPathFormValidator.validate(documentPathForm, bindingResult);
        if(bindingResult.hasErrors()) {
            logger.info("errors in form" + bindingResult.toString());
            return "document-path";
        }
        for(PathElement pe: documentPathForm.getPathElementList()) {
            logger.info(pe.getTransactionName() + "     :"   + pe.getAssingnedRole().getName());
        }
        transactionService.createNewDocumentPath(documentPathForm);
        return "redirect:/home";
    }

    @GetMapping("/path/edit")
    ModelAndView initDocumentPathFormEdit() {
        ModelAndView mav = new ModelAndView("document-path-edit");
        DocumentPathForm connectionToEdit = connectionService.getConnectionToEdit(doctypeRepository.findByName("Doctype 1"));
        mav.addObject("documentEditForm", connectionToEdit);
        logger.info(connectionToEdit.getPathElementList().size() + "?");
        return mav;
    }

}
