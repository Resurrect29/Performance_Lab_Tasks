import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TestJson {
    @JsonProperty("tests")
    private List<NodeTestJson> testJsonList;
}
