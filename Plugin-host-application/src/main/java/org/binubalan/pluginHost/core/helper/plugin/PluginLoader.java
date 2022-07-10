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

/**
 * Plugin loader
 */
public class PluginLoader {

    /**
     * Plugin directory uri
     */
    public static final String PLUGIN_DIR = getPluginDirFromEnv();

    /**
     * Plugin file extension
     */
    public static final String PLUGIN_EXTENSION = ".jar";

    /**
     * Touch configuration file name
     */

    public static final String PLUGIN_TOUCH_CONFIG = "touch.json";

    /**
     * Touch configuration key for bootstrap class
     */
    public static final String TOUCH_CONFIG_BOOTSTRAP = "bootStrapClass";

    /**
     * Plugin directory environment key
     */
    public static final String ENV_KEY_TOUCH_PLUGIN_DIR = "TOUCH_PLUGIN_DIR";

    /**
     * Plugin loader singleton instance
     */
    private static PluginLoader instance;

    /**
     * The repository to store the loaded plugins
     */
    private Map<String, String> repository = new HashMap<>();

    /**
     * Available jars in the plugin directory
     */
    private LinkedList<String> availableJars;

    /**
     * Static method to create and get singleton instance
     *
     * @return PluginLoader
     */
    public static PluginLoader getInstance() throws Exception {
        if (instance == null) {
            instance = new PluginLoader();
        }
        return instance;
    }

    /**
     * Method to get plugin directory from environment variable
     *
     * @return Plugin directory name
     */
    private static String getPluginDirFromEnv() {
        String pDir = System.getenv(ENV_KEY_TOUCH_PLUGIN_DIR);
        if (pDir == null) {
            pDir = "plugins";
        }

        return pDir;
    }

    private PluginLoader() throws Exception {
        this.availableJars = getAllJars();
    }

    /**
     * Static method to get all jar files in the plugin directory
     *
     * @return List of jar file URIs
     */
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

    /**
     * Method to check a Plugin is already loaded
     *
     * @param uri plugin URI
     * @return True if loaded, False otherwise
     */
    private boolean isLoaded(String uri) {
        return this.repository.containsKey(uri);
    }

    /**
     * Method to check if any Jar files are available
     *
     * @return True if available, False otherwise
     */
    private boolean isJarsAvailable() {
        return this.availableJars != null && this.availableJars.size() > 0;
    }

    /**
     * Method to load the plugin from the jar URI
     *
     * @param uri the plugin URI
     */
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

    /**
     * Method to load touch config from jar file
     *
     * @param jarFile the jar file
     * @return JsonObject, config object
     */
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

    /**
     * Method to load the jar file
     *
     * @param uri         Jar file's URI
     * @param touchConfig the Touch config object
     */
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
