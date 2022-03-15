package com.kargus.Customer.Website.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "scooters")
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
@Getter
@Setter
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private Integer year;

    private String manufacturer;

    private String model;

    private Integer engineSize;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public void validate() throws IllegalStateException {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        if (year == null || (year < 1950 || year > currentYear)) {
            throw new IllegalStateException("You must include a model year between 1950 and " + currentYear + ".");
        }
        if (manufacturer == null) {
            throw new IllegalStateException("You must include a manufacturer name.");
        }
        if (model == null) {
            throw new IllegalStateException("You must include a model name.");
        }
        if (engineSize == null || (engineSize < 50 || engineSize > 1000)) {
            throw new IllegalStateException("You must include an engine size (in cubic centimeters) between 50 and 1000.");
        }
    }
}
