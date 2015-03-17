package uk.ac.sanger.mig.aker.orders.services;

import java.util.Collection;

import uk.ac.sanger.mig.aker.orders.domain.Sample;

/**
 * @author pi1
 * @since March 2015
 */
public interface SampleService {

	/**
	 * Process a single sample:
	 * <ul>
	 *     <li>Assign initial status, see {@link StatusService#assign(Sample, uk.ac.sanger.mig.aker.orders.domain.Status)}</li>
	 * </ul>
	 *
	 * @param sample sample to process
	 */
	void process(Sample sample);

	/**
	 * Process a collection of samples, see {@link SampleService#process(Sample)}
	 *
	 * @param samples collection of samples to process
	 */
	void process(Collection<Sample> samples);
}
