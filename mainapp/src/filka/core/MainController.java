package filka.core;

import filka.core.Boats.*;
import filka.core.Commands.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public class MainController {

    //Offsets for gui
    private final double VERTICAL_OFFSET = 50.0;
    private final double HORIZONTAL_OFFSET = 50.0;
    private final int COUNT_COLUMNS = 5;

//    BoatCreateDelegate dlCreateBoat;
    Boat boatToProcess;

    ApplicationController app;

    @FXML
    MenuButton mbType, mbCiphers;

    @FXML
    private TableView<Boat> tvObjectsTable;

    @FXML
    private TableColumn<Boat, String> clmName, clmType, clmLength, clmWeight, clmCrewCount;

    private List<CipherService> cipherServices;

    //Method for initializing form
    public void initForm(){
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        //Initialization of table
        tvObjectsTable.setLayoutY(VERTICAL_OFFSET);
        tvObjectsTable.setLayoutX(HORIZONTAL_OFFSET);
        tvObjectsTable.setPrefHeight(screenHeight / 2);

        screenWidth = screenWidth - 2 * HORIZONTAL_OFFSET;
        tvObjectsTable.setPrefWidth(screenWidth );

        for (TableColumn i: tvObjectsTable.getColumns()){
            i.setPrefWidth(screenWidth / COUNT_COLUMNS);
        }


        initColumns();

        cipherServices = MainApplication.getCiphers();

        //Initialization of items and trying deserialize

        boolean isDeserialize = false;
        for (CipherService service: cipherServices){
            MenuItem item = new MenuItem(service.getServiceName());
            mbCiphers.getItems().add(item);
            item.setOnAction( ae -> mbCiphers.setText( ((MenuItem)(ae.getSource())).getText() ) );
            mbCiphers.setText(item.getText());

        }

        app = new ApplicationController(null, cipherServices);
        app.executeCommand(new DeserializeBoatsCommand(app));
        app.executeCommand(new SetBoatsOnTable(app, tvObjectsTable));


    }


    private void initColumns(){

        clmName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        clmCrewCount.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getCrewCount())));
        clmLength.setCellValueFactory(c -> new SimpleStringProperty(Double.toString(c.getValue().getLength())));
        clmWeight.setCellValueFactory(c -> new SimpleStringProperty(Double.toString(c.getValue().getWeight())));
        clmType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));

    }


    //Set delegates on click
    @FXML
    private void onMultihullBtn(){

        app.executeCommand(new SetMenuButtonCommand(app, mbType, Multihull::new));

    }


    @FXML
    private void onMonohullBtn(){

        app.executeCommand(new SetMenuButtonCommand(app, mbType, Multihull::new));

    }

    @FXML
    private void onMotorboatBtn(){

        app.executeCommand(new SetMenuButtonCommand(app, mbType, MotorBoat::new));

    }


    @FXML
    private void onWarshipBtn(){

        app.executeCommand(new SetMenuButtonCommand(app, mbType, WarShip::new));

    }


    @FXML
    private void onCargoBoatBtn(){

        app.executeCommand(new SetMenuButtonCommand(app, mbType, CargoBoat::new));

    }


    @FXML
    private void onAddBtnClick() {

        openForm("Add", app.getDlCreateBoat().initializing());
        if (boatToProcess != null) {

            app.executeCommand(new AddBoatCommand(app, boatToProcess));
            tvObjectsTable.refresh();

        }

    }


    @FXML
    private void onEditBtnClick(){
        int index = tvObjectsTable.getSelectionModel().getSelectedIndex();

        if (index < 0)
            EditController.showErrorMessage("Boat not selected");
        else{
            openForm("Edit", app.getBoatWithIndex(index));
            if (boatToProcess != null){

                app.executeCommand(new EditBoatCommand(app, index, boatToProcess));
                tvObjectsTable.refresh();

            }
        }

    }


    @FXML
    private void onDeleteBtnClick(){

        int index = tvObjectsTable.getSelectionModel().getSelectedIndex();

        if (app.executeCommand(new DeleteBoatCommand(app, index)))
            tvObjectsTable.refresh();
        else
            EditController.showErrorMessage("No fields selected");

    }


    @FXML
    private void onSerializeBtnClick(){


        app.executeCommand( new SerializeBoatsCommand(app, mbCiphers.getText()) );
//        serializer = new BSONSerializerAdapter(mbCiphers.getText(), cipherServices);
//
//        try {
//
//            serializer.toSerializeBoats(olBoatsList);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            EditController.showErrorMessage("Unknown error in serializing");
//        }

    }


    //Controller for editor form
    EditController editController;
    private void openForm(String btnText, Boat boat){

        try {

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/filka/edit-form.fxml"));
            Parent root = fxmlLoader.load();

            //Initialization of the form
            editController = fxmlLoader.getController();
            editController.setBtnEditText(btnText);
            editController.initForm(boat);

            stage.setTitle(btnText + " boat");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest( windowEvent -> onCloseForm() );
            stage.showAndWait();

            boatToProcess = editController.getBoat();

        }catch (Exception e){

            e.printStackTrace();

        }

    }


    private void onCloseForm(){

        boatToProcess = editController.getBoat();

    }

}