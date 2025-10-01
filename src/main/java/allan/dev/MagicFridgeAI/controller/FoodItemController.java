package allan.dev.MagicFridgeAI.controller;

import allan.dev.MagicFridgeAI.model.FoodItem;
import allan.dev.MagicFridgeAI.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    //Post

    @PostMapping("/criar")
    public ResponseEntity<FoodItem> criar(@RequestBody FoodItem foodItem) {
        FoodItem salvo = foodItemService.salvar(foodItem);
        return ResponseEntity.ok(salvo);
    }

    //Get
    @GetMapping("/listar")
    public ResponseEntity<List<FoodItem>> listar() {
        List<FoodItem> listarTudo = foodItemService.listar();
        return ResponseEntity.ok(listarTudo);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<String> listarPorId(@PathVariable Long id) {
        FoodItem item = foodItemService.listarPorId(id);

        if (item != null){
            return ResponseEntity.ok("Item encontrado: " + item.getNome());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");

    }


    //Put
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterar(@RequestBody FoodItem foodItem, @PathVariable Long id){
        FoodItem atualizado = foodItemService.alterar(foodItem, id);

        if (atualizado != null){
            return ResponseEntity.ok("Item atualizado: " + atualizado.getNome());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
        }
    }


    //Delete
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        FoodItem item = foodItemService.listarPorId(id);

        if (item != null){
            foodItemService.deletar(id);
            return ResponseEntity.ok("Item deletado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
        }
    }
}
