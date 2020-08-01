package com.vtb.zolotarev.homeWork3;

import com.vtb.zolotarev.homeWork3.repositories.LotRepository;
import com.vtb.zolotarev.homeWork3.services.LotService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class OptimisticAndPessimisticMethods {
    private static SessionFactory sessionFactory;

    public static void optimisticLockScene() {
        initFactory();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        CountDownLatch countDownLatch = new CountDownLatch(8);
        for (int i = 1; i < 9; i++) {
            int userId = i;
            executorService.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    int lotNumber = (int) (Math.random() * 4 + 1);
                    LotService.optimisticRaiseUserBid(userId, lotNumber);
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "закончил повышать ставку на лоты!");
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
            executorService.shutdown();
            sessionFactory.close();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void pessimisticLockScene() {
        initFactory();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        CountDownLatch countDownLatch = new CountDownLatch(8);
        for (int i = 1; i < 9; i++) {
            int userId = i;
            executorService.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    int lotNumber = (int) (Math.random() * 4 + 1);
                    LotService.pessimisticRaiseUserBid(userId, lotNumber);
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "закончил повышать ставку на лоты!");
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
            executorService.shutdown();
            sessionFactory.close();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static long getAllLotsSum(){
        initFactory();
        long answer = LotService.getAllLotsSum();
        sessionFactory.close();
        return answer;
    }

    private static void initFactory() {
        sessionFactory = new Configuration()
                .configure("homeWork3/config/hibernate.cfg.xml")
                .buildSessionFactory();
        LotRepository.setSessionFactory(sessionFactory);
    }
}
