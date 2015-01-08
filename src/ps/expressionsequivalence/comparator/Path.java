package ps.expressionsequivalence.comparator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import ps.expressionsequivalence.parser.Identifier;
import ps.expressionsequivalence.parser.Operator;
import ps.expressionsequivalence.parser.Token;

/**
 *
 * @author Худяков Павел
 */
class Path implements Comparable<Path>{

    public Path(DefaultMutableTreeNode leaf) {
        assert leaf.isLeaf();
        DefaultMutableTreeNode node = leaf;

        while(!node.isRoot()) {
            assert node.getUserObject() instanceof Token;

            list.add((Token) node.getUserObject());
            node = (DefaultMutableTreeNode) node.getParent();
        }

        list.add((Token) node.getUserObject());
    }

    List<Token> list = new LinkedList<Token>();

    public int compareTo(Path p) {
        return this.getLeafValue().compareTo(p.getLeafValue());
    }

    public String getLeafValue() {
        assert list.get(0) instanceof Identifier;
        return list.get(0).toString();
    }

    boolean isEquivalent(Path other) {
        if(this.list.size() != other.list.size()) {
            return false;
        }
        Iterator t = this.list.iterator();
        Iterator o = other.list.iterator();
        t.next(); // start with second node, first node is leaf
        o.next();

        while(t.hasNext() && o.hasNext()) {
            Operator tOperator = (Operator) t.next();
            Operator oOperator = (Operator) o.next();
            if(!tOperator.toString().equals(oOperator.toString())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        Iterator i = list.iterator();
        while(i.hasNext()) {
            Token t = (Token) i.next();
            sb.append(String.format("%s ", t.toString()));
        }

        sb.append(")");
        return sb.toString();
    }

}
