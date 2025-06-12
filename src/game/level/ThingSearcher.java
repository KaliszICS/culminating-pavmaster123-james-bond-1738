package game.level;
import java.util.ArrayList;

/**
 * A data structure made to efficiently search an array of Things by Position.
 */
public class ThingSearcher{
    private ArrayList<Thing> things;
    private Thing[] thingsArray;
    private boolean sortByX;

    private boolean lessThan(Thing left, Thing right){
        if(this.sortByX){
            return left.getPosition().getX() < right.getPosition().getX();
        }else{
            return left.getPosition().getY() < right.getPosition().getY();
        }
    }

    private double getValue(Thing thing){
        return this.sortByX ? thing.getPosition().getX() : thing.getPosition().getY();
    }

    private Thing[] merge(Thing[] left, Thing[] right){
        Thing[] toReturn = new Thing[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        while(true){
            if(leftIndex == left.length){
                for(;rightIndex < right.length; rightIndex++){
                    toReturn[leftIndex + rightIndex] = right[rightIndex];
                }
                break;
            }
            if(rightIndex == right.length){
                for(;leftIndex < left.length; leftIndex++){
                    toReturn[leftIndex + rightIndex] = left[leftIndex];
                }
                break;
            }

            if(lessThan(left[leftIndex], right[rightIndex])){
                toReturn[leftIndex + rightIndex] = left[leftIndex++];
            }else{
                toReturn[leftIndex + rightIndex] = right[rightIndex++];
            }
        }
        return toReturn;
    }

    private Thing[] mergeSort(int lower, int upper){
        if(upper <= lower){
            return new Thing[]{things.get(lower)};
        }
        int mid = (lower+upper)/2;
        Thing[] left = mergeSort(lower, mid);
        Thing[] right = mergeSort(mid+1, upper);
        return merge(left, right);
    }

    private int lowerBound(double coord){
        int bound = thingsArray.length-1;
        for(int low = 0, high = bound, mid = bound/2; low <= high; mid = (low + high)/2){
            if(getValue(this.thingsArray[mid]) >= coord){
                bound = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return bound;
    }

    private int upperBound(double coord){
        int bound = thingsArray.length-1;
        for(int low = 0, high = bound, mid = bound/2; low <= high; mid = (low + high)/2){
            if(getValue(this.thingsArray[mid]) <= coord){
                bound = mid;
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return bound;
    }

    /**
     * Searches a Position around a given radius for Things.
     * @param position The Position to search around.
     * @param searchRadius The radius to search around the Position.
     * @return An ArrayList of all Things that are within the radius around the Position.
     */
    public ArrayList<Thing> search(Position position, double searchRadius){
        int lower;
        int upper;
        if(this.sortByX){
            lower = lowerBound(position.getX() - searchRadius);
            upper = upperBound(position.getX() + searchRadius);
        }else{
            lower = lowerBound(position.getY() - searchRadius);
            upper = upperBound(position.getY() + searchRadius);
        }

        ArrayList<Thing> thingsInBounds = new ArrayList<Thing>();
        for(int i = lower; i <= upper; i++){
            thingsInBounds.add(this.thingsArray[i]);
        }

        return thingsInBounds;
    }

    /**
     * The constructor of the ThingSearcher.
     * @param things The ArrayList of Things to search.
     * @param sortByX Determines if the Array is sorted by the X-axis or Y-axis.
     */
    public ThingSearcher(ArrayList<Thing> things, boolean sortByX){
        this.things = things;
        this.sortByX = sortByX;
        this.thingsArray = mergeSort(0, things.size()-1);
    }
}