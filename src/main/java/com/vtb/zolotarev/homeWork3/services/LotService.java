package com.vtb.zolotarev.homeWork3.services;

import com.vtb.zolotarev.homeWork3.repositories.LotRepository;

public class LotService {
    private static LotRepository lotRepository = new LotRepository();
    public static void optimisticRaiseUserBid(long userId,long lotId){
        lotRepository.optimisticRaiseUserBid(userId,lotId);
    }

    public static void pessimisticRaiseUserBid(long userId,long lotId){
        lotRepository.pessimisticRaiseUserBid(userId,lotId);
    }

    public static long getAllLotsSum(){
        return lotRepository.getAllLotsSum();
    }
}
