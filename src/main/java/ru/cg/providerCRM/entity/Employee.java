package ru.cg.providerCRM.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "HOME_PHONE_NUMBER")
    private String homePhoneNumber;

    @Column(name = "WORK_PHONE_NUMBER")
    private String workPhoneNumber;

    @Column(name = "POSITION")
    private String position;

    @ManyToOne
    @JoinColumn(name = "PROVIDER_ID")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "PRODUCER_ID")
    private Producer producer;

}