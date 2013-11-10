package net.larry1123.util.config;

import net.canarymod.api.world.World;
import net.canarymod.config.Configuration;
import net.canarymod.plugin.Plugin;
import net.visualillusionsent.utils.PropertiesFile;

import java.io.File;
import java.util.HashMap;

public class UtilConfigManager {

    private static final UtilConfigManager config = new UtilConfigManager();

    private static HashMap<ConfigBase, HashMap<String, PropertiesFile>> plugin_cfg_cache = new HashMap<ConfigBase, HashMap<String, PropertiesFile>>();

    private final String pluginName = "CanaryUtil";
    private BungeeCordConfig bungeecordConfig;
    private LoggerConfig loggerConfig;

    /**
     * Gets the Config Manager
     *
     * @return Config Manager
     */
    public static UtilConfigManager getConfig() {
        return config;
    }

    private UtilConfigManager() {}

    /**
     * Gets the Config for BungeeCord
     *
     * @return Config Manager for BungeeCord
     */
    public BungeeCordConfig getBungeeCordConfig() {
        if (bungeecordConfig == null) bungeecordConfig = new BungeeCordConfig(pluginName);
        return bungeecordConfig;
    }

    /**
     * Reloads the Config of the BungeeCord Config Manager
     */
    public void reloadBungeeCordConfig() {
        getBungeeCordConfig().reload();
    }

    /**
     * Gets the Config for the Logger
     *
     * @return Config Manager for Logger
     */
    public LoggerConfig getLoggerConfig() {
        if (loggerConfig == null) loggerConfig = new LoggerConfig(pluginName);
        return loggerConfig;
    }

    /**
     * Reloads the Config of the Logger Config Manager
     */
    public void reloadLoggerConfig() {
        getLoggerConfig().reload();
    }

    /**
     * Well lets at lest try our best at the Deprecated side of this
     *
     * @param configBase What ConfigBase is this PropFile for
     * @param filePath Well the path to the file
     * @return PropertiesFile you wanted
     */
    private static PropertiesFile getPluginCachedConfig(ConfigBase configBase, String filePath) {
        if (!plugin_cfg_cache.containsKey(configBase)) {
            plugin_cfg_cache.put(configBase, new HashMap<String, PropertiesFile>());
        }
        if (!plugin_cfg_cache.get(configBase).containsKey(filePath)) {
            PropertiesFile file = new PropertiesFile(filePath);
            file.save();

            plugin_cfg_cache.get(configBase).put(filePath, file);
        }
        return plugin_cfg_cache.get(configBase).get(filePath);
    }

    /**
     * Used for getting config file before you have a {@link net.canarymod.plugin.Plugin} object
     *
     * @param config What {@link ConfigBase} to base the wrapper's logic about
     * @param plugin the name of the {@link net.canarymod.plugin.Plugin}
     */
    @Deprecated
    public ConfigFile getPluginConfig(ConfigBase config, String plugin) {
        PropertiesFile propertiesFile = getPluginCachedConfig(config, "config" + File.separatorChar + plugin + File.separatorChar + plugin + ".cfg");
        return new ConfigFile(config, propertiesFile);
    }

    /**
     * Makes a wrapper for the server-wide configuration of a plugin
     *
     * @param config What {@link ConfigBase} to base the wrapper's logic about
     * @param plugin the {@link net.canarymod.plugin.Plugin}
     */
    public ConfigFile getPluginConfig(ConfigBase config, Plugin plugin) {
        return new ConfigFile(config, Configuration.getPluginConfig(plugin));
    }

    /**
     * Used for getting config file before you have a {@link Plugin} object
     *
     * @param config What {@link ConfigBase} to base the wrapper's logic about
     * @param plugin the name of the {@link Plugin}
     * @param module Used to create multiple configurations for a single {@link Plugin}.
     */
    @Deprecated
    public ConfigFile getPluginConfig(ConfigBase config, String plugin, String module) {
        PropertiesFile propertiesFile = getPluginCachedConfig(config, "config" + File.separatorChar + plugin + File.separatorChar + plugin + "." + module + ".cfg");
        return new ConfigFile(config, propertiesFile);
    }

    /**
     * Makes a wrapper for the server-wide configuration of a {@link Plugin}
     *
     * @param config What {@link ConfigBase} to base the wrapper's logic about
     * @param plugin the {@link Plugin}
     * @param module Used to create multiple configurations for a single {@link Plugin}.
     */
    public ConfigFile getPluginConfig(ConfigBase config, Plugin plugin, String module) {
        return new ConfigFile(config, Configuration.getPluginConfig(plugin, module));
    }

    /**
     * Used for getting config file before you have a {@link Plugin} object
     *
     * @param config What {@link ConfigBase} to base the wrapper's logic about
     * @param plugin the name of the {@link Plugin}
     * @param world  the world
     */
    @Deprecated
    public ConfigFile getPluginConfig(ConfigBase config, String plugin, World world) {
        PropertiesFile propertiesFile = getPluginCachedConfig(config, "config" + File.separatorChar + plugin + File.separatorChar + "worlds" + File.separatorChar + world.getFqName() + File.separatorChar + plugin + ".cfg");
        return new ConfigFile(config, propertiesFile);
    }

    /**
     * Makes a wrapper for the world-specific configuration of a {@link Plugin} If there is no world-specific configuration, it will take the server-wide
     * configuration
     *
     * @param config What {@link ConfigBase} to base the wrapper's logic about
     * @param plugin the {@link Plugin}
     * @param world  the world
     */
    public ConfigFile getPluginConfig(ConfigBase config, Plugin plugin, World world) {
        return new ConfigFile(config, Configuration.getPluginConfig(plugin, world));
    }

    /**
     * Used for getting config file before you have a {@link Plugin} object
     *
     * @param config What {@link ConfigBase} to base the wrapper's logic about
     * @param plugin the name of the {@link Plugin}
     * @param module Used to create multiple configurations for a single {@link Plugin}.
     * @param world  the world
     */
    @Deprecated
    public ConfigFile getPluginConfig(ConfigBase config, String plugin, String module, World world) {
        PropertiesFile propertiesFile = getPluginCachedConfig(config, "config" + File.separatorChar + plugin + File.separatorChar + "worlds" + File.separatorChar + world.getFqName() + File.separatorChar + plugin + "." + module + ".cfg");
        return new ConfigFile(config, propertiesFile);
    }

    /**
     * Makes a wrapper for the world-specific configuration of a {@link Plugin} If there is no world-specific configuration, it will take the server-wide
     * configuration
     *
     * @param config What {@link ConfigBase} to base the wrapper's logic about
     * @param plugin the {@link Plugin}
     * @param module Used to create multiple configurations for a single {@link Plugin}.
     * @param world  the world
     */
    public ConfigFile getPluginConfig(ConfigBase config, Plugin plugin, String module, World world) {
        return new ConfigFile(config, Configuration.getPluginConfig(plugin, module, world));
    }

}
