import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NodeTestJson {
    private Integer id;
    private String title;
    private String value;

    @JsonProperty("values")
    private List<NodeTestJson> children;

    public boolean hasChildren(){
        return children != null && !children.isEmpty();
    }
}
