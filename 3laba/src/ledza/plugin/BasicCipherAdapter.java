package ledza.plugin;

import filka.core.CipherService;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;

abstract class BasicCipherAdapter implements CipherService {

    private final Plugin plugin;
    public BasicCipherAdapter(Plugin plugin){

        this.plugin = plugin;

    }


    @Override
    public boolean encryptFile(String fileName){

        boolean res = true;

        try {

            File fileInfo = new File(fileName);
            byte[] bytes = Files.readAllBytes(fileInfo.toPath());
            bytes = this.plugin.encode(new String(bytes)).getBytes();
            Files.write(fileInfo.toPath(), bytes);

        }catch (Exception e){

            res = false;

        }

        return res;
    }

    @Override
    public boolean decryptFile(String fileName){

        boolean res = true;
        try{

            File fileInfo = new File(fileName);
            byte[] bytes = Files.readAllBytes(fileInfo.toPath());
            bytes = this.plugin.decode(new String(bytes)).getBytes();
            Files.write(fileInfo.toPath(), bytes);

        }catch (Exception e){

            res = false;

        }

        return res;
    }

    @Override
    public String getServiceName(){

        return this.plugin.getSignature();

    }



}