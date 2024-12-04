package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;


import java.io.Serializable;

@Data
@Named("x")
@SessionScoped
public class XCoordBean implements Serializable {
    private float x;

}