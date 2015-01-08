package ps.expressionsequivalence.comparator;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.tree.DefaultMutableTreeNode;
import ps.expressionsequivalence.parser.Identifier;
import ps.expressionsequivalence.parser.Operator;
import ps.expressionsequivalence.parser.Token;

/**
 * 
 * @author Худяков Павел
 */
public class Comparator {
    public HashMap<Character, Character> compare(DefaultMutableTreeNode leftTree, DefaultMutableTreeNode rightTree) throws CloneNotSupportedException {

        // Сравнить деревья на эквивалентность без учета листьев собрав информацию о возможном соответствии листьев.
        correspondences = compare_rec(leftTree, rightTree, false);

        if(correspondences != null) {
            System.out.println(correspondences);
        }

        return null;
    }

    /*
     * Для правого дерева заполняется просто упорядоченная коллекция уникальных идентификаторов.
     */
    List<Character> rightLeafsInfo = null;

    /*
     * Вспомогательные коллеции листовых узлов деревьев.
     */
    List<DefaultMutableTreeNode> leftLeafs = null;
    List<DefaultMutableTreeNode> rightLeafs = null;

    /*
     * Ассоциативный массив "Идентификатор-возможные соответствия". Возвращается рекурсивной процедурой compare_rec
     */
    List<CharToCharArray> correspondences = null;

    /*
     * Рекурсивная процедура сравнения двух деревьев выражений. Работает в двух режимах. "Точное" сравнение и "Поиск возможных соответствий".
     * В режиме поиска возможных соответствий процедура возвращает ассоциативный массив возможных соответствий идентификаторов для дальнейшего
     * перебора.
     * В режиме точного сравнения происходит сравнение деревьев на полное совпадение идентификаторов. В этом режиме возвращается пустой
     * ассоциативный массив.
     * В любом режиме если выражения не совпадают возвращается null.
     */
    private List<CharToCharArray> compare_rec(DefaultMutableTreeNode leftTree, DefaultMutableTreeNode rightTree, boolean exact) {
        assert leftTree.getUserObject() instanceof Token && rightTree.getUserObject() instanceof Token;

        Token leftToken = (Token) leftTree.getUserObject();
        Token rightToken = (Token) rightTree.getUserObject();
        
        if(leftToken instanceof Operator && rightToken instanceof Operator) {
            assert leftTree.getChildCount() == 2 && rightTree.getChildCount() == 2;

            Operator leftOperator = (Operator) leftToken;
            Operator rightOperator = (Operator) rightToken;

            if(leftOperator.equals(rightOperator)) {
                DefaultMutableTreeNode leftTree0 = (DefaultMutableTreeNode)leftTree.getChildAt(0);
                DefaultMutableTreeNode rightTree0 = (DefaultMutableTreeNode)rightTree.getChildAt(0);
                DefaultMutableTreeNode leftTree1 = (DefaultMutableTreeNode)leftTree.getChildAt(1);
                DefaultMutableTreeNode rightTree1 = (DefaultMutableTreeNode)rightTree.getChildAt(1);

                if(rightOperator.isCommutative()) {
                    List<CharToCharArray> dc0 = null;
                    dc0 = compare_rec(leftTree0, rightTree0, exact);
                    List<CharToCharArray> dc1 = null;
                    dc1 = compare_rec(leftTree1, rightTree1, exact);
                    List<CharToCharArray> dc = null;
                    if(dc0 != null && dc1 != null) {
                        dc = new LinkedList<CharToCharArray>();
                        dc.addAll(dc0);
                        dc.addAll(dc1);
                    }

                    List<CharToCharArray> rc0 = null;
                    rc0 = compare_rec(leftTree0, rightTree1, exact);
                    List<CharToCharArray> rc1 = null;
                    rc1 = compare_rec(leftTree1, rightTree0, exact);
                    List<CharToCharArray> rc = null;
                    if(rc0 != null && rc1 != null) {
                        rc = new LinkedList<CharToCharArray>();
                        rc.addAll(rc0);
                        rc.addAll(rc1);
                    }

                    if ((dc != null) && (rc != null)) {
                        dc.addAll(rc);
                        return dc;
                    } else {
                        if (dc != null) {
                            return dc;
                        } else {
                            if (rc != null) {
                                return rc;
                            } else {
                                return null;
                            }
                        }
                    }
                } else {
                    List<CharToCharArray> dc0 = null;
                    dc0 = compare_rec(leftTree0, rightTree0, exact);
                    List<CharToCharArray> dc1 = null;
                    dc1 = compare_rec(leftTree1, rightTree1, exact);
                    List<CharToCharArray> dc = new LinkedList<CharToCharArray>();
                    if((dc0 != null) && (dc1 != null)) {
                        dc.addAll(dc0);
                        dc.addAll(dc1);
                        return dc;
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        } else {
            if(leftToken instanceof Identifier && rightToken instanceof Identifier) {
                Identifier leftIdentifier = (Identifier) leftToken;
                Identifier rightIdentifier = (Identifier) rightToken;

                List<CharToCharArray> result = new LinkedList<CharToCharArray>();
                if(!exact) {
                    Set<Character> l = new HashSet<Character>();
                    l.add(rightIdentifier.getValue());
                    result.add(new CharToCharArray(leftIdentifier.getValue(), l));
                    return result;
                } else {
                    if(leftIdentifier.equals(rightIdentifier)) {
                        return result;
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        }
    }

    /**
     * Следующая перестановка элементов последовательности rightLeafsInfo
     * @return Истина если следующая перестановка существует, ложь в противном случае.
     */
    boolean ScrambleRightLeafsInfo() {
        int N = rightLeafsInfo.size() - 1;
        List<Character> X = rightLeafsInfo;
        int i = N - 1;

        //поиск i
        while ((i >= 0) && (X.get(i) > X.get(i + 1))) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = i + 1;

        //{поиск j}
        while ((j < N) && (X.get(j + 1) > X.get(i))) {
            j++;
        }

        Collections.swap(X, i, j);
        for (j = i + 1; j <= (N + i) / 2; j++) {
            Collections.swap(X, j, N - j + i + 1);
        }
        return true;
    }

    /**
     * Предикат сравнения двух листовых узлов-идентификаторов.
     */
    static final java.util.Comparator<DefaultMutableTreeNode> LEAFS_ORDER =
                                 new java.util.Comparator<DefaultMutableTreeNode>() {
        public int compare(DefaultMutableTreeNode n1, DefaultMutableTreeNode n2) {
            assert n1.getUserObject() instanceof Identifier && n2.getUserObject() instanceof Identifier;
            Identifier i1 = (Identifier) n1.getUserObject();
            Identifier i2 = (Identifier) n2.getUserObject();
            if(i1.getValue() > i2.getValue()) {
                return 1;
            } else {
                if(i1.getValue() < i2.getValue()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    };

}