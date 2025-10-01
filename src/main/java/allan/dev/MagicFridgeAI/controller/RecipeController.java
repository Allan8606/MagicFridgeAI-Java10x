package allan.dev.MagicFridgeAI.controller;


import allan.dev.MagicFridgeAI.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {

    @Autowired
    private GeminiService geminiService;

    @GetMapping
    public Mono<ResponseEntity<String>> gerarReceita(){
        return geminiService.gerarReceita();

    }

}
