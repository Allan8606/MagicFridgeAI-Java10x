package allan.dev.MagicFridgeAI.controller;


import allan.dev.MagicFridgeAI.model.FoodItem;
import allan.dev.MagicFridgeAI.service.FoodItemService;
import allan.dev.MagicFridgeAI.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private GeminiService geminiService;

    @GetMapping("/gerar")
    public Mono<ResponseEntity<String>> gerarReceita(){
        List<FoodItem> foodItems = foodItemService.listar();


        return geminiService.gerarReceita(foodItems)
                .map(receita -> ResponseEntity.ok(receita))
                .defaultIfEmpty(ResponseEntity.noContent().build());

    }

}
