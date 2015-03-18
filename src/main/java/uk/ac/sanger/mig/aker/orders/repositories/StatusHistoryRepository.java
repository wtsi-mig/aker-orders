package uk.ac.sanger.mig.aker.orders.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import uk.ac.sanger.mig.aker.orders.domain.Sample;
import uk.ac.sanger.mig.aker.orders.domain.StatusHistory;

/**
 * @author pi1
 * @since March 2015
 */
@Repository
@RepositoryRestResource(exported = false)
public interface StatusHistoryRepository extends CrudRepository<StatusHistory, Long> {

	@Query("select sh from StatusHistory sh where sh.sample = :sample ")
	StatusHistory currentStatus(@Param("sample") Sample sample);
}
