import java.util.ArrayList;
import java.util.List;

public class LFSR {
    private List<Integer> actualState;
    private Polynom polynom;

    public LFSR(List<Integer> initialState, Polynom polynom){
        if(initialState.size() <= polynom.getNotNullPositions().get(0)){
            throw new IllegalArgumentException("The polynom order is to big for this initial state");
        }

        this.actualState = new ArrayList<>(initialState);
        this.polynom = polynom;
    }

    public int clock(){
        int result = polynom.apply(actualState);
        int outBit = actualState.get(0);
        actualState.remove(0);
        actualState.add(result);
        return outBit;
    }
}