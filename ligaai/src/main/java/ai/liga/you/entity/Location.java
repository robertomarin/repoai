package ai.liga.you.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Location {

	@Column(length = 15)
	private String zipCode;

	@Column(length = 30)
	private String city;

	@Column(length = 30)
	private String state;

	@Column(length = 30)
	private String country;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

}
