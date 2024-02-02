package au.com.xtramile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class XtramileJavaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(XtramileJavaTestApplication.class, args);
	}

}
