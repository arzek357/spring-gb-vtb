package com.vtb.zolotarev.homeWork3;

import com.vtb.zolotarev.homeWork3.validation.PrepareDataApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAllMethods {


    /*
    * Блокировка       Тест1    Тест2
    * Оптимистичная   16.217s  15.882s
    * Пессимистичная  6.552s    6,875s
    * */
    @Test
    public void optimisticLockTest(){
        PrepareDataApp.forcePrepareData();
        long time = System.currentTimeMillis();
        OptimisticAndPessimisticMethods.optimisticLockScene();
        System.out.println(System.currentTimeMillis()-time);
        Assertions.assertEquals(800_000,OptimisticAndPessimisticMethods.getAllLotsSum());
    }

    @Test
    public void pessimisticLockTest(){
        PrepareDataApp.forcePrepareData();
        long time = System.currentTimeMillis();
        OptimisticAndPessimisticMethods.pessimisticLockScene();
        System.out.println(System.currentTimeMillis()-time);
        Assertions.assertEquals(800_000,OptimisticAndPessimisticMethods.getAllLotsSum());
    }
}
