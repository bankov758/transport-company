package org.example.dao;

import org.example.configuration.HibernateConfig;
import org.example.entity.Client;
import org.example.exception.DuplicateEntityException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDao extends AbstractDao<Client>{
    public ClientDao() {
        super(Client.class);
    }

    @Override
    public void create(Client entity) {
        checkIfClientExists(entity);
        super.create(entity);
    }

    @Override
    public void update(Client entity) {
        checkIfClientExists(entity);
        super.update(entity);
    }

    private void checkIfClientExists(Client entity) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.createQuery(
                            "select c from Client c" +
                                    " where c.firstName = :firstName " +
                                    " and c.lastName = :lastName " +
                                    " and c.ssn = :ssn ",
                            Client.class)
                    .setParameter("firstName", entity.getFirstName())
                    .setParameter("lastName", entity.getLastName())
                    .setParameter("ssn", entity.getSsn())
                    .getSingleResultOrNull();
            transaction.commit();
            if (client != null) {
                throw new DuplicateEntityException(
                        Client.class.getSimpleName(),
                        "name and ssn",
                        entity.getFirstName() + " " + entity.getLastName() + " " + entity.getSsn());
            }
        }
    }
}
