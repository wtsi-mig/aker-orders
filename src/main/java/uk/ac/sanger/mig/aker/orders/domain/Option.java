package uk.ac.sanger.mig.aker.orders.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pi1
 * @since March 2015
 */
@Entity
@Table(name = "options")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Option extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String value;

	@ManyToOne(optional = false)
	@JsonBackReference
	private Order order;

	@Transient
	private Collection<String> restrictedOptions = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Collection<String> getRestrictedOptions() {
		return restrictedOptions;
	}

	public void setRestrictedOptions(Collection<String> restrictedOptions) {
		this.restrictedOptions = restrictedOptions;
	}
}
