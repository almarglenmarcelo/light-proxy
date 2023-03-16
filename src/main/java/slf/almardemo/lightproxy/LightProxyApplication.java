package slf.almardemo.lightproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"slf.almardemo"})
@SpringBootApplication
public class LightProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightProxyApplication.class, args);
	}

}

