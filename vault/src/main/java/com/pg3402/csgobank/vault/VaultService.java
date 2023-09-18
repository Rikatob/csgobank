package com.pg3402.csgobank.vault;


import lombok.Data;
import org.springframework.web.reactive.function.client.WebClient;

@Data
public class VaultService {


    // MONO single or no object.
    // FLUX list or no object.
    public Boolean validateTransaction(){
        // TODO: 9/18/2023 Should we take the url in as a parameter?
        // TODO: 9/18/2023 Need to check what happens if it fails to get validation, should be "standalone".

        String transactionUrl = "http://localhost:8081/transaction";

        WebClient.Builder builder = WebClient.builder();


        Boolean validation = builder.build()
                .get()
                .uri(transactionUrl)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return validation;
    }

}
