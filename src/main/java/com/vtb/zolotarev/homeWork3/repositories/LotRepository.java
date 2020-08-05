package com.vtb.zolotarev.homeWork3.repositories;

import com.vtb.zolotarev.homeWork3.entity.Lot;
import com.vtb.zolotarev.homeWork3.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

public class LotRepository {
    private static SessionFactory sessionFactory;


    public static void setSessionFactory(SessionFactory sessionFactory) {
        LotRepository.sessionFactory = sessionFactory;
    }

    public void optimisticRaiseUserBid(long userId, long bidId) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            try {
                Lot currentLot = session.createQuery("SELECT l FROM Lot l WHERE l.id=:id", Lot.class).setLockMode(LockModeType.OPTIMISTIC).setParameter("id", bidId).getSingleResult();
                currentLot.setActualRate(currentLot.getActualRate() + 100);
                currentLot.setUser(session.createNamedQuery("findUserById", User.class).setParameter("id", userId).getSingleResult());
                session.getTransaction().commit();
            } catch (OptimisticLockException ex) {
                session.getTransaction().rollback();
                optimisticRaiseUserBid(userId, bidId);
            }
        }
    }

    public void pessimisticRaiseUserBid(long userId, long bidId) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Lot currentLot = session.createQuery("SELECT l FROM Lot l WHERE l.id=:id", Lot.class).setLockMode(LockModeType.PESSIMISTIC_WRITE).setHint("javax.persistence.lock.timeout", 5000).setParameter("id", bidId).getSingleResult();
            currentLot.setActualRate(currentLot.getActualRate() + 100);
            currentLot.setUser(session.createNamedQuery("findUserById", User.class).setParameter("id", userId).getSingleResult());
            session.getTransaction().commit();
        }
    }

    public long getAllLotsSum(){
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            long answer=session.createQuery("SELECT l FROM Lot l", Lot.class).getResultList().stream().mapToLong(Lot::getActualRate).sum();
            session.getTransaction().commit();
            return answer;
        }
    }
}
