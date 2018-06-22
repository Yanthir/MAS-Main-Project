package mas.service;

import mas.mapper.IngredientMapper;
import mas.model.business.Ingredient;
import mas.model.dto.IngredientDTO;

import java.util.List;

public class IngredientService {
    public static List<IngredientDTO> loadIngredients() {
        List<IngredientDTO> ingredientDTOS = IngredientMapper.selectAllIngredients();
        for(IngredientDTO IngredientDTO : ingredientDTOS) {
            new Ingredient(IngredientDTO);
        }
        return ingredientDTOS;
    }
}
