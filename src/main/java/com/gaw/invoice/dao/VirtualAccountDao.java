package com.gaw.invoice.dao;

import com.gaw.invoice.entity.PaymentProvider;
import com.gaw.invoice.entity.VirtualAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface VirtualAccountDao extends PagingAndSortingRepository<VirtualAccount, String> {
    Optional<VirtualAccount> findByPaymentProviderAndCompanyIdAndAccountNumber(PaymentProvider provider, String companyId, String accountNumber);
}
