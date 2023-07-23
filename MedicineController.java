package com.example.backend.controller;

import com.example.backend.model.Medicine;
import com.example.backend.model.Prescription;
import com.example.backend.repository.MedicineRepository;

import com.example.backend.repository.PrescriptionRepository;
import jakarta.annotation.Resource;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/medicine")
public class MedicineController {

    @Resource
    private final PrescriptionRepository prescriptionRepository;

    @Resource
    private final MedicineRepository medicineRepository;

    //Method displaying all the data from the table.
    @GetMapping
    @ResponseBody
    public ResponseEntity<Iterable<Medicine>> getAllMedicine() {
        return new ResponseEntity<>(medicineRepository.findAll(), HttpStatus.OK);
    }

    //Method displaying specific data by id from the table.
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable long id) {
        Optional<Medicine> medicine = medicineRepository.findById(id);
        if (medicine.isPresent()) {
            return new ResponseEntity<>(medicine.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    //Methods that deletes data from table by ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMedicineById(@PathVariable long id) {
        medicineRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Method that all the data from the table.
    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteAllMedicine() {
        medicineRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //Method that allows to insert data into the table.
    @PostMapping
    public ResponseEntity<Medicine> createMedicine(@RequestBody @Valid Medicine medicine) {
        if (medicine.getPrescription() != null) {
            Prescription prescription = medicine.getPrescription();
            if (prescription.getId() == 0) {
                // If the prescription ID is 0, it means the prescription is not saved in the database.
                Prescription savedPrescription = prescriptionRepository.save(prescription);
                medicine.setPrescription(savedPrescription);
            } else {
                // If the prescription ID is not 0, it means the prescription might already exist in the database.
                // In this case, just fetch the existing prescription by ID and associate it with the medicine.
                Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(prescription.getId());
                if (prescriptionOptional.isPresent()) {
                    Prescription existingPrescription = prescriptionOptional.get();
                    medicine.setPrescription(existingPrescription);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        }

        Medicine savedMedicine = medicineRepository.save(medicine);
        return new ResponseEntity<>(savedMedicine, HttpStatus.CREATED);
    }



    //Method updating the data from the table.
    @PutMapping("/medicines{id}/edit")
    public ResponseEntity<HttpStatus> updateMedicine(@RequestBody @Valid Medicine medicine) {
        medicineRepository.save(medicine);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
