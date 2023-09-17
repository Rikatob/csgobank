package com.pg3402.csgobank.vault;


import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class VaultService {


    WebClient client = WebClient.create();


    WebClient.ResponseSpec responseSpec  = client.get()
            .uri("localhost:8080/transaction")
            .retrieve();


/*

    public boolean validateTransaction() throws IOException {
        URL url = new URL("localhost:8080/transaction");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return Boolean.parseBoolean(con.getResponseMessage());
    }
*/
}
