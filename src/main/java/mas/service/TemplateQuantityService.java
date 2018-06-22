package mas.service;

import mas.mapper.TemplateQuantityMapper;
import mas.model.business.TemplateQuantity;
import mas.model.dto.TemplateQuantityDTO;

import java.util.List;

public class TemplateQuantityService {
    public static List<TemplateQuantityDTO> loadTemplateQuantities() {
        List<TemplateQuantityDTO> templateQuantityDTOS = TemplateQuantityMapper.selectAllTemplateQuantities();
        for(TemplateQuantityDTO TemplateQuantityDTO : templateQuantityDTOS) {
            new TemplateQuantity(TemplateQuantityDTO);
        }
        return templateQuantityDTOS;
    }
}
