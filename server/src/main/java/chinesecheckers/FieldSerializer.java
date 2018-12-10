package chinesecheckers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;

public class FieldSerializer extends JsonSerializer<Field> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(Field value,
                          JsonGenerator gen,
                          SerializerProvider serializers)
            throws IOException, JsonProcessingException {

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        gen.writeFieldName(writer.toString());
    }
}
