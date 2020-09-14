import hudson.PluginWrapper
import jenkins.model.Jenkins

private static void printPlugins() {
    for (PluginWrapper plugin: Jenkins.getInstance().pluginManager.plugins) {
        println("${plugin.getShortName()}:${plugin.getVersion()}")
    }
}
