package com.test.springboot.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "medicine_data_base")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicinename;
    private String medicineproducer;
    private Date bestbeforedate;

    public Medicine(String medicinename, String medicineproducer,Date bestbeforedate){
        this.medicinename = medicinename;
        this.medicineproducer = medicineproducer;
        this.bestbeforedate = bestbeforedate;
    }

    public Medicine() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public String getMedicineproducer() {
        return medicineproducer;
    }

    public void setMedicineproducer(String medicineproducer) {
        this.medicineproducer = medicineproducer;
    }

    public Date getBestbeforedate() {
        return bestbeforedate;
    }

    public void setBestbeforedate(Date bestbeforedate) {
        this.bestbeforedate = bestbeforedate;
    }
}
