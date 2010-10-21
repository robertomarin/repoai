package ai.liga.ligaai.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Immutable
public class Tag {

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Calendar created;

	public Tag() {

	}

	public Tag(String id) {
		this.setId(id);
		this.created = Calendar.getInstance();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = !GenericValidator.isBlankOrNull(id) ? id.toLowerCase() : "";
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
