package cipher;

import filka.core.CipherService;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.file.Files;

public class Des implements CipherService {

    private static final byte[] secretKey ={
            0x39, (byte) 0xbc, 0x66, 0x5c,
            0x52, (byte)0xcf, (byte)0x8e, (byte)0xe4
    };

    private static final byte[] iv = {
            (byte)0xf9, 0x6b, 0x03, 0x40,
            (byte)0xaa, (byte)0x83, 0x51, (byte)0xa9
    };

    @Override
    public boolean decryptFile(String fileName){

        boolean result = true;

        try {
            File fileInfo = new File(fileName);
            byte[] fileBytes = Files.readAllBytes(fileInfo.toPath());

            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            SecretKey aesKey = new SecretKeySpec(secretKey, "DES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));

            byte[] decryptedFile = cipher.doFinal(fileBytes);
            Files.write(fileInfo.toPath(), decryptedFile);

        }catch (Exception e){

            result = false;
        }

        return result;
    }

    @Override
    public boolean encryptFile(String fileName){

        boolean result = true;

        try{
            File fileInfo = new File(fileName);
            byte[] fileBytes = Files.readAllBytes(fileInfo.toPath());

            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            SecretKey aesKey = new SecretKeySpec(secretKey, "DES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(iv));

            byte[] decryptedFile = cipher.doFinal(fileBytes);
            Files.write(fileInfo.toPath(), decryptedFile);


        }catch (Exception e){

            result = false;
        }


        return result;
    }

    @Override
    public String getServiceName(){

        return "DES";
    }


}