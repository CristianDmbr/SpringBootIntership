package com.example.backend.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank //Validation.
    @Size(min = 2, max = 30, message = "The minimum and maximum lengths are 2 and 30 for adding a medicine name.")
    @Column(name = "medicinename")
    private String medicineName;

    @NotBlank //Validation.
    @Size(min = 2, max = 30, message = "The minimum and maximum lengths are 2 and 30 for adding the producer name.")
    @Column(name = "medicineproducer")
    private String producerName;

    @NotNull
    @FutureOrPresent(message = "Add an accurate date.") //Validation.
    @Column(name = "best_before_date")
    private LocalDate bestBeforeDate;

    @Column(name = "created_tm", insertable = false, updatable = false)
    private LocalDateTime createdTm;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

}
