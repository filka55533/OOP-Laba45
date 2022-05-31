package filka.core.Commands;

public class DeleteBoatCommand extends Command{

    protected int boatPosition;

    public DeleteBoatCommand(ApplicationController app, int boatPosition){

        super(app);
        this.boatPosition = boatPosition;

    }

    @Override
    public boolean execute(){

        boolean res = true;

        if (boatPosition >= 0 && boatPosition < app.getBoatsList().size())
            app.getBoatsList().remove(boatPosition);

        return res;
    }


}