package mas.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mas.model.HelloWorldModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldResponse {
    private HelloWorldModel model;
}
