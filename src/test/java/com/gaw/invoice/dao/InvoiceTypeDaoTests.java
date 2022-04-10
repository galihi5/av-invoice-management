package com.gaw.invoice.dao;

import com.gaw.invoice.entity.InvoiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"/sql/delete-invoice-type.sql", "/sql/insert-inactive-invoice-type.sql"})
public class InvoiceTypeDaoTests {

    @Autowired InvoiceTypeDao invoiceTypeDao;

    @Test
    public void testInsertInvoiceType() throws InterruptedException {
        InvoiceType it = new InvoiceType();
        it.setCode("IT-001");
        it.setName("Invoice Type Test");
        Assertions.assertNull(it.getId());
        invoiceTypeDao.save(it);

        System.out.println("ID : " + it.getId());
        System.out.println("Create Time : " + it.getCreated());
        System.out.println("Create by : " + it.getCreatedBy());
        System.out.println("Update Time : " + it.getUpdated());
        System.out.println("Update by : " + it.getUpdatedBy());
        System.out.println("Status Record : " + it.getStatusRecord());

        Assertions.assertNotNull(it.getId());
        Assertions.assertNotNull(it.getCreated());
        Assertions.assertNotNull(it.getCreatedBy());
        Assertions.assertNotNull(it.getUpdated());
        Assertions.assertNotNull(it.getUpdatedBy());
        Assertions.assertNotNull(it.getStatusRecord());
        Assertions.assertEquals(it.getCreated(), it.getUpdated());

        Thread.sleep(1000);
        it.setName("Test Update");
        it = invoiceTypeDao.save(it);
        System.out.println("Create Time : " + it.getCreated());
        System.out.println("Update Time : " + it.getUpdated());
        Assertions.assertNotEquals(it.getCreated(), it.getUpdated());
    }

    @Test
    public void testQuerySoftDelete(){
        Long jumlahRecord = invoiceTypeDao.count();
        System.out.println("Jumlah Record : " + jumlahRecord);

        Assertions.assertEquals(1, jumlahRecord);
    }

    @Test
    public void testSoftDelete(){
        InvoiceType it = invoiceTypeDao.findById("test002").get();
        System.out.println("InvoiceType soft delete : " + it.getId());

        Iterable<InvoiceType> invoiceTypes = invoiceTypeDao.findAll();
        invoiceTypes.forEach((invoiceType) -> {
            System.out.println(invoiceType.getId() + " | " + invoiceType.getStatusRecord());
        });

        invoiceTypeDao.delete(it);
        System.out.println("After soft delete...");
        invoiceTypes = invoiceTypeDao.findAll();
        invoiceTypes.forEach((invoiceType) -> {
            System.out.println(invoiceType.getId() + " | " + invoiceType.getStatusRecord());
        });

        Long jumlahRecord = invoiceTypeDao.count();
        System.out.println("Jumlah Record : " + jumlahRecord);

        Assertions.assertEquals(0, jumlahRecord);
    }

}
