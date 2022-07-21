package com.gaw.invoice.helper;

import com.gaw.invoice.dao.VirtualAccountDao;
import com.gaw.invoice.entity.PaymentProvider;
import com.gaw.invoice.entity.VirtualAccount;
import com.gaw.invoice.exception.VirtualAccountAlreadyPaidException;
import com.gaw.invoice.exception.VirtualAccountNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class VirtualAccountHelper {
    public VirtualAccount getExistingVirtualAccount(VirtualAccountDao virtualAccountDao, PaymentProvider provider, String companyId, String accountNumber) throws VirtualAccountNotFoundException {
        Optional<VirtualAccount> optVa = virtualAccountDao.findByPaymentProviderAndCompanyIdAndAccountNumber(provider, companyId, accountNumber);
        if (!optVa.isPresent()){
            throw  new VirtualAccountNotFoundException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] not found");
        }
        VirtualAccount va = optVa.get();
        return va;
    }

    public void checkVaAlreadyPaid(PaymentProvider provider, String companyId, String accountNumber, VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()){
            throw new VirtualAccountAlreadyPaidException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] already paid");
        }
    }

    public void checkPaymentAmount(VirtualAccount va, BigDecimal amount){

    }
}
