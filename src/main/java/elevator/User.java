package elevator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    int currentFloor;
    IntReq intReq;
    ExtReq extReq;
    String userId;

}
