import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ASG {
    private LFSR lfsr0, lfsr1, lfsr2;

    public ASG(String key){
        if(key == null){
            throw new NullPointerException("The key string is null");
        }
        if(key.length() < 384){
            throw new IllegalArgumentException("The key string length must be greater than 387");
        }

        List<Integer> initialState0 = new ArrayList<>();
        List<Integer> initialState1 = new ArrayList<>();
        List<Integer> initialState2 = new ArrayList<>();

        for(int i = 0; i < 128; i++){
            initialState0.add(key.charAt(i) - '0');
        }

        // 225 - 128 = 127
        for(int i = 128; i < 255; i++){
            initialState1.add(key.charAt(i) - '0');
        }

        // 384 - 255 = 129
        for(int i = 255; i < 384; i++){
            initialState2.add(key.charAt(i) - '0');
        }

        // X^120 + X^118 + X^111 + X^7 + 1
        Polynom p0 = new Polynom(Arrays.asList(119, 117, 110, 6, 0));
        // X^116 + X^71 + X^70 + X + 1
        Polynom p1 = new Polynom(Arrays.asList(115, 70, 69, 1, 0));
        // X^125 + X^108 + X^107 + X + 1
        Polynom p2 = new Polynom(Arrays.asList(124, 107, 106, 1, 0));

        lfsr0 = new LFSR(initialState0, p0);
        lfsr1 = new LFSR(initialState1, p1);
        lfsr2 = new LFSR(initialState2, p2);
    }

    public String GenerateKeyStream(int length){
        StringBuilder keyStream = new StringBuilder();

        int currentLFSR0, currentLFSR1 = 0, currentLFSR2 = 0;

        for(int i = 0; i < length; ++i){
            currentLFSR0 = lfsr0.clock();
            if(currentLFSR0 == 1){
                currentLFSR1 = lfsr1.clock();
            }else{
                currentLFSR2 = lfsr2.clock();
            }
            int outBit = currentLFSR1 ^ currentLFSR2;
            keyStream.append(outBit);
        }
        return keyStream.toString();
    }


}
