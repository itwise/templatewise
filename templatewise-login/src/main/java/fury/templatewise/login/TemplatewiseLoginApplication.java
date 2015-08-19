package fury.templatewise.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:/config/properties/${spring.profiles.active}/application-${spring.profiles.active}.properties"})
public class TemplatewiseLoginApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TemplatewiseLoginApplication.class);
	}
	
	@Profile("local")
	public static void main(String[] args) {
		SpringApplication.run(TemplatewiseLoginApplication.class, args);
	}
	
}
