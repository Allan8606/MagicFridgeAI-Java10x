package allan.dev.MagicFridgeAI.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private String apiUrl = "https://generativelanguage.googleapis.com/";

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(apiUrl).build();
    }


}
