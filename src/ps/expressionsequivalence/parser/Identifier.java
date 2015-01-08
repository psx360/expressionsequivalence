package ps.expressionsequivalence.parser;

/**
 *
 * @author Худяков Павел
 */
public class Identifier extends Token {
    private final char identifier;

    public Identifier(char identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Identifier other = (Identifier) obj;
        if (this.identifier != other.identifier) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.identifier;
        return hash;
    }

  

    @Override
    public String toString() {
        return Character.toString(identifier);
    }

    public char getValue() {
        return identifier;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Identifier(this.identifier);
    }


}
