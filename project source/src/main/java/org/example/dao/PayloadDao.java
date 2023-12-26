package org.example.dao;

import org.example.configuration.HibernateConfig;
import org.example.entity.Payload;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PayloadDao extends AbstractDao<Payload>{

    public PayloadDao() {
        super(Payload.class);
    }

    public Payload getPayloadByQualification(String qualification) {
        Payload payload;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            payload = session.createQuery(
                            " select p from Payload p " +
                                    " join p.payloadQualification pQ " +
                                    " where pQ.qualification = :qualification ",
                            Payload.class)
                    .setParameter("qualification", qualification)
                    .getSingleResult();
            transaction.commit();
        }
        return payload;
    }

}
