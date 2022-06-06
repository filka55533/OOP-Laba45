package ledza.foreign_plugins;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.BinaryCodec;

public class BinaryArchiver {
    public String getArchiveType() {
        return "Binary archiving";
    }

    public byte[] archiveData(byte[] data) throws EncoderException {
        BinaryEncoder binaryEncoder = new BinaryCodec();

        return binaryEncoder.encode(data);
    }

    public byte[] decodeData(byte[] binData) throws DecoderException {
        BinaryDecoder binaryDecoder = new BinaryCodec();
        return binaryDecoder.decode(binData);
    }
}
