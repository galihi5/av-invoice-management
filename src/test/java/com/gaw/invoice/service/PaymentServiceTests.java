package com.gaw.invoice.service;

import com.gaw.invoice.exception.VirtualAccountAlreadyPaidException;
import com.gaw.invoice.exception.VirtualAccountNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentServiceTests {
    @Autowired private PaymentService paymentService;

    @Test
    public void testPay() throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {
        paymentService.pay(null, null, null, null, null);
    }
}
