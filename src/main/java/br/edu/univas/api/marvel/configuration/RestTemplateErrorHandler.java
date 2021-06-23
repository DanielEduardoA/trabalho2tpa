package br.edu.univas.api.marvel.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import br.edu.univas.api.marvel.exception.MarvelApiException;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError()) {
        	String body = toString(response.getBody());
            throw new MarvelApiException(body, response.getStatusCode());
        } 
    }
    
    private String toString(InputStream inputStream) {
        try (Scanner s = new Scanner(inputStream).useDelimiter("\\A")) {
			return s.hasNext() ? s.next() : "";
		}
    }

}
