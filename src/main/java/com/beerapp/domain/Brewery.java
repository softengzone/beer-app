package com.beerapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="brewery")
public class Brewery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="brewery_id")
	private Long id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=50)
	private String email;
	
	@Column(length=20)
	private String phone;
	
	@NotBlank
	@Column(length=35)
	private String address1;
	
	@Column(length=35)
	private String address2;
	
	@Column(length=35)
	private String address3;
	
	@Column(name="post_code", length=20)
	private String postCode;
	
	@Column(length=35)
	private String country;

	public Brewery() {
		
	}
	
	public Brewery(String name, String email, String phone, String address1, String address2, String address3, String postCode, String country) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.postCode = postCode;
		this.country = country;
	}

	public Brewery(Long id, String name, String email, String phone, String address1, String address2, String address3, String postCode, String country) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.postCode = postCode;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
		Brewery other = (Brewery) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Brewery [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address1="
				+ address1 + ", address2=" + address2 + ", address3=" + address3 + ", postCode=" + postCode
				+ ", country=" + country + "]";
	}
	
	
}
