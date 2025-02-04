package request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PointRequest {
    private double x;
    private double y;
    private double r;
    private Long userId;
}
