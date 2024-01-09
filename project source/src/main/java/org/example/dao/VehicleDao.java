package org.example.dao;

import org.example.configuration.HibernateConfig;
import org.example.entity.Order;
import org.example.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VehicleDao extends AbstractDao<Vehicle>{

    public VehicleDao() {
        super(Vehicle.class);
    }

    /**
     * Retrieves a suitable vehicle for a given order based on payload capacity and company compatibility.
     *
     * @param order The order for which a suitable vehicle is requested.
     * @return The suitable Vehicle for the specified order.
     */
    public Vehicle getSuitableForOrder(Order order){
        Vehicle vehicle;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.createQuery(
                            " select v from Vehicle v " +
                                    " join fetch v.company c " +
                                    " where v.capacityUnit = :capacityUnit " +
                                    " and v.capacity >= :capacity " +
                                    " and c.id = :companyId ",
                            Vehicle.class)
                    .setParameter("capacityUnit", order.getPayload().getUnit())
                    .setParameter("capacity", order.getPayload().getUnitValue())
                    .setParameter("companyId", order.getCompany().getId())
                    .getResultStream().findFirst().orElseThrow();
            transaction.commit();
        }
        return vehicle;
    }

}
