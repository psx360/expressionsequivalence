package ps.expressionsequivalence.comparator;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Худяков Павел
 */
class CharToCharArray {
    public Character character = '-';
    public Set<Character> charArray = new HashSet<Character>();

    CharToCharArray(char value, Set<Character> l) {
        this.character = value;
        this.charArray = l;
    }

    @Override
    public String toString() {
        return "[" + character + "->" + charArray + "]";
    }


}
