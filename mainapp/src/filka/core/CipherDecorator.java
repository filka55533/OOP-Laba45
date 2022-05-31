package filka.core;


import filka.core.Boats.Boat;
import javafx.collections.ObservableList;

public class CipherDecorator extends SerializerDecorator {

    private final CipherService cipherService;
    private final String fileForProcess;
    public CipherDecorator(Serializer wrapper, CipherService cipherService, String fileName){

        super(wrapper);
        this.cipherService = cipherService;
        this.fileForProcess = fileName;

    }

    @Override
    public ObservableList<Boat> toDeserializeBoats() throws Exception {

        ObservableList<Boat> res;
        boolean isDecrypted = this.cipherService.decryptFile(this.fileForProcess);

        if (!isDecrypted){

            throw new Exception("Error on encrypting");

        }else{
            try {
                res = super.toDeserializeBoats();

            }catch (Exception e){

                this.cipherService.encryptFile(this.fileForProcess);
                throw new Exception("On serializing");

            }
        }

        return res;

    }


    @Override
    public void toSerializeBoats(ObservableList<Boat> boatsList) throws Exception {

        super.toSerializeBoats(boatsList);
        this.cipherService.encryptFile(this.fileForProcess);

    }

    @Override
    public String getServiceName(){
        return this.cipherService.getServiceName();
    }


}