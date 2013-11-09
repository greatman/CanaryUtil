package net.larry1123.util.config;

import net.canarymod.api.world.World;
import net.canarymod.config.Configuration;
import net.canarymod.plugin.Plugin;
import net.visualillusionsent.utils.PropertiesFile;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.lang.reflect.Field;

public class ConfigFile {

    /**
     * The PropertiesFile to control
     */
    private final PropertiesFile propertiesfile;

    /**
     * The ConfigBase to base the PropertiesFile on
     */
    private final ConfigBase config;

    /**
     * Holds a list of usable Fields
     */
    private Field[] configFields;

    /**
     * Creates a ConfigFile controller for a given PropertiesFile
     * Will load data from file to ConfigBase
     *
     * @param config         ConfigBase to use
     * @param propertiesfile PropertiesFile to be controlled
     */
    public ConfigFile(ConfigBase config, PropertiesFile propertiesfile) {
        // Just got to make sure this stuff is not null
        if (propertiesfile == null) throw new NullPointerException("PropertiesFile can not be null");
        if (config == null) throw new NullPointerException("ConfigBase can not be null");
        this.config = config;
        this.propertiesfile = propertiesfile;
        for (Field field : config.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigField.class)) {
                ArrayUtils.add(configFields, field);
            }
        }
        for (Field field : config.getClass().getFields()) {
            if (field.isAnnotationPresent(ConfigField.class)) {
                if (!ArrayUtils.contains(configFields, field)) {
                    ArrayUtils.add(configFields, field);
                }
            }
        }
        ConfigLogic.load(propertiesfile, config, configFields);
    }

    /**
     * Used for getting config file before you have a {@link Plugin} object
     *
     * @param config What ConfigBase to base the wrapper's logic about
     * @param plugin the name of the {@link Plugin}
     */
    @Deprecated
    public ConfigFile(ConfigBase config, String plugin) {
        this(config, new PropertiesFile("config" + File.separatorChar + plugin + File.separatorChar + plugin + ".cfg"));
    }

    /**
     * Makes a wrapper for the server-wide configuration of a plugin
     *
     * @param config What ConfigBase to base the wrapper's logic about
     * @param plugin the {@link Plugin}
     */
    public ConfigFile(ConfigBase config, Plugin plugin) {
        this(config, Configuration.getPluginConfig(plugin));
    }

    /**
     * Used for getting config file before you have a {@link Plugin} object
     *
     * @param config What ConfigBase to base the wrapper's logic about
     * @param plugin the name of the {@link Plugin}
     * @param module Used to create multiple configurations for a single {@link Plugin}.
     */
    @Deprecated
    public ConfigFile(ConfigBase config, String plugin, String module) {
        this(config, new PropertiesFile("config" + File.separatorChar + plugin + File.separatorChar + plugin + "." + module + ".cfg"));
    }

    /**
     * Makes a wrapper for the server-wide configuration of a {@link Plugin}
     *
     * @param config What ConfigBase to base the wrapper's logic about
     * @param plugin the {@link Plugin}
     * @param module Used to create multiple configurations for a single {@link Plugin}.
     */
    public ConfigFile(ConfigBase config, Plugin plugin, String module) {
        this(config, Configuration.getPluginConfig(plugin, module));
    }

    /**
     * Used for getting config file before you have a {@link Plugin} object
     *
     * @param config What ConfigBase to base the wrapper's logic about
     * @param plugin the name of the {@link Plugin}
     * @param world  the world
     */
    @Deprecated
    public ConfigFile(ConfigBase config, String plugin, World world) {
        this(config, new PropertiesFile("config" + File.separatorChar + plugin + File.separatorChar + "worlds" + File.separatorChar + world.getFqName() + File.separatorChar + plugin + ".cfg"));
    }

    /**
     * Makes a wrapper for the world-specific configuration of a {@link Plugin} If there is no world-specific configuration, it will take the server-wide
     * configuration
     *
     * @param config What ConfigBase to base the wrapper's logic about
     * @param plugin the {@link Plugin}
     * @param world  the world
     */
    public ConfigFile(ConfigBase config, Plugin plugin, World world) {
        this(config, Configuration.getPluginConfig(plugin, world));
    }

    /**
     * Used for getting config file before you have a {@link Plugin} object
     *
     * @param config What ConfigBase to base the wrapper's logic about
     * @param plugin the name of the {@link Plugin}
     * @param module Used to create multiple configurations for a single {@link Plugin}.
     * @param world  the world
     */
    @Deprecated
    public ConfigFile(ConfigBase config, String plugin, String module, World world) {
        this(config, new PropertiesFile("config" + File.separatorChar + plugin + File.separatorChar + "worlds" + File.separatorChar + world.getFqName() + File.separatorChar + plugin + "." + module + ".cfg"));
    }

    /**
     * Makes a wrapper for the world-specific configuration of a {@link Plugin} If there is no world-specific configuration, it will take the server-wide
     * configuration
     *
     * @param config What ConfigBase to base the wrapper's logic about
     * @param plugin the {@link Plugin}
     * @param module Used to create multiple configurations for a single {@link Plugin}.
     * @param world  the world
     */
    public ConfigFile(ConfigBase config, Plugin plugin, String module, World world) {
        this(config, Configuration.getPluginConfig(plugin, module, world));
    }

    /**
     * Gets the controlled PropertiesFile
     * You should know what you are doing if you try to make changes directly
     *
     * @return PropertiesFile being controlled
     */
    public PropertiesFile getPropFile() {
        return propertiesfile;
    }

    /**
     * Updates the ConfigBase to match the current file without saving.
     * Tells the controlled {@link PropertiesFile} to reload first.
     * If you made changes that you want to be reloaded into the ConfigBase make sure it to save first.
     */
    public void reload() {
        propertiesfile.reload();
        ConfigLogic.load(propertiesfile, config, configFields);
    }

    /**
     * Saves data to disk if any change has been made
     */
    public void save() {
        ConfigLogic.save(propertiesfile, config, configFields);
    }

}
