package mas.service;

import mas.mapper.BatchMapper;
import mas.model.business.Batch;
import mas.model.dto.BatchDTO;

import java.util.List;

public class BatchService {
    public static List<BatchDTO> loadBatches() {
        List<BatchDTO> batchDTOS = BatchMapper.selectAllBatches();
        for(BatchDTO BatchDTO : batchDTOS) {
            new Batch(BatchDTO);
        }
        return batchDTOS;
    }
}
