
    private final MedicineRepository medicineRepository;

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = (List<Medicine>) medicineRepository.findAll();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable long id) {
        Optional<Medicine> medicine = medicineRepository.findById(id);
        return medicine.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicineById(@PathVariable long id) {
        medicineRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable long id, @RequestBody @Valid Medicine updatedMedicine) {
        Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
        if (optionalMedicine.isPresent()) {
            Medicine existingMedicine = optionalMedicine.get();
            existingMedicine.setMedicinename(updatedMedicine.getMedicinename());
            existingMedicine.setMedicineproducer(updatedMedicine.getMedicineproducer());
            existingMedicine.setBestbeforedate(updatedMedicine.getBestbeforedate());
            medicineRepository.save(existingMedicine);
            return new ResponseEntity<>(existingMedicine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable long id, @RequestBody @Valid Medicine updatedMedicine) {
        Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
        if (optionalMedicine.isPresent()) {
            Medicine existingMedicine = optionalMedicine.get();
            existingMedicine.setMedicinename(updatedMedicine.getMedicinename());
            existingMedicine.setMedicineproducer(updatedMedicine.getMedicineproducer());
            existingMedicine.setBestbeforedate(updatedMedicine.getBestbeforedate());
            medicineRepository.save(existingMedicine);
            return new ResponseEntity<>(existingMedicine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMedicines() {
        medicineRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






spring.datasource.url=jdbc:postgresql://localhost:5432/medicine
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect




