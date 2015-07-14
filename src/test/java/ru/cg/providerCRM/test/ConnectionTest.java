package ru.cg.providerCRM.test;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import ru.cg.providerCRM.entity.*;
import ru.cg.providerCRM.util.HibernateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

public class ConnectionTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        File file = new File("C:\\test\\test.bmp");
        byte[] bFile = new byte[(int) file.length()];
        String extension = FilenameUtils.getExtension(file.getName());

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Document document = new Document();
        document.setName(file.getName());
        document.setFile(bFile);
        document.setExtension("." + extension);
        session.save(document);

        Tag firstTag = new Tag();
        firstTag.setTagText("firstTag");
        session.save(firstTag);

        Tag secondTag = new Tag();
        secondTag.setTagText("secondTag");
        session.save(secondTag);


        Product table = new Product();
        table.setName("Стол");
        table.setPrice(150500L);
        session.save(table);

        Provider provider = new Provider();
        provider.setName("TestProvider");
        provider.setAddress("Test address55");
        provider.setPhoneNumber("111-111-111");
        provider.setStorageAddress("Test storage address");
        provider.setTags(Arrays.asList(firstTag));
        List<Product> products = Arrays.asList(table);
        provider.setProducts(products);
        provider.setNote("test note provider");
        provider.setDocuments(Arrays.asList(document));
        session.save(provider);

        Producer producer = new Producer();
        producer.setName("TestProducer");
        producer.setAddress("Test producer address66");
        producer.setPhoneNumber("888-888-888");
        producer.setTags(Arrays.asList(secondTag, firstTag));
        producer.setNote("test note producer");
        session.save(producer);
        producer.setProviders(Arrays.asList(provider));


        Employee vasya = new Employee();
        vasya.setFullName("Vaskan");
        vasya.setEmail("test@test.com");
        vasya.setHomePhoneNumber("555-555-555");
        vasya.setWorkPhoneNumber("777-777-777");
        vasya.setPosition("Director");
        vasya.setProducer(producer);
        session.save(vasya);

        Employee dima = new Employee();
        dima.setFullName("Diman");
        dima.setEmail("test2@test.com");
        dima.setHomePhoneNumber("555-555-5552");
        dima.setWorkPhoneNumber("777-777-7772");
        dima.setPosition("Manager");
        dima.setProvider(provider);
        session.save(dima);


        session.getTransaction().commit();

        session.close();
    }
}
