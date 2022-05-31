package filka.core;

import filka.core.Boats.Boat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class BSONSerializerAdapter extends BSONSerializer {

    private String serviceName;
    private List<CipherService> services;

    public BSONSerializerAdapter(String serviceName, List<CipherService> services){

        super();
        this.serviceName = serviceName;
        this.services = services;

    }

    @Override
    public void toSerializeBoats(ObservableList<Boat> boatsList) throws IOException {

        for (CipherService service: services){

            if (service.getServiceName().compareTo(serviceName) == 0){

                super.toSerializeBoats(boatsList);
                service.encryptFile(this.DOCUMENT_NAME);

            }

        }

    }


    @Override
    public ObservableList<Boat> toDeserializeBoats() throws Exception {

        ObservableList<Boat> result = FXCollections.observableArrayList();
        boolean isSuccess = false;

        for (CipherService service: services){

            if (isSuccess) break;

            try{
                isSuccess = service.decryptFile(DOCUMENT_NAME);
                result = super.toDeserializeBoats();
                isSuccess = true;
            }catch (Exception e){
                if (isSuccess){
                    service.encryptFile(DOCUMENT_NAME);
                    isSuccess = false;
                }
            }
        }

        if (!isSuccess)
            throw new Exception("Can not configure");

        return result;
    }
}
