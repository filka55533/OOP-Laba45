package filka.core.Commands;

import filka.core.*;
import filka.core.Boats.Boat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class DeserializeBoatsCommand extends Command{

    public DeserializeBoatsCommand(ApplicationController app){

        super(app);

    }

    @Override
    public boolean execute(){

        boolean res = false;
        ObservableList<Boat> boats = FXCollections.observableArrayList();

        for (CipherService service: app.getServices()){

            if (res) break;

            Serializer serializer = new SerializerDecorator(new BSONSerializer());
            serializer = new CipherDecorator(serializer, service, BSONSerializer.DOCUMENT_NAME);

            try{

                boats = serializer.toDeserializeBoats();
                res = true;

            }catch (Exception e){

                System.out.println("Can not to deserialize");

            }

        }

        app.setBoatsList(boats);

        return res;
    }





}