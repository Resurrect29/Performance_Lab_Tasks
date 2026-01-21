import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

public class Task3 {

    public void main(String[] args) throws IOException {

        if(args.length < 3){
            System.err.println("Укажите пути к файлам как аргументы");
            return;
        }

        String valuesFilePath = args[0];
        String testFilePath = args[1];
        String reportFilePath = args[2];

        File valuesFile = new File(valuesFilePath);
        File testFile = new File(testFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        ValuesJson valuesJson = objectMapper.readValue(valuesFile, ValuesJson.class);
        TestJson testJson = objectMapper.readValue(testFile, TestJson.class);

        Map<Integer, String> valuesMap = valuesJson.getValuesJsonList().stream()
                .collect(Collectors.toMap(NodeValuesJson::getId, NodeValuesJson::getValue));

        testJson.getTestJsonList().forEach(item -> {
            if(StringUtils.isBlank(item.getValue())){
                updateItem(item, valuesMap);
            }
        });

        objectMapper.writeValue(new File(reportFilePath), testJson);

    }

    private void updateItem(NodeTestJson nodeTestJson, Map<Integer, String> valuesMap){
        nodeTestJson.setValue(valuesMap.get(nodeTestJson.getId()));

        if(nodeTestJson.hasChildren()){
            nodeTestJson.getChildren().forEach(item -> updateItem(item,valuesMap));
        }
    }
}
