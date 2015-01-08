package ps.expressionsequivalence.comparator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Худяков Павел
 */
class Group {

    static Group createGroup(Path path) {
        Group group = new Group();
        group.list.add(path);
        return group;
    }

    public List<Path> list = new LinkedList<Path>();

    boolean isEquivalent(Group other) {
        Iterator t = this.list.iterator();
        HashMap<Path, Boolean> e = new HashMap<Path, Boolean>();

        while(t.hasNext()) {
            Path tPath = (Path) t.next();
            Iterator o = other.list.iterator();
            boolean isEquivalentFound = false;

            while(o.hasNext()) {
                Path oPath = (Path) o.next();

                if(!e.containsKey(oPath)) {
                    if(tPath.isEquivalent(oPath)) {
                        e.put(oPath, true);
                        isEquivalentFound = true;
                        break;
                    }
                }
            }

            if(!isEquivalentFound) {
                return false;
            }
        }

        return true;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator i = list.iterator();
        while(i.hasNext()) {
            Path p = (Path) i.next();
            sb.append(String.format("%s", p.toString()));
        }

        sb.append("]");
        return sb.toString();
    }

}
