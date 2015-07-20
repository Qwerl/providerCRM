package ru.cg.providerCRM.entity;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Column(name = "FULL_NAME", unique = true)
    private String fullName;

    @Column(name = "EMAIL", unique = true)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", homePhoneNumber='" + homePhoneNumber + '\'' +
                ", workPhoneNumber='" + workPhoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", provider=" + provider +
                ", producer=" + producer +
                '}';
    }
}
