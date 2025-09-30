package allan.dev.MagicFridgeAI.service;

import allan.dev.MagicFridgeAI.model.FoodItem;
import allan.dev.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    public FoodItem salvar(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> listar(){
        return foodItemRepository.findAll();
    }

    public FoodItem listarPorId(Long id){
        Optional<FoodItem> item = foodItemRepository.findById(id);

        return item.orElse(null);
    }

    public FoodItem alterar(FoodItem foodItem, Long id){
        Optional<FoodItem> item = foodItemRepository.findById(id);

        if (item.isPresent()){
            FoodItem atualizado = foodItem;
            atualizado.setId(id);
            atualizado = foodItemRepository.save(atualizado);
            return atualizado;
        }
        return null;
    }

    public void deletar(Long id){
        Optional<FoodItem> item = foodItemRepository.findById(id);

        if(item.isPresent()){
            foodItemRepository.deleteById(id);
        }

    }




}
