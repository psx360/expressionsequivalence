package ps.expressionsequivalence.comparator;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Худяков Павел
 */
class Paths {

    static Paths createPaths(DefaultMutableTreeNode node) {
        Paths result = new Paths();

        if(node.isLeaf()) {
            result.merge(Paths.createPaths(new Path(node)));
        }  else {

            for(int i = 0; i < node.getChildCount(); i++) {
                Paths childPaths = createPaths((DefaultMutableTreeNode) node.getChildAt(i));
                result.merge(childPaths);
            }
        }

        return result;
    }

    private void merge(Paths paths) {
         Iterator iterator = paths.list.iterator();

         while(iterator.hasNext()) {
             list.add((Path) iterator.next());
         }
    }

    private static Paths createPaths(Path path) {
        Paths result = new Paths();
        result.list.add(path);
        return result;
    }

    public void sort() {
        Collections.sort(list);
    }

    List<Path> list = new LinkedList<Path>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Iterator i = list.iterator();
        while(i.hasNext()) {
            Path p = (Path) i.next();
            sb.append(String.format("%s ", p.toString()));
        }

        sb.append("}");
        return sb.toString();
    }
}
