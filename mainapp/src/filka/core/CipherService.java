package filka.core;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface CipherService {

    boolean decryptFile(String fileName);
    boolean encryptFile(String fileName);

    String getServiceName();

    static List<CipherService> getServices(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, CipherService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

}