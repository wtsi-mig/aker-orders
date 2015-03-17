package uk.ac.sanger.mig.aker.orders.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import uk.ac.sanger.mig.aker.orders.domain.Order;

/**
 * @author pi1
 * @since March 2015
 */
@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
}
