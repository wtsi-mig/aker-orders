package uk.ac.sanger.mig.aker.orders.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pi1
 * @since March 2015
 */
@Entity
@Table(name = "samples")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sample extends BaseEntity {

	@Column
	private String barcode;

	@ElementCollection
	@MapKeyColumn(name = "name")
	@Column(name = "value")
	@CollectionTable(name = "sample_options", joinColumns = @JoinColumn(name = "sample_id"))
	private Map<String, String> options;

	@ManyToOne(optional = false)
	@JsonBackReference
	private Order order;

	@OneToMany(mappedBy = "sample")
	private Collection<StatusHistory> statusHistory = new ArrayList<>();

	@Transient
	private Status currentStatus;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Collection<StatusHistory> getStatusHistory() {
		return statusHistory;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setCurrentStatus() {
		currentStatus = statusHistory
				.stream()
				.max(Comparator.comparing(BaseEntity::getUpdate))
				.orElseThrow(IllegalStateException::new)
				.getStatus();
	}
}
