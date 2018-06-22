package mas.service;

import mas.mapper.OrderMapper;
import mas.model.business.Order;
import mas.model.dto.OrderDTO;

import java.util.List;

public class OrderService {
    public static List<OrderDTO> loadOrders() {
        List<OrderDTO> orderDTOS = OrderMapper.selectAllOrders();
        for(OrderDTO OrderDTO : orderDTOS) {
            new Order(OrderDTO);
        }
        return orderDTOS;
    }
}
