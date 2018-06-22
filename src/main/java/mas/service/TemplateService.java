package mas.service;

import mas.mapper.TemplateMapper;
import mas.model.business.Template;
import mas.model.dto.TemplateDTO;

import java.util.List;

public class TemplateService {
    public static List<TemplateDTO> loadTemplates() {
        List<TemplateDTO> templateDTOS = TemplateMapper.selectAllTemplates();
        for(TemplateDTO TemplateDTO : templateDTOS) {
            new Template(TemplateDTO);
        }
        return templateDTOS;
    }
}
