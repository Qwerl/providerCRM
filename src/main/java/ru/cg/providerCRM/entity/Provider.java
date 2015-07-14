package ru.cg.providerCRM.entity;

import javax.persistence.*;
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
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "STORAGE_ADDRESS")
    private String storageAddress;

    @Column(name = "NOTE")
    private String note;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL,  orphanRemoval = true)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Provider provider = (Provider) o;

        if (!id.equals(provider.id)) return false;
        if (!name.equals(provider.name)) return false;
        if (employees != null ? !employees.equals(provider.employees) : provider.employees != null) return false;
        if (!address.equals(provider.address)) return false;
        if (!phoneNumber.equals(provider.phoneNumber)) return false;
        if (!storageAddress.equals(provider.storageAddress)) return false;
        if (!note.equals(provider.note)) return false;
        if (tags != null ? !tags.equals(provider.tags) : provider.tags != null) return false;
        if (products != null ? !products.equals(provider.products) : provider.products != null) return false;
        return !(documents != null ? !documents.equals(provider.documents) : provider.documents != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (employees != null ? employees.hashCode() : 0);
        result = 31 * result + address.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + storageAddress.hashCode();
        result = 31 * result + note.hashCode();
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (documents != null ? documents.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", storageAddress='" + storageAddress + '\'' +
                '}';
    }


}
