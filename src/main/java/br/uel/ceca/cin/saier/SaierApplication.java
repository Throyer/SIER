/**
 * SAIER 2.0.0.
 */
package br.uel.ceca.cin.saier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication
public class SaierApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaierApplication.class, args);
	}
}
