
import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {
    private Tokenizer tokenizer;
    private RBTree<Integer, List<String>> rbTree;

    public RBTree<Integer, List<String>> build(String text) {
        // ########## YOUR CODE STARTS HERE ##########
        tokenizer = new MyTokenizer(text);
        rbTree = new RBTree<>();
        while (tokenizer.hasNext()) {
            Token cur = tokenizer.current();
            if (cur.getType() == Token.Type.UPPER_CASE_WORD) {
                Node<Integer, List<String>> searchNode = rbTree.search(cur.getValue().length());
                if (searchNode != null) searchNode.data.add(cur.getValue());
                else {
                    List<String> li = new ArrayList<>();
                    li.add(cur.getValue());
                    rbTree.insert(cur.getValue().length(), li);
                }
            }
            tokenizer.next();
        }
        return rbTree;
    }




}
