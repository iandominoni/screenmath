package br.com.alura.screenmatch.api;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ApiCallGithub {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String BASE_URL = "https://api.github.com";
    private static final String TOKEN = dotenv.get("TOKEN");

    private final HttpClient client;

    public ApiCallGithub(){
        this.client=HttpClient.newHttpClient();
    }

    public String buscarUsuario(String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + username))
                .header("Authorization", "Bearer" + TOKEN)
                .header("Accept", "application/vnd.github+json")
                .GET()
                .build();

        return sendRequest(request);
    }
    public String buscarRepo(String username, String repo) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/repos/" + username + "/"))
                .header("Authorization", "Bearer" + TOKEN)
                .header("Accept", "application/vnd.github+json")
                .GET()
                .build();
        return sendRequest(request);
    }
    public String sendRequest(HttpRequest request) throws  IOException, InterruptedException{
        HttpResponse<String> response = client.send( request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200){
            throw new RuntimeException("Erro: " + response.statusCode());
        }
        return response.body();
    }
}
