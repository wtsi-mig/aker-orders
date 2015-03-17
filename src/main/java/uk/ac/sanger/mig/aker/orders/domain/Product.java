package uk.ac.sanger.mig.aker.orders.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pi1
 * @since March 2015
 */
@Entity
@Table(name = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column
	private Integer unitCost;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Integer unitCost) {
		this.unitCost = unitCost;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("name", name)
				.append("unitCost", unitCost)
				.toString();
	}
}
