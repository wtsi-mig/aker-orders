package uk.ac.sanger.mig.aker.orders.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import uk.ac.sanger.mig.aker.orders.domain.Sample;

/**
 * @author pi1
 * @since March 2015
 */
@Repository
@RepositoryRestResource(exported = false)
public interface SampleRepository extends CrudRepository<Sample, Long> {
}
