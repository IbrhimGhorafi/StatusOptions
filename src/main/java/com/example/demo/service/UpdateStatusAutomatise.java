package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.entity.Operation;
import com.example.demo.entity.Status;
import com.example.demo.entity.StatusLibelle;
import com.example.demo.repository.OperationRepository;
import com.example.demo.repository.StatusRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateStatusAutomatise {
    private static final Logger LOGGER = Logger.getLogger(UpdateStatusAutomatise.class.getName());

    private final OperationRepository operationRepository;
    private final StatusRepository statusRepository;

    public UpdateStatusAutomatise(OperationRepository operationRepository, StatusRepository statusRepository) {
        this.operationRepository = operationRepository;
        this.statusRepository = statusRepository;
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void verfierDate() {
        Status statusRepositoryByLibelle = statusRepository.findByLibelle(StatusLibelle.EN_VALIDATION);
        List<Operation> operations = operationRepository.findByDateMiseJourIsNull();

        LOGGER.log(Level.INFO, "Number of operations to update: {0}", operations.size());

        for (Operation operation : operations) {
            operation.setDateMiseJour(new Date());
            operation.setStatus(statusRepositoryByLibelle);
        }
        operationRepository.saveAll(operations);

        LOGGER.log(Level.INFO, "Updated {0} operations", operations.size());
    }
}
