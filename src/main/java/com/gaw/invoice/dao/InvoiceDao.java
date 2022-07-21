package com.gaw.invoice.dao;

import com.gaw.invoice.entity.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceDao extends CrudRepository<Invoice, String> {
}
