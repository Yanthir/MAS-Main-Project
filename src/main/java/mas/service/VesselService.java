package mas.service;

import mas.mapper.VesselMapper;
import mas.model.business.Vessel;
import mas.model.dto.VesselDTO;

import java.util.List;

public class VesselService {
    public static List<VesselDTO> loadVessels() {
        List<VesselDTO> vesselDTOS = VesselMapper.selectAllVessels();
        for(VesselDTO VesselDTO : vesselDTOS) {
            new Vessel(VesselDTO);
        }
        return vesselDTOS;
    }
}
