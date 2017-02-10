/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.podolanski.dao.Status;
import net.podolanski.dao.CurrentState;
import net.podolanski.dao.Department;
import net.podolanski.dao.Doctype;
import net.podolanski.dao.Request;
import net.podolanski.dao.Transaction;
import net.podolanski.dao.Userrole;
import net.podolanski.dao.repository.CurrentStateRepository;
import net.podolanski.dao.repository.DoctypeRepository;
import net.podolanski.dao.repository.TransactionRepository;
import net.podolanski.dao.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentStateService {

    @Autowired
    DoctypeRepository doctypeRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CurrentStateRepository currentStateRepository;

    @Autowired
    RequestService requestService;

    public void update(CurrentState currentState) {
        Status status = currentState.getStatusId();
        Request request = currentState.getRequest();
        switch (status) {
            case Odrzucono:
            case Modyfikacja:
                currentStateRepository.deleteByRequest(request);
                request.setStatusId(status);
                requestService.update(request);
                break;
            case Zatwierdzono:
                onCurrentStateAccepted(currentState);
                break;
        }
    }

    private void onCurrentStateAccepted(CurrentState currentState) {
        Request request = currentState.getRequest();

        List<CurrentState> currentStateList = currentStateRepository.findByRequest(request);

        if (isCurrentFlowFinished(currentStateList)) {

            Set<CurrentState> currentStateUpdateList = findNextStates(currentStateList);

            if (currentStateUpdateList.isEmpty()) {
                request.setStatusId(Status.Zatwierdzono);
                requestService.update(request);
            }
            currentStateRepository.deleteByRequest(request);
            currentStateRepository.save(currentStateUpdateList);
        }
    }

    private Set<CurrentState> findNextStates(List<CurrentState> currentStateList) {
        Set<CurrentState> currentStateUpdateList = new HashSet<>();

        currentStateList.forEach((cs) -> {
            Request request = cs.getRequest();
            Transaction transaction = transactionRepository
                    .findNextTransaction(cs.getTransaction(), request.getDocType());

            if (!isLastStep(transaction, request.getDocType())) {
                Department newDepartment = cs.getDepartment();

                List<Userrole> userRole = userRoleRepository
                        .findByRoleAndDepartment(transaction.getRoleId(), newDepartment);

                newDepartment = userRole.isEmpty() ? newDepartment.getFKdepartmentid() : newDepartment;

                CurrentState newState = new CurrentState(request, transaction, newDepartment);
                acceptStatusForOwnerRequest(userRole, newState);
                currentStateUpdateList.add(newState);
            }
        });
        return currentStateUpdateList;
    }

    private boolean acceptStatusForOwnerRequest(List<Userrole> userRoleList, CurrentState currentState) {
        for (Userrole ur : userRoleList) {
            if (ur.getUser().equals(currentState.getRequest().getUser())) {
                currentState.setStatusId(Status.Zatwierdzono);
                return true;
            }
        }
        return false;
    }

    public void intializeFlow(Request request) {
        Transaction transaction
                = transactionRepository.findFirstTransaction(request.getDocType().getDoctypeId());
        CurrentState currentState
                = new CurrentState(request, transaction, request.getDepartment());

        List<Userrole> userRole = userRoleRepository
                .findByRoleAndDepartment(transaction.getRoleId(), request.getDepartment());

        currentStateRepository.save(currentState);
        if (acceptStatusForOwnerRequest(userRole, currentState)) {
            onCurrentStateAccepted(currentState);
        }
    }

    // wyjazd do transaction service
    private boolean isLastStep(Transaction transaction, Doctype doctype) {
        return transactionRepository.findFirstTransaction(doctype.getDoctypeId()).equals(transaction);
    }

    private boolean isCurrentFlowFinished(List<CurrentState> currentStateList) {
        List<CurrentState> acceptedCurrenStates = currentStateList.stream()
                .filter((cs) -> cs.getStatusId().equals(Status.Zatwierdzono))
                .collect(Collectors.toList());
        return currentStateList.size() - 1 == acceptedCurrenStates.size();
    }
}
