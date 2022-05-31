package ciphering;


import filka.core.CipherService;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.file.Files;

public class AES implements CipherService {

    private static final byte[] secretKey ={
            (byte)0xf7, (byte)0xa0, 0x2d, 0x78,
            0x3d, 0x50, (byte)0xaf, 0x77,
            0x63, (byte)0x83, (byte)0xf0, (byte)0xa2,
            0x53, (byte)0xc9, (byte)0x84, (byte)0xb1
    };

    private static final byte[] iv = {
            0x45, 0x7d, (byte)0xb6, 0x56,
            0x38, 0x4f, (byte)0xa4, 0x61,
            0x3c, 0x0f, (byte)0x80, 0x0f,
            0x3c, 0x74, (byte)0x0c, 0x7c

    };

    @Override
    public boolean decryptFile(String fileName){

        boolean result = true;

        try {
            File fileInfo = new File(fileName);
            byte[] fileBytes = Files.readAllBytes(fileInfo.toPath());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKey aesKey = new SecretKeySpec(secretKey, "AES");
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

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKey aesKey = new SecretKeySpec(secretKey, "AES");
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

        return "AES";
    }

}