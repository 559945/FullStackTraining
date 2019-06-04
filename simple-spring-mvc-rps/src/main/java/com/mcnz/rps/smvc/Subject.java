package com.mcnz.rps.smvc;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subject_id")
	long subjectId;
	
	@Column(name="subtitle")
	String subtitle;
	
	@Column(name="duration_in_hours")
	int durationInHours;
	
	@OneToMany(mappedBy = "subjects", cascade = CascadeType.ALL)
	Set<Book> references;

	public Subject() {

	}

	public Subject(String subtitle, int durationInHours) {

		this.subtitle = subtitle;
		this.durationInHours = durationInHours;

	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subtitle=" + subtitle
				+ ", durationInHours=" + durationInHours + ", references="
				+ references + "]";
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public int getDurationInHours() {
		return durationInHours;
	}

	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}

	public Set<Book> getReferences() {
		return references;
	}

	public void setReferences(Set<Book> references) {
		this.references = references;
	}
}
