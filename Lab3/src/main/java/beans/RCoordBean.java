package beans;




import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;


import java.io.Serializable;


@Data
@Named("r")
@SessionScoped
public class RCoordBean implements Serializable {
    private float r;
}