package mas.service;

import mas.mapper.SetMapper;
import mas.model.business.Set;
import mas.model.dto.SetDTO;

import java.util.List;

public class SetService {
    public static List<SetDTO> loadSets() {
        List<SetDTO> setDTOS = SetMapper.selectAllSets();
        for(SetDTO SetDTO : setDTOS) {
            new Set(SetDTO);
        }
        return setDTOS;
    }
}
