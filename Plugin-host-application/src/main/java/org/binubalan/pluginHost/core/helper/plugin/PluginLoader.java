package org.binubalan.pluginHost.core.helper.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.binubalan.pluginIntegrations.abstracts.IPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {
    public static final String PLUGIN_DIR = getPluginDirFromEnv();
    public static final String PLUGIN_EXTENSION = ".jar";

    public static final String PLUGIN_TOUCH_CONFIG = "touch.json";

    public static final String TOUCH_CONFIG_BOOTSTRAP = "bootStrapClass";
    public static final String ENV_KEY_TOUCH_PLUGIN_DIR = "TOUCH_PLUGIN_DIR";

    private static PluginLoader instance;

    private Map<String, String> repository = new HashMap<>();

    private LinkedList<String> availableJars;

    public static PluginLoader getInstance() throws Exception {
        if (instance == null) {
            instance = new PluginLoader();
        }
        return instance;
    }

    private static String getPluginDirFromEnv(){
        String pDir = System.getenv(ENV_KEY_TOUCH_PLUGIN_DIR);
        if(pDir == null) {
            pDir = "plugins";
        }

        return pDir;
    }

    private PluginLoader() throws Exception {
        this.availableJars = getAllJars();
    }

    public static LinkedList<String> getAllJars() throws Exception {
        final File directory = new File(PLUGIN_DIR);
        if (!directory.isDirectory() || !directory.exists()) {
            throw new Exception("Fatal error: No plugin directory found: " + PLUGIN_DIR);
        }
        LinkedList<String> jars = new LinkedList<>();
        Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .forEach(file -> {
                    if (file.isDirectory()) {
                        return;
                    }
                    final String fileName = file.getName();
                    System.out.println(fileName);
                    if (fileName.endsWith(PLUGIN_EXTENSION)) {
                        System.out.println("Jar identified: " + fileName);
                        jars.add(fileName);
                    }
                });
        return jars;
    }

    private boolean isLoaded(String uri) {
        return this.repository.containsKey(uri);
    }

    private boolean isJarsAvailable() {
        return this.availableJars != null && this.availableJars.size() > 0;
    }

    public void load(String uri) throws Exception {
        if (!this.isJarsAvailable()) {
            System.out.println("No Plugins found");
        }
        if (this.isLoaded(uri)) {
            System.out.println("Skipping " + uri + " as it is already loaded");
            return;
        }

        if (!this.availableJars.contains(uri)) {
            System.out.println("Skipping " + uri + " as it is not loaded in marketplace : FATAL");
            return;
        }

        uri = PLUGIN_DIR + File.separator + uri;

        JarFile jarFile = new JarFile(new File(uri));

        //load the touch json config
        JsonObject touchConfig = this.loadConfig(jarFile);
        this.loadJar(uri, touchConfig);
    }

    public JsonObject loadConfig(JarFile jarFile) throws IOException {
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            if (!jarEntry.getName().endsWith(PLUGIN_TOUCH_CONFIG)) {
                continue;
            }
            return (new Gson())
                    .fromJson(
                            new InputStreamReader(jarFile.getInputStream(jarEntry)),
                            JsonObject.class
                    );
        }
        System.out.println("No Touch config found so skipping");
        return null;
    }

    public void loadJar(String uri, JsonObject touchConfig) throws Exception {
        File jarF = new File(uri);
        URLClassLoader urlClassLoader = new URLClassLoader(
                new URL[]{jarF.toURI().toURL()},
                this.getClass().getClassLoader()
        );

        final String bootStrapClass = touchConfig.get(TOUCH_CONFIG_BOOTSTRAP).getAsString();
        Class<?> pluginEntryClass = Class.<IPlugin>forName(bootStrapClass, true, urlClassLoader);
        IPlugin plugin = (IPlugin) pluginEntryClass.getConstructor().newInstance();
        plugin.onMounted();

    }
}
