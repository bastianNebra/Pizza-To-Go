package com.pizza_to_go.pizza_to_go.api;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pizza_to_go.pizza_to_go.api.dto.OrderDTOIn;
import com.pizza_to_go.pizza_to_go.api.dto.OrderDTOOut;
import com.pizza_to_go.pizza_to_go.api.dto.PizzaDto;
import com.pizza_to_go.pizza_to_go.dao.OrderDAO;
import com.pizza_to_go.pizza_to_go.dao.PizzaDAO;

@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {
    WebTarget pizzaDeliveryTarget = ClientBuilder.newClient().target("http://localhost:9080/api/ mn");

    @Inject
    OrderDAO orderDAO;

    @Inject
    PizzaDAO pizzaDAO;

    @POST
    @Transactional
    @Path("/{orderId}")
    public void addPizza(@PathParam("orderId") int orderId, PizzaDto pizza) {
        pizzaDAO.createPizza(orderId, pizza.getSize(), pizza.getPrice(), pizza.getTopping());
        orderDAO.updatePriceOrder(orderId, pizza.getPrice());
    }

/*	@GET
	@Path("/{orderId}")
	public OrderDTOOut getOrder(@PathParam("orderId") int orderId) {
		Optional<Orders> order = orderDAO.findOrder(orderId);
		if (order.isPresent()) {
			OrderDTOOut orderDTO = new OrderDTOOut(order.get());
			List<Pizza> pizzas = pizzaDAO.getPizzas(orderId);
			List<PizzaDto> pizzaDTOs = new ArrayList<>();
			for (Pizza pizza : pizzas) {
				PizzaDto pizzaIn = new PizzaDto(pizza);
				pizzaDTOs.add(pizzaIn);
			}
			orderDTO.setPizza(pizzaDTOs);
			return orderDTO;
		} else {
			throw new RuntimeException("ERROR: Order not found");
		}
	}*/

    @POST
    @Transactional
    public float sendOrder(OrderDTOIn order) {
        int orderId = orderDAO.createOrder(order.getUsername());
        System.out.println(orderId);
        List<PizzaDto> pizzas = order.getPizza();
        float price = 0.0f;
        for (PizzaDto pizza : pizzas) {
            pizzaDAO.createPizza(orderId, pizza.getSize(), pizza.getPrice(), pizza.getTopping());
            price += pizza.getPrice();
        }
        try (@SuppressWarnings("unused") Response response = pizzaDeliveryTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(new OrderDTOOut(order, orderId), MediaType.APPLICATION_JSON))) {
            return price;
        }
    }

}
