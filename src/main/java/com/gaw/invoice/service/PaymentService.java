package com.gaw.invoice.service;

import com.gaw.invoice.dao.InvoiceDao;
import com.gaw.invoice.dao.VirtualAccountDao;
import com.gaw.invoice.entity.Invoice;
import com.gaw.invoice.entity.PaymentProvider;
import com.gaw.invoice.entity.StatusRecord;
import com.gaw.invoice.entity.VirtualAccount;
import com.gaw.invoice.exception.PaymentExceedInvoiceAmountException;
import com.gaw.invoice.exception.VirtualAccountAlreadyPaidException;
import com.gaw.invoice.exception.VirtualAccountNotFoundException;
import com.gaw.invoice.helper.VirtualAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service @Transactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = {
        VirtualAccountAlreadyPaidException.class,
        VirtualAccountNotFoundException.class,
        PaymentExceedInvoiceAmountException.class
})
public class PaymentService {

    @Autowired private ActivityLogService activityLogService;
    @Autowired private VirtualAccountDao virtualAccountDao;
    @Autowired private InvoiceDao invoiceDao;
    @Autowired private VirtualAccountHelper virtualAccountHelper;

    @Transactional(
            rollbackFor = {
                    VirtualAccountNotFoundException.class,
                    VirtualAccountAlreadyPaidException.class,
                    PaymentExceedInvoiceAmountException.class
            })
    public void pay(PaymentProvider provider, String companyId, String accountNumber, BigDecimal amount, String reference) throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {
//        begin tx1
//        activityLogService.log("Start payment VA " + accountNumber);

        VirtualAccount va = virtualAccountHelper.getExistingVirtualAccount(virtualAccountDao, provider, companyId, accountNumber);
        virtualAccountHelper.checkVaAlreadyPaid(provider, companyId, accountNumber, va);
        virtualAccountHelper.checkPaymentAmount(va, amount);

        // 3. cek apakah amount pembayaran > nilai tagihan
        // 4. update status VA menjadi lunas
        va.setStatusRecord(StatusRecord.INACTIVE);

        // 5. update status invoice menjadi lunas
        Invoice invoice = va.getInvoice();
        invoice.setTotalPayment(invoice.getTotalPayment().add(amount));
        invoice.setPaymentStatus(Invoice.PaymentStatus.FULL);
        invoiceDao.save(invoice);

        // 6. insert ke tabel payment
        // 7. notifikasi (next fase)

//        commit tx1

    }

}
