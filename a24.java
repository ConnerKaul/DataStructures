import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.IOException;

//Lab to see if change can be made for an amount owed given coin sizes 
public class a24 {
    public static void main(String[] args) throws NumberFormatException, IOException {

        //Represent the coin sizes in a heap ADT using the PriorityQueue or the PriorityBlockingQueue class. Make the heap independent of the application by using class IntegerCompare, implementing Comparator.
        PriorityQueue<Integer> coinSize = new PriorityQueue<>(new IntegerCompare());
        
        //Represent the change in a set ADT using the HashMap or the TreeMap class.
        Map<Integer, Integer> change = new HashMap<>();
        
        //enter coin sizes and the amount owed
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the coin size: ");
        String input = reader.readLine();
        Scanner scanner = new Scanner(input);

        while(scanner.hasNextInt()) {
            coinSize.add(scanner.nextInt());
        }

        System.out.println("Enter amount owed: ");
        int amountOwed = Integer.parseInt(reader.readLine());

        //Change counter.
        int changeAmount = 0;
        
        while(!coinSize.isEmpty() && changeAmount < amountOwed) {
            int coin = coinSize.peek();

            if(changeAmount + coin <= amountOwed) {
                changeAmount += coin;
                
                if(change.containsKey(coin)){
                  int newQuantity = change.get(coin);
                  newQuantity += 1;
                  change.put(coin, newQuantity);          
                }else{
                  change.put(coin, 1);
                }
            }else{
               int throwAway = coinSize.poll();
            }
        }
        if(changeAmount == amountOwed){
           System.out.println("Change: " + change);
        }else{
           System.out.println("Change is not possible with given coins");
        }
    }

}

class IntegerCompare implements Comparator<Integer> {

    @Override
    public int compare(Integer a1, Integer a2) {
        return a2.compareTo(a1);
    }

}
