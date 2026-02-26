package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.api.ApiCallGithub;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PrincipalGithub {
     public static void main(String[] args) throws IOException, InterruptedException {
         Gson gson = new GsonBuilder()
                 .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                 .create();

         Scanner input = new Scanner((System.in));
         ApiCallGithub api = new ApiCallGithub();
         System.out.println("1 - Buscar usuário");
         System.out.println("2 - Buscar repositório");

         Integer opcao = Integer.parseInt(input.nextLine());


         switch(opcao){
             case 1:
                 System.out.println("Digite o user: ");
                 String user = input.nextLine();
                 try {
                     System.out.println(api.buscarUsuario(user));
                 } catch (Exception e) {
                     System.out.println("Deu erro: " + e.getMessage());
                 }
                 break;
             case 2:
                 System.out.println("Digite o user:");
                 String userrepo = input.nextLine();
                 System.out.println("Digite o repo: ");
                 String repo = input.nextLine();
                 try {
                         System.out.println(api.buscarRepo(userrepo,repo));
             } catch (Exception e) {
                 System.out.println("Deu erro: " + e.getMessage());
             }
                 break;
             default:
                 System.out.println("Opção inválida.");
                 return;
         }


    }
}
