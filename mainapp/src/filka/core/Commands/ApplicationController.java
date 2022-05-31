package filka.core.Commands;

import filka.core.BoatCreateDelegate;
import filka.core.Boats.Boat;
import filka.core.Boats.Multihull;
import filka.core.CipherService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ApplicationController {

    private ObservableList<Boat> boatsList;
    BoatCreateDelegate dlCreateBoat;
    private List<CipherService> services;

    ObservableList<Boat> getBoatsList() {
        return boatsList;
    }

    void setBoatsList(ObservableList<Boat> boatsList) {
        this.boatsList = (boatsList != null) ? boatsList : FXCollections.observableArrayList();
    }

    public ApplicationController(ObservableList<Boat> boatsList, List<CipherService> services){

        setBoatsList(boatsList);
        this.services = services;

        dlCreateBoat = Multihull::new;

    }


    void setDlCreateBoat(BoatCreateDelegate delegate){
        this.dlCreateBoat = delegate;

    }

    public BoatCreateDelegate getDlCreateBoat(){

        return this.dlCreateBoat;

    }


    List<CipherService> getServices() {
        return services;
    }

    public boolean executeCommand(Command command){
        return command.execute();
    }


    public Boat getBoatWithIndex(int index){

        return (index >= 0 && boatsList.size() > index) ? boatsList.get(index) : null;

    }
}