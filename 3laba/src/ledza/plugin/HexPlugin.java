package ledza.plugin;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HexPlugin implements Plugin {
    @Override
    public String encode(String inputText) {
        return new String(Hex.encodeHex(inputText.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decode(String outputText)  {
        try {
            return new String(Hex.decodeHex(outputText.toCharArray()), StandardCharsets.UTF_8);
        } catch (DecoderException e) {
            throw new RuntimeException("Error decode hex from file");
        }
    }

    @Override
    public String getSignature() {
        return "hex";
    }
}
