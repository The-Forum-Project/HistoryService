package com.example.historyservice.dao;

import com.example.historyservice.domain.History;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryDao {
    protected SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void add(History history) {
        getCurrentSession().save(history);
    }

    public List<History> getAllByUserId(Long userId) {
        Session session = getCurrentSession();
        String sql = "FROM History WHERE userId = :userId ORDER BY viewDate DESC";
        Query<History> query = session.createQuery(sql, History.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
