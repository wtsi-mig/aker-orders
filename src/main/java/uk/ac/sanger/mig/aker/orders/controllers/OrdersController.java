package uk.ac.sanger.mig.aker.orders.controllers;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Order create(@ModelAttribute Order orderRequest) {
		return orderService.process(orderRequest);
	}

	@RequestMapping(value = "/by-owner/{owner}", method = RequestMethod.GET)
	@ResponseBody
	public Resources<Resource<Order>> orders(@PathVariable String owner) {
		final Resources<Resource<Order>> orders = Resources.wrap(orderService.findAllByOwner(owner));

		for (Resource<Order> order : orders) {
			final Link show = linkTo(methodOn(OrdersController.class).show(order.getContent().getId(), owner)).withSelfRel();
			order.add(show);
		}

		return orders;
	}

	@RequestMapping(value = "/show/{id}/{owner}")
	@ResponseBody
	public Resource<Order> show(@PathVariable("id") Long id, @PathVariable("owner") String owner) {
		final Order order = orderService.findByOwnerAndId(owner, id).orElse(null);
		return new Resource<>(order);
	}

	@RequestMapping(value = "/by-owner/{owner}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Order orders(@PathVariable String owner, @PathVariable Long id) {
		final Optional<Order> order = orderService.findByOwnerAndId(owner, id);
		return order.get();
	}
}
