package com.example.backend.controller;

import com.example.backend.model.Prescription;
import com.example.backend.repository.PrescriptionRepository;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/group")
public class PrescriptionController {

    @Resource
    private PrescriptionRepository prescriptionRepository;

    @GetMapping
    public ResponseEntity<Iterable<Prescription>> getAllPrescription() {
        return new ResponseEntity<>(prescriptionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable long id) {
        Optional<Prescription> prescription = this.prescriptionRepository.findById(id);
        if (prescription.isPresent()) {
            return new ResponseEntity<>(prescription.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createPrescription(@RequestBody Prescription prescription) {
        prescriptionRepository.save(prescription);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updatePrescription(@RequestBody Prescription prescription) {
        prescriptionRepository.save(prescription);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePrescriptionById(@PathVariable long id) {
        this.prescriptionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
