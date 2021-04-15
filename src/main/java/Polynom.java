import java.util.ArrayList;
import java.util.List;

public class Polynom {
    private List<Integer> notNullPositions;

    public Polynom(List<Integer> notNullPositions){
        if(notNullPositions == null){
            throw new NullPointerException();
        }
        if(notNullPositions.size() < 2){
            throw new IllegalArgumentException("Polinomul trebuie sa aiba macar doua pozitii nenule!");
        }
        this.notNullPositions = new ArrayList<>();

        boolean firstPosition = true;
        for(Integer i : notNullPositions){

            if(firstPosition){
                firstPosition = false;
            }else{
                if(this.notNullPositions.get(this.notNullPositions.size() - 1) <= i){
                    throw new IllegalArgumentException("Pozitiile polinomului trebuie sa fie descrescatoare!");
                }
            }
            this.notNullPositions.add(i);
        }
    }

    public List<Integer> getNotNullPositions() {
        return notNullPositions;
    }

    public int apply(List<Integer> input){

        int result = input.get(notNullPositions.get(0)) ^ input.get(notNullPositions.get(1));
        for(int i = 2; i < notNullPositions.size(); ++i){
            result = result ^ input.get(notNullPositions.get(i));
        }
        return result;
    }
}
