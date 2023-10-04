package com.pg3402.csgobank;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorHandling implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        Response.Body body = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()){
            System.out.println("KAKE");
            throw new RuntimeException(requestUrl);
        }
        return null;
    }
}
