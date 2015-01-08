package ps.expressionsequivalence.lexer;

/**
 *
 * @author Худяков Павел
 */
public class Identifier extends Token {
    private final String identifier;

    public Identifier(String identifier) {
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
        if ((this.identifier == null) ? (other.identifier != null) : !this.identifier.equals(other.identifier)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.identifier != null ? this.identifier.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return identifier;
    }

}
