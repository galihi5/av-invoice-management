package com.gaw.invoice.service;

import com.gaw.invoice.dao.RunningNumberDao;
import com.gaw.invoice.entity.RunningNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RunningNumberService {

    @Autowired private RunningNumberDao runningNumberDao;

    public Long getNumber(String prefix){
        RunningNumber rn = runningNumberDao.findByPrefix(prefix);
        if (rn == null){
            rn = new RunningNumber();
            rn.setPrefix(prefix);
        }

        rn.setLastNumber(rn.getLastNumber() + 1);
        runningNumberDao.save(rn);
        return rn.getLastNumber();
    }
}
