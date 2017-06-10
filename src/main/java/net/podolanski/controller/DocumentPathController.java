package net.podolanski.controller;

import net.podolanski.dao.Doctype;
import net.podolanski.dao.Role;
import net.podolanski.dto.DocumentPathForm;
import net.podolanski.service.ConnectionService;
import net.podolanski.service.DoctypeService;
import net.podolanski.service.TransactionService;
import net.podolanski.service.UserRoleService;
import net.podolanski.validator.DocumentPathFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by maciej on 19.05.17.
 */
@Controller
@RequestMapping("/path/")
public class DocumentPathController {
    @Autowired TransactionService transactionService;
    @Autowired DoctypeService doctypeService;
    @Autowired UserRoleService roleService;
    @Autowired DocumentPathFormValidator documentPathFormValidator;
    @Autowired ConnectionService connectionService;

    @ModelAttribute("documentPathForm")
    private DocumentPathForm documentPathForm() {
        return new DocumentPathForm();
    }

    @ModelAttribute("roleList")
    private Iterable<Role> getRoleList() {
        return roleService.findAll();
    }

    @GetMapping("/new")
    ModelAndView initDocumentPathForm() {
        ModelAndView mav = new ModelAndView("document-path");
        DocumentPathForm documentPathForm = new DocumentPathForm();
        mav.addObject("documentPath", documentPathForm);
        mav.addObject("roleList", roleService.findAll());
        return mav;
    }

    @PostMapping("/new")
    String processDocumentPathForm(@Valid DocumentPathForm documentPathForm, BindingResult bindingResult) {
        documentPathFormValidator.validate(documentPathForm, bindingResult);
        if(bindingResult.hasErrors()) return "document-path";
        transactionService.createNewDocumentPath(documentPathForm.getPathElementList().iterator(),
                null, documentPathForm.getDocumentName());
        return "redirect:/home";
    }

    @GetMapping("/{id}/edit")
    ModelAndView initDocumentPathFormEdit(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("document-path-edit");
        DocumentPathForm connectionToEdit = connectionService.getConnectionToEdit(doctypeService.findOne(id));
        mav.addObject("documentEditForm", connectionToEdit);
        return mav;
    }

    @PostMapping("/{id}/edit")
    String processDocumentPathFormEdit(@PathVariable Integer id, @Valid DocumentPathForm documentEditForm, BindingResult bindingResult) {
        Doctype doctype = doctypeService.findOne(id);
        transactionService.editExistingDocumentPath(doctype, documentEditForm);
        return "redirect:/path/";
    }

    @GetMapping("")
    ModelAndView initPathList() {
        ModelAndView mav = new ModelAndView("document-path-list");
        mav.addObject("documentList", doctypeService.findAll());
        return mav;
    }
}
