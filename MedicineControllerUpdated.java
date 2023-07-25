package com.example.backend.controller;

import com.example.backend.model.Medicine;
import com.example.backend.repository.MedicineRepository;

import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
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
import javax.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/medicine")
public class MedicineController {


    @Resource
    private final MedicineRepository medicineRepository;

    @GetMapping
    public ResponseEntity<Iterable<Medicine>> getAllMedicines() {
        Sort sortByIdAsc = Sort.by(Sort.Direction.ASC, "id");
        return new ResponseEntity<>(medicineRepository.findAll(sortByIdAsc), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable long id) {
        Optional<Medicine> medicine = medicineRepository.findById(id);
        return medicine.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
    public ResponseEntity<Medicine> updateMedicine(@RequestBody @Valid Medicine medicine) {
        return new ResponseEntity<>(medicineRepository.save(medicine), HttpStatus.OK);
    }

}
