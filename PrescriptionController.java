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
@RequestMapping("/api/prescription")
public class PrescriptionController {

    @Resource
    private PrescriptionRepository prescriptionRepository;

    @GetMapping
    public ResponseEntity<Iterable<Prescription>> getAllPrescription() {
        return new ResponseEntity<>(prescriptionRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createPrescription(@RequestBody Prescription prescription) {
        prescriptionRepository.save(prescription);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePrescription(@PathVariable long id, @RequestBody Prescription updatedPrescription) {
        Prescription prescription = prescriptionRepository.findById(id).orElse(null);
        if (prescription != null) {
            // Updates the prescription with the new data
            prescription.setMedicineType(updatedPrescription.getMedicineType());
            prescriptionRepository.save(prescription);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePrescription(@PathVariable long id) {
        Prescription prescription = prescriptionRepository.findById(id).orElse(null);
        if (prescription != null) {
            prescriptionRepository.delete(prescription);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
