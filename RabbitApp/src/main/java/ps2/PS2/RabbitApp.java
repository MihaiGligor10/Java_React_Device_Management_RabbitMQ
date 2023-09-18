package ps2.PS2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import ps2.PS2.service.ServiceRabbit;


@EntityScan
@SpringBootApplication
public class RabbitApp {


	public static void main(String[] args) throws Exception {

		SpringApplication.run(RabbitApp.class, args);
		ServiceRabbit serviceRabbit=new ServiceRabbit("sensor.csv");
		serviceRabbit.read();
	}
}
