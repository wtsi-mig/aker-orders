package uk.ac.sanger.mig.aker.orders.services;

import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import uk.ac.sanger.mig.aker.orders.domain.Sample;
import uk.ac.sanger.mig.aker.orders.domain.Status;
import uk.ac.sanger.mig.aker.orders.domain.StatusHistory;
import uk.ac.sanger.mig.aker.orders.repositories.StatusHistoryRepository;
import uk.ac.sanger.mig.aker.orders.repositories.StatusRepository;

/**
 * @author pi1
 * @since March 2015
 */
@Service
@PropertySource("status.properties")
public class StatusServiceImpl implements StatusService {

	@Value("${initial}")
	private String initialStatusName;

	@Resource
	private StatusRepository repository;

	@Resource
	private StatusHistoryRepository historyRepository;

	@Override
	public StatusHistory assign(Sample sample, Status status) {
		return assign(sample, status, null);
	}

	@Override
	public StatusHistory assign(Sample sample, Status status, String message) {
		StatusHistory change = new StatusHistory();
		change.setSample(sample);
		change.setStatus(status);
		change.setNote(message);
		change.setCreated(Calendar.getInstance().getTime());

		return historyRepository.save(change);
	}

	@Override
	public Status initial() {
		return repository.findByName(initialStatusName);
	}
}
