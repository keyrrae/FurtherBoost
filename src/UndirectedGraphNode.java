import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuanwang on 10/29/16.
 */
public class UndirectedGraphNode {
    int label;

    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}
