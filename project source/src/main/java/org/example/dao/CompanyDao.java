package org.example.dao;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.configuration.HibernateConfig;
import org.example.entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CompanyDao extends AbstractDao<Company> {

    public CompanyDao(Class<Company> clazz) {
        super(clazz);
    }

    public static List<Company> getCompaniesByName(String name, boolean enableLike) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
            Root<Company> root = criteriaQuery.from(Company.class);
            if (enableLike) {
                criteriaQuery.select(root).where(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            } else {
                criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));
            }
            Query<Company> query = session.createQuery(criteriaQuery);
            List<Company> companies = query.getResultList();
            return companies;
        }
    }

}
