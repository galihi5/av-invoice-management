package com.gaw.invoice.dao;

import com.gaw.invoice.entity.RunningNumber;
import org.springframework.data.repository.CrudRepository;

public interface RunningNumberDao extends CrudRepository<RunningNumber, String> {
    RunningNumber findByPrefix(String prefix);
}
