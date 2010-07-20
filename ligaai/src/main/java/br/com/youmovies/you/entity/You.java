package br.com.youmovies.you.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class You {

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 50, unique = true, nullable = false)
	private String mail;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 32)
	private String password;

	@Column(length = 50)
	private String nickname;

	@Temporal(TemporalType.DATE)
	private Calendar birth;

	@Embedded
	private Location location;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(final String nickname) {
		this.nickname = nickname;
	}

	public Calendar getBirth() {
		return birth;
	}

	public void setBirth(final Calendar birth) {
		this.birth = birth;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(final Location location) {
		this.location = location;
	}

}
