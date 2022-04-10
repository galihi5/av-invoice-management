package com.gaw.invoice.helper;

import com.gaw.invoice.dao.VirtualAccountDao;
import com.gaw.invoice.entity.PaymentProvider;
import com.gaw.invoice.entity.VirtualAccount;
import com.gaw.invoice.exception.VirtualAccountAlreadyPaidException;
import com.gaw.invoice.exception.VirtualAccountNotFoundException;

import java.util.Optional;

public class VirtualAccountHelper {
    public static VirtualAccount getExistingVirtualAccount(VirtualAccountDao virtualAccountDao, PaymentProvider provider, String companyId, String accountNumber) throws VirtualAccountNotFoundException {
        Optional<VirtualAccount> optVa = virtualAccountDao.findByPaymentProviderAndCompanyIdAndAccountNumber(provider, companyId, accountNumber);
        if (!optVa.isPresent()){
            throw  new VirtualAccountNotFoundException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] not found");
        }
        VirtualAccount va = optVa.get();
        return va;
    }

    public static void checkVaAlreadyPaid(PaymentProvider provider, String companyId, String accountNumber, VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()){
            throw new VirtualAccountAlreadyPaidException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] already paid");
        }
    }
}
