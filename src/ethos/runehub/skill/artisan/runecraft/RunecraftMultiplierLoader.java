package ethos.runehub.skill.artisan.runecraft;

import org.runehub.api.io.data.DataAcessObjectBase;
import org.runehub.api.io.load.JsonSerializer;
import org.runehub.api.io.load.LazyLoader;

import java.io.File;
import java.io.IOException;

public class RunecraftMultiplierLoader extends LazyLoader<Integer,RunecraftMultiplier> {

    private static RunecraftMultiplierLoader instance = null;

    public static RunecraftMultiplierLoader getInstance() {
        if (instance == null)
            instance= new RunecraftMultiplierLoader();
        return instance;
    }

    @Override
    protected RunecraftMultiplier load(Integer key) {
        try {
            RunecraftMultiplier value = serializer.read(new File("./Data/runehub/skills/rune-multipliers.json")).getMultiplierMap().get(key);
            if (value == null) {
                throw new NullPointerException("No Value Found: " + key);
            } else {
                return value;
            }
        } catch (IOException e) {
            return null;
        }
    }

    private RunecraftMultiplierLoader() {
        super(null);
    }


    private final JsonSerializer<RunecraftMultipliers> serializer = new JsonSerializer<>(RunecraftMultipliers.class);

}
