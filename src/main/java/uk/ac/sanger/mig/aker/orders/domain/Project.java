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
@Table(name = "projects")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project extends BaseEntity {

	@Column(nullable = false)
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("code", code)
				.toString();
	}
}
