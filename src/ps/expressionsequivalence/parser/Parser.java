package ps.expressionsequivalence.parser;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author Худяков Павел. Синтаксический и лексический анилизатор выполнены в одном модуле т. к. лексика ограничена
 * только английскими буквами, их распознавание не требуется выносить в отдельный модуль.
 * Грамматика:
 * E->  T[E']
 * E'-> +|-T[E']
 * T->  F[T']
 * T'-> +|-[T']
 * F->  l|(E)
 * где l это терминальный символ - буква английского алфавита
 * E-expr
 * E' - expr_rest
 * T - term
 * T' - term_rest
 * F - factor
 */
public class Parser {
    public TreeModel parse(String expression) throws SyntaxException {
        input = expression;
        pos = 0;
        treeModel = new DefaultTreeModel(new DefaultMutableTreeNode());
        DefaultMutableTreeNode exprNode = expr();
        if(!eoln()) {
            throw new SyntaxException("Unexpected symbols after end of expression.");
        }
        treeModel.setRoot(exprNode);
        return treeModel;

    }

    /*
     * Синтаксическое дерево.
     */
    private DefaultTreeModel treeModel = null;

    /**
     * Входная последовательность.
     */
    private static String input;

    /**
     * Текущее положение во входной последовательности.
     */
    private static int pos;

    private DefaultMutableTreeNode expr() throws SyntaxException {
        DefaultMutableTreeNode resultNode = null;
        DefaultMutableTreeNode termNode = term();

        if (check('-') || check('+')) {
            DefaultMutableTreeNode newExprRestNode = expr_rest(termNode);
            resultNode = newExprRestNode;
        } else {
            resultNode = termNode;
        }

        return resultNode;
    }

    private DefaultMutableTreeNode expr_rest(DefaultMutableTreeNode a) throws SyntaxException {
        DefaultMutableTreeNode resultNode = null;
        DefaultMutableTreeNode exprRestNode = new DefaultMutableTreeNode();
        treeModel.insertNodeInto(a, exprRestNode, exprRestNode.getChildCount());

        switch (current()) {
            case ('-'):
            case ('+'): {
                exprRestNode.setUserObject(new Operator(current()));
                next();
                DefaultMutableTreeNode newTermNode = term();
                treeModel.insertNodeInto(newTermNode, exprRestNode, exprRestNode.getChildCount());

                if (check('-') || check('+')) {
                    DefaultMutableTreeNode nestedExprRestNode = expr_rest(exprRestNode);
                    resultNode = nestedExprRestNode;
                } else {
                    resultNode = exprRestNode;
                }
            }
            break;
            default: {
                throw new SyntaxException(String.format("+|- expected but '%c' found.", current()));
            }
        }

        return resultNode;
    }

    private DefaultMutableTreeNode term() throws SyntaxException {

        DefaultMutableTreeNode resultNode = null;
        DefaultMutableTreeNode newFactorNode = factor();

        if (check('*') || check('/')) {
            resultNode = term_rest(newFactorNode);
        } else {
            resultNode = newFactorNode;
        }

        return resultNode;
    }

    private DefaultMutableTreeNode term_rest(DefaultMutableTreeNode a) throws SyntaxException {
        DefaultMutableTreeNode resultNode = null;
        DefaultMutableTreeNode newTermRestNode = new DefaultMutableTreeNode();
        treeModel.insertNodeInto(a, newTermRestNode, newTermRestNode.getChildCount());

        switch (current()) {
            case ('/'):
            case ('*'): {
                newTermRestNode.setUserObject(new Operator(current()));
                next();
                DefaultMutableTreeNode newFactorNode = factor();
                treeModel.insertNodeInto(newFactorNode, newTermRestNode, newTermRestNode.getChildCount());

                    if (check('*') || check('/')) {
                        resultNode = term_rest(newTermRestNode);
                    } else {
                        resultNode = newTermRestNode;
                    }
            }
            break;
            default: {
                throw new SyntaxException(String.format("Sign expected but '%c' found", current()));
            }
        }
        return resultNode;
    }

    private DefaultMutableTreeNode factor() throws SyntaxException {
        DefaultMutableTreeNode resultNode = new DefaultMutableTreeNode();

        if (Character.isLetter(current())) {
            DefaultMutableTreeNode newLetterNode = new DefaultMutableTreeNode();
            newLetterNode.setUserObject(new Identifier(current()));
            next();
            resultNode = newLetterNode;
        } else {
            require('(');
            next();
            DefaultMutableTreeNode newExprNode = expr();
            resultNode = newExprNode;
            require(')');
            next();
        }
        return resultNode;
    }

    /*
     * Если текущий символ не равен c, то исключение.
     * Возвращает текущий символ.
     */
    private char require(char c) throws SyntaxException {
        if(eoln()) {
            throw new SyntaxException(String.format("'%c' expected but eoln found.", c));
        }

        if (input.charAt(pos) != c) {
            throw new SyntaxException(String.format("'%c' expected but '%c' found.", c, current()));
        }

        return input.charAt(pos);
    }

    /*
     * Проверяет текущий символ на равенство c
     */
    private boolean check(char c) {
        if (pos < input.length()) {
                return input.charAt(pos) == c;
        } else {
            return false;
        }
    }

    private boolean eoln() {
        return pos >= input.length();
    }

    private void next() throws SyntaxException {
        pos++;
    }

    /*
     * Возврящает текущий символ если он допустим, если не допустим, то генерирует исключение.
     */
    private char current() throws SyntaxException {
        if(eoln()) {
            throw new SyntaxException("Unexpected end of expression.");
        }

        if(!Character.isLetter(input.charAt(pos)) && input.charAt(pos) != '+' && input.charAt(pos) != '-' &&
                input.charAt(pos) != '*' && input.charAt(pos) != '/' && input.charAt(pos) != '(' &&
                input.charAt(pos) != ')') {
            throw new SyntaxException(String.format("Illegal symbol '%c'", input.charAt(pos)));
        }

        return input.charAt(pos);
    }
}
