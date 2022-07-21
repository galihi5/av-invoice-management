package com.gaw.invoice.dao;

import com.gaw.invoice.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerDao extends PagingAndSortingRepository<Customer, String> {
    Optional<Customer> findByCode(String customerCode);
}
