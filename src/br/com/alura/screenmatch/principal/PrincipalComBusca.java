package br.com.alura.screenmatch.principal;


import br.com.alura.screenmatch.api.ApiCallOmdb;
import br.com.alura.screenmatch.excecao.ErroConversaoAno;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PrincipalComBusca {

    private static Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
    private static ApiCallOmdb api = new ApiCallOmdb();
    private static List<Titulo> titulos = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        loopFilme();
    }

    public static void loopFilme() throws IOException, InterruptedException{
        Scanner input = new Scanner(System.in);
        while(true){
            String busca = lerFilme(input);
            if (busca.equalsIgnoreCase("sair")){
                FileWriter escrever = new FileWriter("filmes.json");
                escrever.write(gson.toJson(titulos));
                escrever.close();
                break;
            }
            buscaFilme(busca);
        }
    }
    public static String lerFilme(Scanner input){
        System.out.println("Digite um filme para buscar(ou 'sair' para encerrar): ");
        return input.nextLine();
    }
    public static void buscaFilme(String busca) throws IOException, InterruptedException{
        Map<String, String> params = new HashMap<>();

        params.put("t", busca);
        String response = api.get(params);
        if(response.contains("\"Response\":\"False\"")){
            System.out.println("Filme não encontrado: " + busca);
            return;


        }
        TituloOmdb obraTituloOmdb = gson.fromJson(response, TituloOmdb.class);
        try {
            Titulo obraTitulo = new Titulo(obraTituloOmdb);
            titulos.add(obraTitulo);
            System.out.println(titulos);
        } catch (ErroConversaoAno e) {
            System.out.println("Erro: ");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }


