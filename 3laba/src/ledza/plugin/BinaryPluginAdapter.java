package ledza.plugin;

import ledza.foreign_plugins.BinaryArchiver;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BinaryPluginAdapter implements Plugin{

    BinaryArchiver binaryArchiver;

    public BinaryPluginAdapter() {
        this.binaryArchiver = new BinaryArchiver();
    }

    @Override
    public String encode(String inputText) {
        try {
            return new String(binaryArchiver.archiveData(inputText.getBytes(StandardCharsets.UTF_8)));
        }
        catch (EncoderException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String decode(String outputText)  {
        try {
            return new String(binaryArchiver.decodeData(outputText.getBytes(StandardCharsets.UTF_8)));
        }
        catch (DecoderException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getSignature() {
        return "binary";
    }
}
