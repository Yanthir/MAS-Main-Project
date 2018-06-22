package mas.service;

import mas.mapper.SubscriptionMapper;
import mas.model.business.Subscription;
import mas.model.dto.SubscriptionDTO;

import java.util.List;

public class SubscriptionService {
    public static List<SubscriptionDTO> loadSubscriptions() {
        List<SubscriptionDTO> subscriptionDTOS = SubscriptionMapper.selectAllSubscriptions();
        for(SubscriptionDTO SubscriptionDTO : subscriptionDTOS) {
            new Subscription(SubscriptionDTO);
        }
        return subscriptionDTOS;
    }
}
