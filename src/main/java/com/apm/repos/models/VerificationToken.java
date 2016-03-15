package com.apm.repos.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class VerificationToken {
	private static final int EXPIRATION = 60 * 24;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long token_id;

	private String token;
	private Date expiryDate;
	private boolean verified;

	@OneToOne(targetEntity = APMUser.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(nullable = false, name = "user_id")
	private APMUser user;
	
	public VerificationToken() {
		super();
	}

	public VerificationToken(String token, APMUser user) {
		super();
		this.setToken(token);
		this.user = user;
		this.setExpiryDate(calculateExpiryDate(EXPIRATION));
		this.setVerified(false);
	}

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	public APMUser getUser() {
		return user;
	}

	public void setUser(APMUser user) {
		this.user = user;
	}

}
