package com.example.backend.controller;

import com.example.backend.model.Prescription;
import com.example.backend.repository.PrescriptionRepository;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            // Update the prescription with the new data
            prescription.setMedicineType(updatedPrescription.getMedicineType());


            // Save the updated prescription
            prescriptionRepository.save(prescription);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Prescription with the given ID not found
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
