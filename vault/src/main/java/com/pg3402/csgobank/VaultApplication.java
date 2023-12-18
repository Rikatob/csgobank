package com.pg3402.csgobank;

import com.pg3402.csgobank.vault.CatFact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
public class VaultApplication {

	public static void main(String[] args) {

		SpringApplication.run(VaultApplication.class, args);


		// TEST for catfact object with fields.
		WebClient.Builder builder = WebClient.builder();
		String factUrl = "https://catfact.ninja/fact?max_length=140";
		CatFact catfact = builder.build()
				.get()
				.uri(factUrl)
				.retrieve()
				.bodyToMono(CatFact.class)
				.block();

		System.out.println("\n\n----------------------");
		System.out.println(catfact);
		System.out.println("----------------------\n\n");

	}





}
