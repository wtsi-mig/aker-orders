package uk.ac.sanger.mig.aker.orders.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pi1
 * @since March 2015
 */
@Entity
@Table(name = "orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order extends BaseEntity {

	@OneToMany(mappedBy = "order")
	@JsonManagedReference
	private Collection<Sample> samples = new ArrayList<>();

	@OneToMany(mappedBy = "order")
	@JsonManagedReference
	private Collection<Option> options = new ArrayList<>();

	@OneToOne(optional = false)
	private Product product;

	@OneToOne(optional = false)
	private Project project;

	@Column(nullable = false, updatable = false)
	private String owner;

	@Transient
	private int estimateCost;

	@Transient
	private boolean processed = false;

	public Collection<Sample> getSamples() {
		return samples;
	}

	public void setSamples(Collection<Sample> samples) {
		this.samples = samples;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Collection<Option> getOptions() {
		return options;
	}

	public void setOptions(Collection<Option> options) {
		this.options = options;
	}

	public int getEstimateCost() {
		return estimateCost;
	}

	public void setEstimateCost(int estimateCost) {
		this.estimateCost = estimateCost;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@JsonProperty
	public Long getIdentifier() {
		return id;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("samples", samples)
				.append("options", options)
				.append("product", product)
				.append("project", project)
				.append("estimateCost", estimateCost)
				.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Order rhs = (Order) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.samples, rhs.samples)
				.append(this.options, rhs.options)
				.append(this.product, rhs.product)
				.append(this.project, rhs.project)
				.append(this.estimateCost, rhs.estimateCost)
				.append(this.processed, rhs.processed)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(samples)
				.append(options)
				.append(product)
				.append(project)
				.append(estimateCost)
				.append(processed)
				.toHashCode();
	}
}
