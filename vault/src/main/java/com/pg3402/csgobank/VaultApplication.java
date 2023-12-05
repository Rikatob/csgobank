package com.pg3402.csgobank;

import com.pg3402.csgobank.vault.AmundFact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
public class VaultApplication {

	public static void main(String[] args) {

		SpringApplication.run(VaultApplication.class, args);


		// TEST for amundFact object with fields.
		WebClient.Builder builder = WebClient.builder();
		String factUrl = "https://catfact.ninja/fact?max_length=140";
		AmundFact amundFact = builder.build()
				.get()
				.uri(factUrl)
				.retrieve()
				.bodyToMono(AmundFact.class)
				.block();

		System.out.println("\n\n----------------------");
		System.out.println(amundFact);
		System.out.println("----------------------\n\n");

	}





}
