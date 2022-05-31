package filka.core.Commands;

import filka.core.Boats.Boat;

public class EditBoatCommand extends DeleteBoatCommand{

    private Boat newBoat;
    public EditBoatCommand(ApplicationController app, int boatPosition, Boat newBoat){

        super(app, boatPosition);
        this.newBoat = newBoat;

    }

    @Override
    public boolean execute(){

        boolean result = super.execute();
        if (result){

            app.getBoatsList().add(boatPosition, newBoat);

        }

        return result;

    }




}