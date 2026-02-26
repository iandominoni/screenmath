package br.com.alura.screenmatch.principal;


import br.com.alura.screenmatch.api.ApiCallOmdb;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        Scanner input = new Scanner((System.in));
        System.out.println("Digite um filme para buscar: ");
        var busca = input.nextLine();

        ApiCallOmdb api = new ApiCallOmdb();

        Map<String, String> params = new HashMap<>();

        params.put("t", busca);
        String response = api.get(params);

        TituloOmdb obraTituloOmdb = gson.fromJson(response, TituloOmdb.class);
        try {
            Titulo obraTitulo = new Titulo(obraTituloOmdb);
            System.out.println(obraTitulo);
            System.out.println(response);
        } catch (NumberFormatException e) {
            System.out.println("Erro: ");
            System.out.println(e.getMessage());
        }

    }
}
