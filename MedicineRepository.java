package com.test.springboot.Repository;

import com.test.springboot.Entity.Medicine;
import org.springframework.data.repository.CrudRepository;

public interface MedicineRepository extends CrudRepository <Medicine,Long> {
}
