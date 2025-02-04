package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class PointResponse {
    private double x;
    private double y;
    private double r;
    private boolean hit;
}
