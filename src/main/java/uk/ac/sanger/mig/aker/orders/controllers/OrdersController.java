package uk.ac.sanger.mig.aker.orders.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

}
