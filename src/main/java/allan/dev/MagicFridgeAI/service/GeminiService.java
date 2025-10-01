package allan.dev.MagicFridgeAI.service;

import allan.dev.MagicFridgeAI.model.FoodItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeminiService {

    private final WebClient webClient;

    // A chave da API é injetada diretamente da variável de ambiente "API_KEY" usando @Value.
    // Isso garante que o Spring a carregue corretamente no contexto.
    @Value("${API_KEY}")
    private String apiKey;

    // Construtor que recebe o WebClient
    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Método que vai gerar a receita
    public Mono<String> gerarReceita(List<FoodItem> foodItems) {


        String alimentos = foodItems.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s",
                        item.getNome(),
                        item.getCategoria(),
                        item.getQuantidade(),
                        item.getValidade()))
                .collect(Collectors.joining("\n"));

        // Constrói o prompt com os itens disponíveis
        String prompt = "Baseado no meu banco de dados faça uma receita com os seguintes itens: " + alimentos;

        // Usando um modelo rápido e eficiente
        String model = "gemini-2.5-flash";

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
                // A URL base já está no WebClient. Adicionamos apenas o path e o queryParam "key".
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/" + model + ":generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                // Adicionando tratamento de erro para status 4xx e 5xx da API
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class).map(body -> new RuntimeException("Erro na API Gemini (" + response.statusCode() + "): " + body)))
                .bodyToMono(String.class)
                .map(response -> {
                    try {
                        // Extrai o texto da resposta JSON
                        return new ObjectMapper()
                                .readTree(response)
                                .at("/candidates/0/content/parts/0/text")
                                .asText();
                    } catch (Exception e) {
                        throw new RuntimeException("Erro ao processar a resposta JSON da API", e);
                    }
                });
    }
}
