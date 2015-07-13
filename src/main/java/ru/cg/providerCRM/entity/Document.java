package ru.cg.providerCRM.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "DOCUMENT")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (!id.equals(document.id)) return false;
        if (!name.equals(document.name)) return false;
        if (!extension.equals(document.extension)) return false;
        return Arrays.equals(file, document.file);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + extension.hashCode();
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }
}
