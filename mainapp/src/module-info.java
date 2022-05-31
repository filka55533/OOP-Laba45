import filka.core.BasicCipherService;
import filka.core.CipherService;


module mainapp {

    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires org.mongodb.bson;

    opens filka.core to javafx.fxml;

    exports filka.core;
    exports filka.core.Boats;

    uses CipherService;
    provides CipherService with BasicCipherService;
}