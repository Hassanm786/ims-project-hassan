package com.qa.ims.persistence.domain;

import java.util.Objects;


public class Items {
	
	private Long id;
	private String name;
	private Double value;
	
	public Items(Long id, String name, Double value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	
	public Items(String name, Double value) {
		super();
		this.name = name;
		this.value = value;
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(value, other.value);
	}
	
	
	
}
