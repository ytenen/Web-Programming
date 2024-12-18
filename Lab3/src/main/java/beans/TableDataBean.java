package beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Data;
import model.Result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@Named("tableDataBean")
@ApplicationScoped
public class TableDataBean implements Serializable {
    private List<Result> results;

    public TableDataBean(){
        results = new ArrayList<>();
    }

}
