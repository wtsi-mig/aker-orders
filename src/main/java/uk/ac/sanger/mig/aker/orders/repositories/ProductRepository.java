package uk.ac.sanger.mig.aker.orders.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uk.ac.sanger.mig.aker.orders.domain.Product;

/**
 * @author pi1
 * @since March 2015
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
