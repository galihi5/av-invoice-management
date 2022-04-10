package com.gaw.invoice.service;

import com.gaw.invoice.dao.VirtualAccountDao;
import com.gaw.invoice.entity.PaymentProvider;
import com.gaw.invoice.entity.VirtualAccount;
import com.gaw.invoice.exception.VirtualAccountAlreadyPaidException;
import com.gaw.invoice.exception.VirtualAccountNotFoundException;
import com.gaw.invoice.helper.VirtualAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service @Transactional
public class PaymentService {

    @Autowired private VirtualAccountDao virtualAccountDao;

    public void pay(PaymentProvider provider, String companyId, String accountNumber, BigDecimal amount, String reference) throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {
        VirtualAccount va = VirtualAccountHelper.getExistingVirtualAccount(virtualAccountDao, provider, companyId, accountNumber);
        VirtualAccountHelper.checkVaAlreadyPaid(provider, companyId, accountNumber, va);

        // 3. cek apakah amount pembayaran > nilai tagihan
        // 4. update status VA menjadi lunas
        // 5. update status invoice menjadi lunas
        // 6. insert ke tabel payment
        // 7. notifikasi (next fase)

    }




}
