package com.gaw.invoice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityLogService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String log){
        // suspend transaction yang sedang berjalan (tx1)
        // start transaction baru (tx2)
        // yang ada disini akan dijalankan dalam transaction baru (tx2)
        // commit/rollback tx2
        // resume tx1
    }
}
