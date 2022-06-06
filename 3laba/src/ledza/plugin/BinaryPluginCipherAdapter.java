package ledza.plugin;

import filka.core.CipherService;

import java.io.File;
import java.nio.file.Files;

public class BinaryPluginCipherAdapter extends BasicCipherAdapter {

    public BinaryPluginCipherAdapter(){

        super(new BinaryPluginAdapter());

    }

}