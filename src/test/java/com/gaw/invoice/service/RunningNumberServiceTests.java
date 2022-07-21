package com.gaw.invoice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
public class RunningNumberServiceTests {

    @Autowired
    private RunningNumberService runningNumberService;

//    @Test
    public void testGetNumber() {
        Long hasil = runningNumberService.getNumber("Test");
        Assertions.assertNotNull(hasil);
        System.out.println("Hasil = " + hasil);
    }

//    @Test
    public void testGetNumberMultithreaded() throws InterruptedException {
        int jumlahThread = 10;
        int iterasi = 5;

        ConcurrentHashMap hasilMap = new ConcurrentHashMap();

        for (int i = 0; i < jumlahThread; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < iterasi; j++) {
                        List<Long> lastNumbers = (List<Long>) hasilMap.get(this.getId());
                        if (lastNumbers == null){
                            lastNumbers = new ArrayList<>();
                        }

                        Long hasil = runningNumberService.getNumber("Test");
                        System.out.println("Thread ["+this.getId()+"] Last = " + hasil);
                        lastNumbers.add(hasil);
                        hasilMap.put(this.getId(), lastNumbers);
                    }
                }
            };
            t.start();
        }

        Thread.sleep(10 * 1000);
        Enumeration<Long> keys = hasilMap.keys();
        while (keys.hasMoreElements()) {
            Long key = keys.nextElement();
            System.out.println("===== Thread " + key + " =====");
            System.out.println(hasilMap.get(key));
        }
    }
}
