package com.example.demo.controller;

import com.example.demo.dto.StatusDTO;
import com.example.demo.entity.StatusLibelle;
import com.example.demo.service.serviceContrat.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statuses")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<StatusDTO> getAllStatus() {
        return statusService.getAllStatus();
    }

    @GetMapping("/{statusLibelle}")
    public StatusDTO getStatusByLabelle(@PathVariable String statusLibelle) {
        return statusService.getStatusByLabelle(StatusLibelle.valueOf(statusLibelle));
    }

    @PostMapping
    public StatusDTO createStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.createStatus(statusDTO);
    }

    @PutMapping("/{id}")
    public StatusDTO updateStatus(@PathVariable Long id, @RequestBody StatusDTO statusDTO) {
        return statusService.updateStatus(id, statusDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
    }
}
