package uk.ac.sanger.mig.aker.orders.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author pi1
 * @since March 2015
 */
@Entity
@Table(name = "sample_status")
public class Status extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
