package filka.core;

import filka.core.Boats.Boat;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface Serializer {

    void toSerializeBoats(ObservableList<Boat> boatsList) throws Exception;

    ObservableList<Boat> toDeserializeBoats() throws Exception;

    String getServiceName();

    static List<CipherService> getServices(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, CipherService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

}