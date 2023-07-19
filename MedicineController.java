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

    @GetMapping
    public ResponseEntity<Iterable<Medicine>> getAllMedicine() {
        return new ResponseEntity<>(medicineRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable long id) {
        Optional<Medicine> student = medicineRepository.findById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMedicineById(@PathVariable long id) {
        medicineRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Medicine> createMedicine(@RequestBody @Valid Medicine medicine) {
        return new ResponseEntity<>(medicineRepository.save(medicine), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateMedicine(@RequestBody @Valid Medicine medicine) {
        medicineRepository.save(medicine);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
