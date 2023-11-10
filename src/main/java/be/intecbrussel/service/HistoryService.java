package be.intecbrussel.service;

import be.intecbrussel.model.Operation;
import be.intecbrussel.model.OperationType;

import java.util.List;

public interface HistoryService {
    void addNewCalculation(OperationType operationType, String result, String... input);
    List<Operation> retrieveOperations();
    List<Operation> retrieveLastOperation(int amount);
}
