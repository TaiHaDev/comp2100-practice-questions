import org.w3c.dom.Node;
import parcel.Parcel;

import java.util.*;

public class ParcelBST extends BinarySearchTree<Parcel> implements Iterable<Parcel>  {

    public ParcelBST(Parcel value, ParcelBST leftNode, ParcelBST rightNode) {
        super(value, leftNode, rightNode);
    }

    public ParcelBST(BinarySearchTree<Parcel> parcelBST) {
        super(parcelBST.value,parcelBST.leftNode,parcelBST.rightNode);
    }

    public ParcelBST(Tree<Parcel> parcelTree) {
        super(parcelTree.value,parcelTree.leftNode,parcelTree.rightNode);
    }

    public ParcelBST(Parcel value) {
        super(value);
    }


    @Override
    public Iterator<Parcel> iterator() {
        return new IteratorPreOrder();
    }

    class IteratorPreOrder implements Iterator<Parcel> {
        // Hint: value, left/right sub-trees can be accessed by:
        //    ParcelBST.this.value    ParcelBST.this.leftNode   ParcelBST.this.rightNode
        // Or equivalently directly:
        //    value   leftNode    rightNode
        Stack<Tree<Parcel>> stack = new Stack<>();
        // You may add methods and variables here if you wish
        public IteratorPreOrder() {
            stack.add(ParcelBST.this);
        };

        @Override
        public boolean hasNext() {
            // TODO
            // START YOUR CODE
            return !stack.isEmpty();
            // END YOUR CODE
        }
        @Override
        public Parcel next() {
            // TODO
            // START YOUR CODE
            if (stack.isEmpty()) throw new NoSuchElementException();
            Tree<Parcel> currentNode = stack.pop();
            if(currentNode.rightNode != null && !(currentNode.rightNode instanceof EmptyTree)) {
                stack.push(currentNode.rightNode);
            }

            if(currentNode.leftNode != null && !(currentNode.leftNode instanceof EmptyTree)) {
                stack.push(currentNode.leftNode);
            }
            return currentNode.value;
            // END YOUR CODE
        }
    }
}
