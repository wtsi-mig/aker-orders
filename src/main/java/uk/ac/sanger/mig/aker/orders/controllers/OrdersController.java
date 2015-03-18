package uk.ac.sanger.mig.aker.orders.controllers;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.ac.sanger.mig.aker.orders.domain.Order;
import uk.ac.sanger.mig.aker.orders.services.OrderService;

/**
 * @author pi1
 * @since March 2015
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Resource
	private OrderService orderService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	private Order create(@ModelAttribute Order orderRequest) {
		return orderService.process(orderRequest);
	}

	@RequestMapping(value = "/by-owner/{owner}", method = RequestMethod.GET)
	@ResponseBody
	private Collection<Order> orders(@PathVariable String owner) {
		return orderService.findAllByOwner(owner);
	}

	@RequestMapping(value = "/by-owner/{owner}/{id}", method = RequestMethod.GET)
	@ResponseBody
	private Order orders(@PathVariable String owner, @PathVariable Long id) {
		final Optional<Order> order = orderService.findByOwnerAndId(owner, id);
		return order.isPresent() ? order.get() : null;
	}

}
