package mas.service;

import mas.mapper.IngredientVolumeMapper;
import mas.model.business.IngredientVolume;
import mas.model.dto.IngredientVolumeDTO;

import java.util.List;

public class IngredientVolumeService {
    public static List<IngredientVolumeDTO> loadIngredientVolumes() {
        List<IngredientVolumeDTO> ingredientVolumeDTOS = IngredientVolumeMapper.selectAllIngredientVolumes();
        for(IngredientVolumeDTO IngredientVolumeDTO : ingredientVolumeDTOS) {
            new IngredientVolume(IngredientVolumeDTO);
        }
        return ingredientVolumeDTOS;
    }
}
