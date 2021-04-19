import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RC4 {
    private static State actualState;

    public static String encrypt(String message, String key){
        List<String> KeyBlocks = binaryToBlocksArray(key, 8);
        // Init state
        actualState = new State(KeyBlocks);

        StringBuilder encryptedMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        for (char aChar : chars) {
            char encryptedChar = (char) (aChar ^ actualState.trans());
            encryptedMessage.append(encryptedChar);
        }
        return encryptedMessage.toString();
    }

    public static String decrypt(String message, String key){
        List<String> KeyBlocks = binaryToBlocksArray(key, 8);
        // Init state
        actualState = new State(KeyBlocks);

        StringBuilder encryptedMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        for (char aChar : chars) {
            char encryptedChar = (char) (aChar ^ actualState.trans());
            encryptedMessage.append(encryptedChar);
        }
        return encryptedMessage.toString();
    }

    public static int getSecondByte(String key) {
        List<String> KeyBlocks = binaryToBlocksArray(key, 8);
        // Init state
        actualState = new State(KeyBlocks);
        // first byte
        actualState.trans();
        //return second byte
        return actualState.trans();
    }


    public static List<String> binaryToBlocksArray(String binary, int blockSize) {

        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }
        return result;
    }


}
