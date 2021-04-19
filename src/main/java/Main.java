import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

    }

    public static void testRC4(){
        int randomLength = 5 + (int)(Math.random() * 10) % 12;
        StringBuilder randomKey = new StringBuilder();
        for(int i = 0; i < randomLength * 8; ++i){
            randomKey.append(getRandomBit());
        }
        String encryptedMessage = RC4.encrypt("se pare ca merge", randomKey.toString());
        String decryptedMessage = RC4.decrypt(encryptedMessage, randomKey.toString());

        System.out.println("encrypted: " + encryptedMessage);
        System.out.println("decrypted: " + decryptedMessage);
    }

    public static void testASG() {
        StringBuilder randomKey = new StringBuilder();
        for(int i = 0; i < 3 * 128; i++){
            randomKey.append(getRandomBit());
        }
        ASG asg = new ASG(randomKey.toString());
        System.out.println(asg.GenerateKeyStream(1024));

        //execution time test
    /*    long avg = 0;
        for(int i = 0; i < 30; ++i){
            long startTime = System.nanoTime();
            asg.GenerateKeyStream(1024);
            long elapsedTime = System.nanoTime() - startTime;
            avg += elapsedTime;
        }
        avg = avg / 30;
        System.out.println(avg);*/

    }


    public static void RC4SecondByte(){
        List<Integer> frecuency = new ArrayList<>(Collections.nCopies(256, 0));
        int loopNumbers = 30000;
        for(int j = 0; j < loopNumbers; ++j){
            int randomLength = 5 + (int)(Math.random() * 10) % 12;
            StringBuilder randomKey = new StringBuilder();
            for(int i = 0; i < randomLength * 8; ++i){
                randomKey.append(getRandomBit());
            }

            int  obtainedByte = RC4.getSecondByte(randomKey.toString());
            frecuency.set(obtainedByte, frecuency.get(obtainedByte) + 1);
        }
        try{
            FileWriter biasWriter = new FileWriter("RC4_bias");
            for(int i = 0; i < 256; i++) {
                float avg = (float) frecuency.get(i) / loopNumbers;
                String toWrite = i + ": " + avg  + "\n";
                biasWriter.write(toWrite);
            }
            biasWriter.close();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getRandomBit(){
        return (int)(Math.round(Math.random()*10)) % 2;
    }


}




