package uk.ac.sanger.mig.aker.orders.services;

import javax.validation.constraints.NotNull;

import uk.ac.sanger.mig.aker.orders.domain.Order;

/**
 * @author pi1
 * @since March 2015
 */
public interface OrderService {

	/**
	 * Processes an order:
	 * <ul>
	 * <li>Saves project</li>
	 * <li>Saves product</li>
	 * <li>Saves samples and sets their status to the initial status</li>
	 * <li>Saves options</li>
	 * <li>Saves the order itself</li>
	 * </ul>
	 *
	 * @param order order to process
	 * @return processed order
	 */
	Order process(@NotNull Order order);
}
