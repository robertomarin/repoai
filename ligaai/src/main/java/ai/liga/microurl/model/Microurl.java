package ai.liga.microurl.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.validator.GenericValidator;

import ai.liga.microurl.util.BaseConverter;

@Entity
public class Microurl {

	@Id
	@GeneratedValue
	private int id;

	@Column(length = 10240, updatable = false)
	private String url;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Calendar created;

	@Column(name = "remoteaddress", length = 100, updatable = false)
	private String remoteAddress;

	private int hits;

	public Microurl() {
	}

	public Microurl(String url, String remoteAddress) {
		this.url = url;
		this.remoteAddress = !GenericValidator.isBlankOrNull(remoteAddress) && remoteAddress.length() > 100 ? remoteAddress
				.substring(0, 100) : remoteAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMicro() {
		return BaseConverter.toBase62(getId());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public Microurl hit() {
		this.hits++;
		return this;
	}

}
