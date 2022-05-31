package filka.core;



public class BasicCipherService implements CipherService {

    @Override
    public boolean decryptFile(String fileName){
        return true;
    }

    @Override
    public boolean encryptFile(String fileName){
        return true;
    }

    @Override
    public String getServiceName(){
        return "No ciphering";
    }

}