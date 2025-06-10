import java.util.ArrayList;

/**
 * A java implementation of the K-dimensional Tree.
 * Tree is assumed to be two-dimensional due to the game also being two-dimensional.
 * Meant for fast searches on a possibly dynamic amount of elements.
 * @author Pavarasan Karunainathan
 */
public class KdTree{
    private class BinaryTree{
        private Thing thing;
        private BinaryTree left;
        private BinaryTree right;
    
        public BinaryTree(Thing thing){
            this.thing = thing;
            this.left = null;
            this.right = null;
        }

        public double getX(){
            return this.thing.getPosition().getX();
        }

        public double getY(){
            return this.thing.getPosition().getY();
        }

        public Thing getThing(){
            return this.thing;
        }
    }

    private BinaryTree root;

    private void insertHelper(BinaryTree node, BinaryTree toInsert, boolean splitOnX){
        if(splitOnX){ // split on x
            if(toInsert.getX() < node.getX()){
                if(node.left == null){
                    node.left = toInsert;
                    return;
                }else{
                    insertHelper(node.left, toInsert, !splitOnX);
                }
            }else{
                if(node.right == null){
                    node.right = toInsert;
                    return;
                }else{
                    insertHelper(node.right, toInsert, !splitOnX);
                }
            }
        }else{ // split on y
            if(toInsert.getY() < node.getY()){
                if(node.left == null){
                    node.left = toInsert;
                    return;
                }else{
                    insertHelper(node.left, toInsert, !splitOnX);
                }
            }else{
                if(node.right == null){
                    node.right = toInsert;
                    return;
                }else{
                    insertHelper(node.right, toInsert, !splitOnX);
                }
            }
        }
    }
    
    private void insert(Thing thing){
        if(this.root == null){
            this.root = new BinaryTree(thing);
        }else{
            insertHelper(this.root, new BinaryTree(thing), true);
        }
    }
    
    private boolean inRadius(Position position, BinaryTree node, double radius){
        double deltaX = node.getX() - position.getX();
        double deltaY = node.getY() - position.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY) <= radius;
    }

    private ArrayList<Thing> foundThings;

    private void searchHelper(BinaryTree node, Position toFind, double searchRadius, boolean splitOnX){
        if(node == null){
            return;
        }
        if(inRadius(toFind, node, searchRadius)){
            foundThings.add(node.getThing());
        }

        if(splitOnX){ // split on x
            if(toFind.getX() < node.getX()){
                searchHelper(node.left, toFind, searchRadius, !splitOnX);
            }else{
                searchHelper(node.right, toFind, searchRadius, !splitOnX);
            }
        }else{ // split on y
            if(toFind.getY() < node.getY()){
                searchHelper(node.left, toFind, searchRadius, !splitOnX);
            }else{
                searchHelper(node.right, toFind, searchRadius, !splitOnX);
            }
        }
    }

    /**
     * Searches the k-dimensional tree with a radius around a certain position.
     * @param position The position to search from.
     * @param searchRadius The radius to search at.
     * @return An ArrayList of Things found in the radius around the position.
     */
    public ArrayList<Thing> search(Position position, double searchRadius){
        foundThings.clear();
        searchHelper(this.root, position, searchRadius, true);
        return foundThings;
    }

    /**
     * Constructs the k-dimensional Tree.
     * @param things The ArrayList of things to construct the tree with.
     */
    public KdTree(ArrayList<Thing> things){
        this.root = null;
        for(Thing thing : things){
            insert(thing);
        }
        this.foundThings = new ArrayList<Thing>();
    }
}