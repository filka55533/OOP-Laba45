package filka.core.Commands;

import filka.core.BSONSerializerAdapter;
import filka.core.Boats.Boat;
import filka.core.CipherService;
import filka.core.Serializer;

import java.util.List;

public class SerializeBoatsCommand extends  Command{

    private String serviceName;

    public SerializeBoatsCommand(ApplicationController app, String serviceName){

        super(app);
        this.serviceName = serviceName;

    }

    @Override
    public boolean execute(){

        boolean res = true;

        Serializer serializer = new BSONSerializerAdapter(serviceName, app.getServices());
        try {

            serializer.toSerializeBoats(app.getBoatsList());

        }catch (Exception e){

            res = false;

        }

        return res;

    }


}