package converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.MyUncheckedException2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public abstract class JsonConverter2<T> {

    private final String jsonFilename;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public JsonConverter2(String jsonFilename) {

        this.jsonFilename = jsonFilename;
    }

    // conversion from object to json
    public void toJson(final T element){
        try (FileWriter fileWriter = new FileWriter(jsonFilename)) {
            if (element == null) {
                throw new MyUncheckedException2("ELEMENT IS NULL");
            }
            gson.toJson(element, fileWriter);
        } catch (MyUncheckedException2 e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {  // re trow
            throw new MyUncheckedException2("TO JSON - JSON FILENAME EXCEPTION");
        }
    }

    // conversion from json to object
    public Optional<T> fromJson(){
        try (FileReader fileReader = new FileReader(jsonFilename)) {
            return Optional.of(gson.fromJson(fileReader, type));
        } catch (IOException e) {
            throw new MyUncheckedException2("FROM JSON - JSON FILENAME EXCEPTION");
        }
//        return Optional.empty();
    }

}
