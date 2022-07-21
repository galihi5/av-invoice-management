package com.gaw.invoice.controller;

import com.gaw.invoice.dao.CustomerDao;
import com.gaw.invoice.dao.InvoiceTypeDao;
import com.gaw.invoice.dto.CreateInvoiceRequestDto;
import com.gaw.invoice.entity.Customer;
import com.gaw.invoice.entity.InvoiceType;
import com.gaw.invoice.exception.CustomerNotFoundException;
import com.gaw.invoice.exception.InvoiceTypeNotFoundException;
import com.gaw.invoice.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController @RequestMapping("/api/invoice")
public class InvoiceApiController {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    InvoiceTypeDao invoiceTypeDao;

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/")
    public void createInvoice(@RequestBody @Valid CreateInvoiceRequestDto createInvoiceRequestDto) throws CustomerNotFoundException, InvoiceTypeNotFoundException {
        Customer customer = customerDao.findByCode(createInvoiceRequestDto.getCustomerCode())
                .orElseThrow(() -> new CustomerNotFoundException("Customer code " + createInvoiceRequestDto.getCustomerCode() + " not found"));

        InvoiceType invoiceType = invoiceTypeDao.findByCode(createInvoiceRequestDto.getInvoiceTypeCode())
                .orElseThrow(() -> new InvoiceTypeNotFoundException("Invoice type " + createInvoiceRequestDto.getInvoiceTypeCode() + " not found"));

        invoiceService.createInvoice();

        log.info("Invoice created");
    }
}
