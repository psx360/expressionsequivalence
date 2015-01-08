package ps.expressionsequivalence.comparator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Худяков Павел
 */
class Groups {

    static Groups createGroups(Paths paths) {
        paths.sort();
        Iterator i = paths.list.iterator();
        Path pre = null;
        Group group = new Group();
        Groups result = new Groups();

        while(i.hasNext()) {
            Path p = (Path) i.next();
            

            if(pre != null) {
                if(!pre.getLeafValue().equals(p.getLeafValue())) {
                    result.list.add(group);
                    group = new Group();
                }
            } 

            group.list.add(p);

            pre = p;
        }
        if(!group.list.isEmpty()) {
            result.list.add(group);
        }

        return result;
    }

    static boolean isEquivalent(Groups leftGroups, Groups rightGroups) {
        Iterator l = leftGroups.list.iterator();
        HashMap<Group, Boolean> e = new HashMap<Group, Boolean>();

        while(l.hasNext()) {
            Group lGroup = (Group) l.next();
            Iterator r = rightGroups.list.iterator();
            boolean isEquivalentFound = false;

            while(r.hasNext()) {
                Group rGroup = (Group) r.next();

                if(!e.containsKey(rGroup)) {
                    if(lGroup.isEquivalent(rGroup)) {
                        e.put(rGroup, true);
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

    private static Groups createGroups(Group group) {
        Groups groups = new Groups();
        groups.list.add(group);
        return groups;
    }

    private List<Group> list = new LinkedList<Group>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Iterator i = list.iterator();
        while(i.hasNext()) {
            Group g = (Group) i.next();
            sb.append(String.format("%s", g.toString()));
        }

        sb.append("}");
        return sb.toString();
    }

}
