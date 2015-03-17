package uk.ac.sanger.mig.aker.orders.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author pi1
 * @since March 2015
 */
@Entity
@Table(name = "status_history")
public class StatusHistory extends BaseEntity {

	@ManyToOne(optional = false)
	private Sample sample;

	@ManyToOne(optional = false)
	private Status status;

	@Column
	private String note;

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("sample", sample)
				.append("status", status)
				.append("note", note)
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
		StatusHistory rhs = (StatusHistory) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.sample, rhs.sample)
				.append(this.status, rhs.status)
				.append(this.note, rhs.note)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(sample)
				.append(status)
				.append(note)
				.toHashCode();
	}
}
