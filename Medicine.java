package com.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDateTime;
import java.util.Date;

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

    @NotBlank
    @Size(min = 2, max = 30, message = "The minimum and maximum lengths are 2 and 30 for adding a medicine name.")
    @Column(name = "medicinename")
    private String medicineName;

    @NotBlank
    @Size(min = 2, max = 30, message = "The minimum and maximum lengths are 2 and 30 for adding the producer name.")
    @Column(name = "medicineproducer")
    private String producerName;

    @NotNull
    @PastOrPresent(message = "Add a accurate date.")
    @Column(name = "best_before_date")
    private Date bestBeforeDate;

    @Column(name = "created_tm", insertable = false, updatable = false)
    private LocalDateTime createdTm;

}
