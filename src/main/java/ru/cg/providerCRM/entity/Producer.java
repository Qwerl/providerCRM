package ru.cg.providerCRM.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCER")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCER_ID")
    private Long id;

    @Column(name = "NAME")
    @NotBlank
    private String name;

    @Column(name = "ADDRESS")
    @NotBlank
    private String address;

    @Column(name = "PHONE_NUMBER")
    @NotBlank
    private String phoneNumber;

    @Column(name = "NOTE")
    @NotBlank
    private String note;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Employee> employees = new ArrayList<Employee>();

    @ManyToMany
    private List<Provider> providers = new ArrayList<Provider>();

    @ManyToMany
    private List<Tag> tags = new ArrayList<Tag>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", note='" + note + '\'' +
                ", employees=" + employees +
                ", providers=" + providers +
                ", tags=" + tags +
                '}';
    }

}
