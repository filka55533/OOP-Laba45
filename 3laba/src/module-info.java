import filka.core.CipherService;
import ledza.plugin.*;

module plugin {

    requires org.apache.commons.codec;
    requires mainapp;

    exports ledza.plugin;

    uses Plugin;
    provides Plugin with HexPlugin, Base64Plugin, BinaryPluginAdapter;

    uses CipherService;
    provides CipherService with Base64PluginCipherAdapter, HexPluginCipherAdapter, BinaryPluginCipherAdapter;

}