package com.pg3402.csgobank;

import com.pg3402.csgobank.vault.AmundFact;
import com.pg3402.csgobank.vault.VaultService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CsGoBankApplication {

	public static void main(String[] args) {

		SpringApplication.run(CsGoBankApplication.class, args);


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
