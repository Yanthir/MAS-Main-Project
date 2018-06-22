package mas.service;

import mas.mapper.BeverageMapper;
import mas.model.business.Beverage;
import mas.model.dto.BeverageDTO;

import java.util.List;

public class BeverageService {
    public static List<BeverageDTO> loadBeverages() {
        List<BeverageDTO> beverageDTOS = BeverageMapper.selectAllBeverages();
        for(BeverageDTO BeverageDTO : beverageDTOS) {
            new Beverage(BeverageDTO);
        }
        return beverageDTOS;
    }
}
