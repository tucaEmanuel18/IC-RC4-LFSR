import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        testASG();
    }

    public static void testASG(){
        StringBuilder randomKey = new StringBuilder();
        for(int i = 0; i < 3 * 128; i++){
            randomKey.append(getRandomBit());
        }
        ASG asg = new ASG(randomKey.toString());
        System.out.println(asg.GenerateKeyStream(1024));
    }

    public static int getRandomBit(){
        return (int)(Math.round(Math.random()*10)) % 2;
    }


}
