package com.gaw.invoice.dao;

import com.gaw.invoice.entity.InvoiceType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface InvoiceTypeDao extends PagingAndSortingRepository<InvoiceType, String> {
    Optional<InvoiceType> findByCode(String code);
}
