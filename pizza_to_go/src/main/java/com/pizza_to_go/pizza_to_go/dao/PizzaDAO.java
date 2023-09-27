package com.pizza_to_go.pizza_to_go.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.pizza_to_go.pizza_to_go.model.Pizza;

@Singleton
public class PizzaDAO {

	@PersistenceContext(name = "jpa-unit")
    EntityManager em;
	
	public void createPizza(int orderId, String size, Float price, List<String> topping) {
		Pizza pizza = new Pizza();
		List<String> topping2 = new ArrayList<>();
		topping2.addAll(topping);

		try {
			pizza.setOrderId(orderId);
			pizza.setSize(size);
			pizza.setPrice(price);
			if (!topping.isEmpty()) {
				pizza.setTopping1(topping.get(0));
				topping2.remove(0);
			}
			if (!topping.isEmpty()) {
				pizza.setTopping2(topping.get(0));
				topping2.remove(0);
			}
			if (!topping.isEmpty()) {
				pizza.setTopping3(topping.get(0));
				topping2.remove(0);
			}
			if (!topping.isEmpty()) {
				pizza.setTopping4(topping.get(0));
				topping2.remove(0);
			}
			if (!topping.isEmpty()) {
				pizza.setTopping5(topping.get(0));
				topping2.remove(0);
			}

			em.persist(pizza);
			em.flush();
			em.refresh(pizza);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Pizza> getPizzas(int orderId) {
		Query query = em.createNamedQuery("Pizza.findByOrderId", Pizza.class);
		query.setParameter("orderId", orderId);
		List<Pizza> pizzas = query.getResultList();
		
		return pizzas;
	}
	
}
