package com.gaw.invoice.dao;

import com.gaw.invoice.entity.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentDao extends PagingAndSortingRepository<Payment, String> {
}
