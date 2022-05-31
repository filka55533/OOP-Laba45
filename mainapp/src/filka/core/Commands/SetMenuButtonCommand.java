package filka.core.Commands;

import filka.core.BoatCreateDelegate;
import javafx.scene.control.MenuButton;

public class SetMenuButtonCommand extends Command{

    private BoatCreateDelegate dlCreateBoat;
    private MenuButton mbType;

    public SetMenuButtonCommand(ApplicationController app, MenuButton mbType, BoatCreateDelegate dlCreateBoat){

        super(app);
        this.dlCreateBoat = dlCreateBoat;
        this.mbType = mbType;

    }

    @Override
    public boolean execute(){

        app.setDlCreateBoat(dlCreateBoat);
        mbType.setText(dlCreateBoat.initializing().getType());
        return true;

    }


}