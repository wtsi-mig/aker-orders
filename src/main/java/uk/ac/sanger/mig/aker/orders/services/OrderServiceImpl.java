package uk.ac.sanger.mig.aker.orders.services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import uk.ac.sanger.mig.aker.orders.domain.Option;
import uk.ac.sanger.mig.aker.orders.domain.Order;
import uk.ac.sanger.mig.aker.orders.domain.Product;
import uk.ac.sanger.mig.aker.orders.domain.Project;
import uk.ac.sanger.mig.aker.orders.domain.Sample;
import uk.ac.sanger.mig.aker.orders.repositories.OptionRepository;
import uk.ac.sanger.mig.aker.orders.repositories.OrderRepository;
import uk.ac.sanger.mig.aker.orders.repositories.ProductRepository;
import uk.ac.sanger.mig.aker.orders.repositories.ProjectRepository;
import uk.ac.sanger.mig.aker.orders.repositories.SampleRepository;

/**
 * @author pi1
 * @since March 2015
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderRepository repository;

	@Resource
	private ProjectRepository projectRepository;

	@Resource
	private ProductRepository productRepository;

	@Resource
	private OptionRepository optionRepository;

	@Resource
	private SampleRepository sampleRepository;

	@Resource
	private SampleService sampleService;

	@Override
	@Transactional
	public Order process(@NotNull Order orderRequest) {
		final Date now = Calendar.getInstance().getTime();

		// get project, otherwise cannot save order
		final Project project = orderRequest.getProject();
		project.setCreated(now);
		final Project savedProject = projectRepository.save(project);

		// get product, otherwise cannot save order
		final Product product = orderRequest.getProduct();
		product.setCreated(now);
		final Product savedProduct = productRepository.save(product);

		// create order "stub" to be able to assign sample's and option's order id
		final Order order = create(orderRequest, savedProject, savedProduct);

		// set current order for each sample, save and finally process (see JavaDoc)
		final Collection<Sample> samples = orderRequest.getSamples();
		samples.forEach(s -> {
			s.setOrder(order);
			s.setCreated(now);
		});
		final Iterable<Sample> savedSamples = sampleRepository.save(samples);
		sampleService.process(IteratorUtils.toList(savedSamples.iterator(), samples.size()));

		// set current order for each option and save them
		final Collection<Option> options = orderRequest.getOptions();
		options.forEach(o -> {
			o.setOrder(order);
			o.setCreated(now);
		});
		optionRepository.save(options);

		order.setProcessed(true);
		return order;
	}

	@Override
	public Collection<Order> findAllByOwner(String owner) {
		final Collection<Order> orders = repository.findByOwner(owner);

		orders.forEach(o -> o.getSamples().forEach(Sample::setCurrentStatus));

		return orders;
	}

	@Override
	public Optional<Order> findByOwnerAndId(String owner, Long id) {
		final Order order = repository.findOne(id);

		if (order == null || !order.getOwner().equals(owner)) {
			return Optional.empty();
		}

		order.getSamples().forEach(Sample::setCurrentStatus);

		return Optional.of(order);
	}

	/**
	 * Create an order in the database
	 *
	 * @param request original order request
	 * @param project project of the order, must be in database
	 * @param product product of the order, must be in database
	 * @return saved Order instance
	 */
	private Order create(Order request, Project project, Product product) {
		Assert.notNull(project.getId(), "Project must be saved prior to creating order");
		Assert.notNull(product.getId(), "Product must be saved prior to creating order");

		final Order order = new Order();
		order.setOwner(request.getOwner());
		order.setProduct(product);
		order.setProject(project);
		order.setCreated(Calendar.getInstance().getTime());
		return repository.save(order);
	}
}
