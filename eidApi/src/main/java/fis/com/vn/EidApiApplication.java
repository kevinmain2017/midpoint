package fis.com.vn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fis.com.vn.repository.MTypeRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {MTypeRepository.class})
public class EidApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EidApiApplication.class, args);
	}

}
