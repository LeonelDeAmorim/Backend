package application.repository;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(ReviewRepository reviewRepository,UserRepository userRepository, ItemRepository itemRepository, TransactionRepository transactionRepository, OrderRepository orderRepository) {

		return args -> {
			userRepository.findAll().forEach(user -> log.info("Preloaded " + user));		};
	}

	@Autowired
	Environment env;

	@Bean
	public DataSource dataSource() {
	final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
	dataSource.setUrl(env.getProperty("spring.datasource.url"));
	return dataSource;
	}
}
