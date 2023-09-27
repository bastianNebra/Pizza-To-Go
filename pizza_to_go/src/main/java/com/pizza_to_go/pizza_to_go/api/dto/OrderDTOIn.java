package com.pizza_to_go.pizza_to_go.api.dto;

import java.io.Serializable;
import java.util.List;

public class OrderDTOIn implements Serializable {

	private String username;
	private List<PizzaDto> pizza;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<PizzaDto> getPizza() {
		return pizza;
	}

	public void setPizza(List<PizzaDto> pizza) {
		this.pizza = pizza;
	}

}
