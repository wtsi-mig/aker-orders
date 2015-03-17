package uk.ac.sanger.mig.aker.orders.services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import uk.ac.sanger.mig.aker.orders.domain.Sample;
import uk.ac.sanger.mig.aker.orders.domain.Status;

/**
 * @author pi1
 * @since March 2015
 */
@Service
public class SampleServiceImpl implements SampleService {

	@Resource
	private StatusService statusService;

	private Status initial;

	@PostConstruct
	private void init() {
		initial = statusService.initial();
	}

	@Override
	public void process(Sample sample) {
		statusService.assign(sample, initial);
	}

	@Override
	public void process(Collection<Sample> samples) {
		samples.forEach(this::process);
	}
}
