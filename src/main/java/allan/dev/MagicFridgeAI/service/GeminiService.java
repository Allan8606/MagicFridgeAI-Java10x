package allan.dev.MagicFridgeAI.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;

    // A chave da API já está definida como uma variável de instância, como na sua classe
    private final String apiKey = System.getenv("API_KEY");

    // Construtor que recebe o WebClient
    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Método que vai gerar a receita
    public Mono<String> gerarReceita(String ingredientes){
        String prompt = "Crie uma receita simples e rapida usando os seguintes ingredientes: " + ingredientes;

        // Corpo da requisição no formato JSON esperado pela API da Gemini
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        // Retorna o Mono<String> da chamada à API
        return webClient.post()
                // Usa a variável de instância apiKey na URI
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/gemini-pro:generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .header("Content-Type", "application/json")
                .bodyValue(requestBody) // O WebClient do Spring WebFlux cuida da serialização do Map para JSON
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    try {
                        // Exemplo de como extrair o texto da resposta JSON
                        return new ObjectMapper()
                                .readTree(response)
                                .at("/candidates/0/content/parts/0/text")
                                .asText();
                    } catch (Exception e) {
                        throw new RuntimeException("Erro ao processar a resposta da API", e);
                    }
                });
    }
}