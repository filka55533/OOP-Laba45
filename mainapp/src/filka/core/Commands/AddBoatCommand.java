package filka.core.Commands;

import filka.core.Boats.Boat;

public class AddBoatCommand extends Command{

    private Boat boatToAdd;
    public AddBoatCommand(ApplicationController app, Boat boatToAdd){
        super(app);
        this.boatToAdd = boatToAdd;

    }

    @Override
    public boolean execute(){

        app.getBoatsList().add(boatToAdd);
        return true;

    }


}