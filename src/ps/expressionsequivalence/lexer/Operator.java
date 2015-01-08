package ps.expressionsequivalence.lexer;

/**
 *
 * @author Худяков Павел
 */
public class Operator extends Token {

    public Operator(String sign) {
        assert sign.equals("+") || sign.equals("-") || sign.equals("*") || sign.equals("/");

        this.sign = sign.charAt(0);
    }

    @Override
    public String toString() { return Character.toString(sign); }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Operator other = (Operator) obj;
        if (this.sign != other.sign) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.sign;
        return hash;
    }



    private char sign = 0;

}
