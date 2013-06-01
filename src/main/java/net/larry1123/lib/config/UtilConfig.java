package net.larry1123.lib.config;


public class UtilConfig {

    private String plugin;
    private static UtilConfig config = new UtilConfig("CanaryUtil");

    private BungeeCordConfig bungeecordConfig;
    private LoggerConfig loggerConfig;

    public static UtilConfig getConfig() {
        return config;
    }

    public UtilConfig(String plugin) {
        this.plugin = plugin;
        bungeecordConfig = new BungeeCordConfig(this.plugin);
        loggerConfig = new LoggerConfig(this.plugin);
    }

    public BungeeCordConfig getBungeeCordConfig() {
        return bungeecordConfig;
    }

    public void reloadBungeeCordConfig() {
        bungeecordConfig = new BungeeCordConfig(this.plugin);
    }

    public LoggerConfig getLoggerConfig() {
        return loggerConfig;
    }

    public void reloadLoggerConfig() {
        loggerConfig = new LoggerConfig(this.plugin);
    }

}
