package ledza.plugin;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Plugin implements Plugin {
    @Override
    public String encode(String inputText) {
        return Base64.getEncoder().encodeToString(inputText.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String decode(String outputText) {
        return new String(Base64.getDecoder().decode(outputText), StandardCharsets.UTF_8);
    }

    @Override
    public String getSignature() {
        return "base64";
    }

}
