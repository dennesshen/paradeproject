package com.parade.paradeproject.util.dataSendModel;
/*
* @author  Christine Hsieh
*/

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DataSendModelSerializer extends StdSerializer<DataSendModel>{

    public DataSendModelSerializer() {
        this(null);
    }



    public DataSendModelSerializer(Class<DataSendModel> d) {
        super(d);
    }



    @Override
    public void serialize(DataSendModel value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {

        gen.writeStartObject();


        value.getMaindata().entrySet().stream().forEach(e -> {
            try {
                gen.writeObjectField(e.getKey(), e.getValue());
            } catch (IOException e1) {
            }
        });


        if (value.getDetaildata() != null) {

            value.getDetaildata().entrySet().stream().forEach(e -> {
                try {
                    gen.writeObjectField(e.getKey(), e.getValue());
                } catch (IOException e1) {
                }


            });
        }

        gen.writeEndObject();

    }


}
