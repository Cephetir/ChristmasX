package wtf.cephetir.christmasx.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import wtf.cephetir.christmasx.ChristmasX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ChristmasConfig {
    File dir = new File(Minecraft.getMinecraft().mcDataDir, "/config");
    File file = new File(dir, "/christamsx.json");


    public int bg = 1;
    public int speedX = 2;
    public int speedY = 3;
    public boolean toggleButtons = true;

    public void save() {
        try {
            ChristmasX.getInstance().print("Saving config...");
            if(!dir.exists()) dir.mkdirs();;
            if(!file.exists()) file.createNewFile();
            ChristmasX.getInstance().print("Saving config...");
            Gson gson = new Gson();
            JsonObject json = new JsonObject();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            json.addProperty("bg", bg);
            json.addProperty("speedX", speedX);
            json.addProperty("speedY", speedY);
            json.addProperty("toggleButtons", toggleButtons);

            writer.write(gson.toJson(json));
            writer.close();
            ChristmasX.getInstance().print("Saved config!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            if(!dir.exists()) {
                dir.mkdirs();
                file.createNewFile();
                return;
            };
            if(!file.exists()) {
                file.createNewFile();
                return;
            }
            ChristmasX.getInstance().print("Loading config...");
            Gson gson = new Gson();
            Scanner scanner = new Scanner(file);

            JsonObject json = gson.fromJson(scanner.nextLine(), JsonObject.class);
            bg = json.getAsJsonPrimitive("bg").getAsInt();
            speedX = json.getAsJsonPrimitive("speedX").getAsInt();
            speedY = json.getAsJsonPrimitive("speedY").getAsInt();
            toggleButtons = json.getAsJsonPrimitive("toggleButtons").getAsBoolean();

            scanner.close();
            ChristmasX.getInstance().print("Loaded config!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
