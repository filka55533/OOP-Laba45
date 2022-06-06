package ledza.plugin;

import org.apache.commons.codec.DecoderException;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface Plugin {

    String encode(String inputText);

    String decode(String outputText) throws DecoderException;

    String getSignature();


    static List<Plugin> getServices(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, Plugin.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
