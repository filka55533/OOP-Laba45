package filka.core.Commands;

import filka.core.Boats.Boat;
import javafx.scene.control.TableView;

public class SetBoatsOnTable extends Command{

    private TableView<Boat> boatTableView;
    public SetBoatsOnTable (ApplicationController app, TableView<Boat> boatTableView){

        super(app);
        this.boatTableView = boatTableView;

    }

    @Override
    public boolean execute(){

        boatTableView.setItems(app.getBoatsList());
        return true;

    }

}