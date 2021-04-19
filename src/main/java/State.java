import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {
    private int I, J;
    private List<Integer> S;

    public State(List<String> KeyBlocks) {
        I = 0;
        J = 0;

        int j = 0;
        S = new ArrayList<>();
        //S0 - identity permutation
        for(int i = 0; i < 256; ++i){
            S.add(i);
        }
        // initial permutation
        for(int i = 0; i < 255; ++i){
            int K = Integer.parseInt(KeyBlocks.get(i % KeyBlocks.size()), 2);
            j = (j + S.get(i) + K ) % 256;
            //swap
            Collections.swap(S, i, j);
        }

    }

    public int trans() {
        I = (I + 1) % 256;
        J = (J + S.get(I)) % 256;
        Collections.swap(S, I, J);
        return S.get((S.get(I) + S.get(J)) % 256);
    }
}
