package com.gaw.invoice.dao;

import com.gaw.invoice.entity.InvoiceType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceTypeDao extends PagingAndSortingRepository<InvoiceType, String> {
}
