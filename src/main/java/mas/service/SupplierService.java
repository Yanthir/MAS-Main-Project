package mas.service;

import mas.mapper.SupplierMapper;
import mas.model.business.Supplier;
import mas.model.dto.SupplierDTO;

import java.util.List;

public class SupplierService {
    public static List<SupplierDTO> loadSuppliers() {
        List<SupplierDTO> supplierDTOS = SupplierMapper.selectAllSuppliers();
        for(SupplierDTO SupplierDTO : supplierDTOS) {
            new Supplier(SupplierDTO);
        }
        return supplierDTOS;
    }
}
