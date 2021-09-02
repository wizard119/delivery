package com.example.delivery;

import com.example.delivery.config.kafka.KafkaProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class DeliveryApplication {

	public static ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		//SpringApplication.run(DeliveryApplication.class, args);
		applicationContext = SpringApplication.run(DeliveryApplication.class, args);
	}

}
