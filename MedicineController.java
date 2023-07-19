package com.example.backend.controller;

import com.example.backend.model.Medicine;
import com.example.backend.repository.MedicineRepository;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/medicine")
public class MedicineController {

    @Resource
    private final MedicineRepository medicineRepository;

    //Method displaying all the data from the table.
    @GetMapping
    public ResponseEntity<Iterable<Medicine>> getAllMedicine() {
        return new ResponseEntity<>(medicineRepository.findAll(), HttpStatus.OK);
    }

    //Method displaying specific data by id from the table.
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable long id) {
        Optional<Medicine> student = medicineRepository.findById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
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
        return new ResponseEntity<>(medicineRepository.save(medicine), HttpStatus.CREATED);
    }

    //Method updating the data from the table.
    @PutMapping
    public ResponseEntity<HttpStatus> updateMedicine(@RequestBody @Valid Medicine medicine) {
        medicineRepository.save(medicine);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
