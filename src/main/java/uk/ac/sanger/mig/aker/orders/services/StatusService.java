package uk.ac.sanger.mig.aker.orders.services;

import uk.ac.sanger.mig.aker.orders.domain.Sample;
import uk.ac.sanger.mig.aker.orders.domain.Status;
import uk.ac.sanger.mig.aker.orders.domain.StatusHistory;

/**
 * @author pi1
 * @since March 2015
 */
public interface StatusService {

	/**
	 * Update status of a sample
	 *
	 * @param sample sample to update
	 * @param status status to update to
	 * @return status history record
	 */
	StatusHistory assign(Sample sample, Status status);

	/**
	 * Update status of a sample with a message
	 *
	 * @param sample sample to update
	 * @param status status to update to
	 * @param message update message
	 * @return status history record
	 */
	StatusHistory assign(Sample sample, Status status, String message);

	/**
	 * @return get the initial status for a sample
	 */
	Status initial();
}
