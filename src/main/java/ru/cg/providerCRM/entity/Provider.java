package ru.cg.providerCRM.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROVIDER")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROVIDER_ID")
    private Long id;

    @Column(name = "NAME")
    @NotBlank
    @Size(min = 1, max = 255)
    private String name;

    @Column(name = "ADDRESS")
    @NotBlank
    @Size(min = 1, max = 255)
    private String address;

    @Column(name = "PHONE_NUMBER")
    @NotEmpty
    @Size(min = 1, max = 255)
    private String phoneNumber;

    @Column(name = "STORAGE_ADDRESS")
    @NotEmpty
    @Size(min = 0, max = 255)
    private String storageAddress;

    @Column(name = "NOTE")
    @NotEmpty
    @Size(min = 0, max = 255)
    private String note;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<Employee>();

    @OneToMany
    private List<Document> documents = new ArrayList<Document>();

    @ManyToMany
    private List<Tag> tags = new ArrayList<Tag>();

    @ManyToMany
    private List<Product> products = new ArrayList<Product>();

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void removeDocument(Document document) {
        documents.remove(document);
    }

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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

    public String getStorageAddress() {
        return storageAddress;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", storageAddress='" + storageAddress + '\'' +
                ", note='" + note + '\'' +
                ", employees=" + employees +
                ", documents=" + documents +
                ", tags=" + tags +
                ", products=" + products +
                '}';
    }

}
