package ru.cg.providerCRM.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "DOCUMENT")
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOCUMENT_ID")
    private Long id;

    @Column(name = "FILE_NAME")
    private String name;

    @Column(name = "FILE_EXTENSION")
    private String extension;

    @Column(name = "FILE")
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "PROVIDER_ID")
    private Provider provider;

}