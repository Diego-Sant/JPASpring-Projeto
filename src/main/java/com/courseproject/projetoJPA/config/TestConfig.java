package com.courseproject.projetoJPA.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.courseproject.projetoJPA.entities.Category;
import com.courseproject.projetoJPA.entities.Order;
import com.courseproject.projetoJPA.entities.User;
import com.courseproject.projetoJPA.entities.enums.OrderStatus;
import com.courseproject.projetoJPA.repositories.CategoryRepository;
import com.courseproject.projetoJPA.repositories.OrderRepository;
import com.courseproject.projetoJPA.repositories.UserRepository;

@Configuration
@Profile("test") //nome exato do spring.profiles do application.properties
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {

		// Usado null no id pois o sistema gera o id automaticamente
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		// Z = Horário UTC de Greenwich (3 horas na frente de Brasília)
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.CANCELED, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.DELIVERED, u1);
		// Pedido 1 e 3 são do cliente 1 e o Pedido 2 é do cliente 2
		
		// salvar no banco de dados
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
	}
	
	
	
}
