package fr.phylisiumstudio.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.phylisiumstudio.logic.ICraftPlayerService;
import fr.phylisiumstudio.logic.models.CraftPlayer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * A service to manage the players with JSON files.
 */
public class JsonCraftPlayerService implements ICraftPlayerService {

    private String path;
    private Gson gson;

    /**
     * The constructor of the class.
     * @param path The path of the directory to store the players.
     */
    public JsonCraftPlayerService(String path) {
        this.path = path;
        File directory = new File(path);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void create(CraftPlayer craftPlayer) {
        File file = getFile(craftPlayer.getUuid());
        String json = this.gson.toJson(craftPlayer);

        try(FileWriter writer = new FileWriter(file)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CraftPlayer read(UUID id) {
        File file = getFile(id);
        CraftPlayer craftPlayer = null;

        if (!file.exists()) {
            return null;
        }

        try(FileReader reader = new FileReader(file)) {
            craftPlayer = this.gson.fromJson(reader, CraftPlayer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return craftPlayer;
    }

    @Override
    public void update(CraftPlayer craftPlayer) {
        File file = getFile(craftPlayer.getUuid());
        String json = this.gson.toJson(craftPlayer);

        try(FileWriter writer = new FileWriter(file)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UUID id) {
        File file = getFile(id);
        file.delete();
    }

    @Override
    public List<CraftPlayer> list() {
        File directory = new File(this.path);
        List<File> files = List.of(directory.listFiles());

        return files.stream().map(file -> {
            try(FileReader reader = new FileReader(file)) {
                return this.gson.fromJson(reader, CraftPlayer.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).toList();
    }

    private File getFile(UUID id) {
        return new File(this.path + File.separator + id.toString() + ".json");
    }
}
