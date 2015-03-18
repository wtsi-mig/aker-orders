package uk.ac.sanger.mig.aker.orders.repositories;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import uk.ac.sanger.mig.aker.orders.domain.Order;

/**
 * @author pi1
 * @since March 2015
 */
@Repository
@RepositoryRestResource(exported = false)
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	Collection<Order> findByOwner(String owner);

}
