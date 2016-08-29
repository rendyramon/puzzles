import java.util.*;
public class GasStations {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        Map<Integer, Integer> unpaidCost = new HashMap<Integer, Integer>();
        int currentGas = 0;
        int totalGas = 0;
        int lastUnpaid = -1;
        int start = 0;
        for(int i = 0; i < gas.length; i++){
            currentGas += gas[i];
            currentGas -= cost[i];

            totalGas  += gas[i];
            totalGas  -= cost[i];
            if(currentGas < 0){
                unpaidCost.put(i, totalGas);
                lastUnpaid = i;
                currentGas = 0;
                start = i+1;
            }
        }
        return lastUnpaid == -1? 0 :
            currentGas + unpaidCost.get(lastUnpaid) >= 0? start : -1;
    }

    // [99,98,97,100]
    // [100,100,100,97]
    public static void main(String[] args){
        GasStations gasStations = new GasStations();
        System.out.println(gasStations.canCompleteCircuit(new int[]{99,98,97,100}, new int[]{100,100,100,97}));
    }
}