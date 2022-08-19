package me.cephetir.christmasx.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import me.cephetir.christmasx.ChristmasX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ChristmasConfig {
    private static final File configFolder = new File(Minecraft.getMinecraft().mcDataDir, "/config/christmasx");
    private static final File config = new File(configFolder, "/christamsx.json");

    public static int bg = 1;
    public static int speedX = 2;
    public static int speedY = 3;
    public static int pAmount = 50;
    public static boolean toggleButtons = true;
    public static boolean toggleSplash = true;
    public static boolean toggleSnow = true;
    public static boolean customBg = false;
    public static String customBgPath = "";
    public static boolean buttonOutline = false;

    public static void save() {
        try {
            ChristmasX.logger.info("Saving config...");
            if (!ChristmasConfig.config.exists()) {
                ChristmasConfig.configFolder.mkdirs();
                ChristmasConfig.config.createNewFile();
            }
            ChristmasX.logger.info("Saving config...");
            Gson gson = new Gson();
            JsonObject json = new JsonObject();
            BufferedWriter writer = new BufferedWriter(new FileWriter(ChristmasConfig.config));

            json.addProperty("bg", ChristmasConfig.bg);
            json.addProperty("speedX", ChristmasConfig.speedX);
            json.addProperty("speedY", ChristmasConfig.speedY);
            json.addProperty("pAmount", ChristmasConfig.pAmount);
            json.addProperty("toggleButtons", ChristmasConfig.toggleButtons);
            json.addProperty("toggleSplash", ChristmasConfig.toggleSplash);
            json.addProperty("toggleSnow", ChristmasConfig.toggleSnow);
            json.addProperty("customBg", ChristmasConfig.customBg);
            json.addProperty("customBgPath", ChristmasConfig.customBgPath);

            writer.write(gson.toJson(json));
            writer.close();
            ChristmasX.logger.info("Saved config!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            if (!ChristmasConfig.config.exists()) {
                ChristmasConfig.configFolder.mkdirs();
                ChristmasConfig.config.createNewFile();
                return;
            }
            ChristmasX.logger.info("Loading config...");
            Gson gson = new Gson();
            Scanner scanner = new Scanner(config);

            JsonObject json = gson.fromJson(scanner.nextLine(), JsonObject.class);
            ChristmasConfig.bg = json.has("bg") ? json.getAsJsonPrimitive("bg").getAsInt() : 1;
            ChristmasConfig.speedX = json.has("speedX") ? json.getAsJsonPrimitive("speedX").getAsInt() : 2;
            ChristmasConfig.speedY = json.has("speedY") ? json.getAsJsonPrimitive("speedY").getAsInt() : 3;
            ChristmasConfig.pAmount = json.has("pAmount") ? json.getAsJsonPrimitive("pAmount").getAsInt() : 50;
            ChristmasConfig.toggleButtons = !json.has("toggleButtons") || json.getAsJsonPrimitive("toggleButtons").getAsBoolean();
            ChristmasConfig.toggleSplash = !json.has("toggleSplash") || json.getAsJsonPrimitive("toggleSplash").getAsBoolean();
            ChristmasConfig.toggleSnow = !json.has("toggleSnow") || json.getAsJsonPrimitive("toggleSnow").getAsBoolean();
            ChristmasConfig.customBg = !json.has("customBg") || json.getAsJsonPrimitive("customBg").getAsBoolean();
            ChristmasConfig.customBgPath = json.has("customBgPath") ? json.getAsJsonPrimitive("bg").getAsString() : "";

            scanner.close();
            ChristmasX.logger.info("Loaded config!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
