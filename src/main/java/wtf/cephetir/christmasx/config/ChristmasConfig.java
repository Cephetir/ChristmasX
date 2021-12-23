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
    static File dir = new File(Minecraft.getMinecraft().mcDataDir, "/config");
    static File file = new File(dir, "/christamsx.json");


    public static int bg = 1;
    public static int speedX = 2;
    public static int speedY = 3;
    public static int pAmount = 150;
    public static boolean toggleButtons = true;
    public static boolean toggleSplash = true;

    public static void save() {
        try {
            ChristmasX.getInstance().print("Saving config...");
            if (!ChristmasConfig.dir.exists()) ChristmasConfig.dir.mkdirs();
            ;
            if (!ChristmasConfig.file.exists()) ChristmasConfig.file.createNewFile();
            ChristmasX.getInstance().print("Saving config...");
            Gson gson = new Gson();
            JsonObject json = new JsonObject();
            BufferedWriter writer = new BufferedWriter(new FileWriter(ChristmasConfig.file));

            json.addProperty("bg", ChristmasConfig.bg);
            json.addProperty("speedX", ChristmasConfig.speedX);
            json.addProperty("speedY", ChristmasConfig.speedY);
            json.addProperty("pAmount", ChristmasConfig.pAmount);
            json.addProperty("toggleButtons", ChristmasConfig.toggleButtons);
            json.addProperty("toggleSplash", ChristmasConfig.toggleSplash);

            writer.write(gson.toJson(json));
            writer.close();
            ChristmasX.getInstance().print("Saved config!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            if (!ChristmasConfig.dir.exists()) {
                ChristmasConfig.dir.mkdirs();
                ChristmasConfig.file.createNewFile();
                return;
            }
            ;
            if (!ChristmasConfig.file.exists()) {
                ChristmasConfig.file.createNewFile();
                return;
            }
            if (ChristmasX.getInstance().logger != null) ChristmasX.getInstance().print("Loading config...");
            Gson gson = new Gson();
            Scanner scanner = new Scanner(file);

            JsonObject json = gson.fromJson(scanner.nextLine(), JsonObject.class);
            ChristmasConfig.bg = json.has("bg") ? json.getAsJsonPrimitive("bg").getAsInt() : 1;
            ChristmasConfig.speedX = json.has("speedX") ? json.getAsJsonPrimitive("speedX").getAsInt() : 2;
            ChristmasConfig.speedY = json.has("speedY") ? json.getAsJsonPrimitive("speedY").getAsInt() : 3;
            ChristmasConfig.pAmount = json.has("pAmount") ? json.getAsJsonPrimitive("pAmount").getAsInt() : 150;
            ChristmasConfig.toggleButtons = json.has("toggleButtons") ? json.getAsJsonPrimitive("toggleButtons").getAsBoolean() : true;
            ChristmasConfig.toggleSplash = json.has("toggleSplash") ? json.getAsJsonPrimitive("toggleSplash").getAsBoolean() : true;

            scanner.close();
            if (ChristmasX.getInstance().logger != null) ChristmasX.getInstance().print("Loaded config!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
