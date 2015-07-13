package ru.cg.providerCRM.entity;

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
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "NOTE")
    private String note;

    @OneToMany
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producer producer = (Producer) o;

        if (!id.equals(producer.id)) return false;
        if (!name.equals(producer.name)) return false;
        if (!address.equals(producer.address)) return false;
        if (!phoneNumber.equals(producer.phoneNumber)) return false;
        if (!note.equals(producer.note)) return false;
        if (employees != null ? !employees.equals(producer.employees) : producer.employees != null) return false;
        if (providers != null ? !providers.equals(producer.providers) : producer.providers != null) return false;
        return !(tags != null ? !tags.equals(producer.tags) : producer.tags != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + note.hashCode();
        result = 31 * result + (employees != null ? employees.hashCode() : 0);
        result = 31 * result + (providers != null ? providers.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", employees=" + employees +
                ", providers=" + providers +
                ", tags=" + tags +
                '}';
    }


}
