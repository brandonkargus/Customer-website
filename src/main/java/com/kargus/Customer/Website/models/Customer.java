package com.kargus.Customer.Website.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email_address")
    private String emailAddress;

    private Integer age;

    private String address;

    @OneToOne
    @JoinColumn(name = "scooter_id")
    private Scooter scooter;

    public void validate() throws IllegalStateException {
        if (fullName == null) {
            throw new IllegalStateException("You must include a customer name");
        }
        if (emailAddress == null) {
            throw new IllegalStateException("You must include an email address");
        }
        if (age == null || (age < 1 || age > 110)) {
            throw new IllegalStateException("You must include an age between 1 and 110");
        }
        if (address == null) {
            throw new IllegalStateException("You must include an address");
        }
    }

}
