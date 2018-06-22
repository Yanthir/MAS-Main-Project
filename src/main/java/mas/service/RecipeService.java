package mas.service;

import mas.mapper.RecipeMapper;
import mas.model.business.Recipe;
import mas.model.dto.RecipeDTO;

import java.util.List;

public class RecipeService {
    public static List<RecipeDTO> loadRecipes() {
        List<RecipeDTO> recipeDTOS = RecipeMapper.selectAllRecipes();
        for(RecipeDTO RecipeDTO : recipeDTOS) {
            new Recipe(RecipeDTO);
        }
        return recipeDTOS;
    }
}
