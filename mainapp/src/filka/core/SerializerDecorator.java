package filka.core;

import filka.core.Boats.Boat;
import filka.core.Commands.ApplicationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SerializerDecorator implements Serializer{

    private Serializer wrapper;

    public SerializerDecorator(){
        this.wrapper = new BSONSerializer();
    }

    public SerializerDecorator(Serializer wrapper){

        if (wrapper != null)
            this.wrapper = wrapper;
        else
            this.wrapper = new BSONSerializer();
    }

    @Override
    public void toSerializeBoats(ObservableList<Boat> boatsList) throws Exception{

        this.wrapper.toSerializeBoats(boatsList);

    }

    @Override
    public ObservableList<Boat> toDeserializeBoats() throws Exception {

        return this.wrapper.toDeserializeBoats();
    }

    @Override
    public String getServiceName(){

        if (this.wrapper != null)
            return this.wrapper.getServiceName();
        else
            return "";

    }
}