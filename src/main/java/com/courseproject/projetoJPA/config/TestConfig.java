package com.courseproject.projetoJPA.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.courseproject.projetoJPA.entities.Category;
import com.courseproject.projetoJPA.entities.Order;
import com.courseproject.projetoJPA.entities.Product;
import com.courseproject.projetoJPA.entities.User;
import com.courseproject.projetoJPA.entities.enums.OrderStatus;
import com.courseproject.projetoJPA.repositories.CategoryRepository;
import com.courseproject.projetoJPA.repositories.OrderRepository;
import com.courseproject.projetoJPA.repositories.ProductRepository;
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
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {

		// Usado null no id pois o sistema gera o id automaticamente
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		// Id - Nome - Email - Número de celular - Senha
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		// Z = Horário UTC de Greenwich (3 horas na frente de Brasília)
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.CANCELED, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.DELIVERED, u1);
		// Id - Instante que o pedido foi realizado - Status da compra - atributo do cliente
		
		// Id - Nome - Descrição - Preço - Url da imagem
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		// salvar no banco de dados
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
	}
	
	
	
}
