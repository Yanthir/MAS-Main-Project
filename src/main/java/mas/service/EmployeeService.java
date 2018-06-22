package mas.service;

import mas.mapper.EmployeeMapper;
import mas.model.business.Employee;
import mas.model.business.ExtendedBusinessObject;
import mas.model.dto.EmployeeDTO;

import java.util.List;

public class EmployeeService {
    public static List<EmployeeDTO> loadEmployees() {
        List<EmployeeDTO> employeeDTOS = EmployeeMapper.selectAllEmployees();
        for(EmployeeDTO EmployeeDTO : employeeDTOS) {
            new Employee(EmployeeDTO);
        }
        return employeeDTOS;
    }
}
