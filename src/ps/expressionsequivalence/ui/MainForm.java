/*
 * MainForm.java
 *
 * Created on 23.05.2010, 21:58:40
 */
package ps.expressionsequivalence.ui;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import ps.expressionsequivalence.comparator.Comparator;
import ps.expressionsequivalence.parser.Parser;
import ps.expressionsequivalence.parser.SyntaxException;

/**
 *
 * @author Худяков Павел
 */
public class MainForm extends javax.swing.JFrame implements DocumentListener {

    public MainForm() {
        initComponents();

        // Назначить обработчики событий изменения полей.
        leftExpressionField.getDocument().addDocumentListener(this);
        leftExpressionField.getDocument().putProperty("name", "leftExpressionField");
        rightExpressionField.getDocument().addDocumentListener(this);
        rightExpressionField.getDocument().putProperty("name", "rightExpressionField");

        updateExpressionView(UpdateSide.BOTH);
    }

    private Parser parser = new Parser();
    private Comparator comparator = new Comparator();

    // Принимают значение истина если введенное (слева и справа соответственно) выражение верно и ложь в противном случае.
    // Имеют ссылочную сементику (с этой целью boolean обернут в Valid). Это необходимо для работы метода обновления
    // пользовательского интерфейса.
    private Valid leftExpressionValid = new Valid();
    private Valid rightExpressionValid = new Valid();

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rightExpressionTree = new javax.swing.JTree();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        leftExpressionTree = new javax.swing.JTree();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        leftExpressionField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        rightExpressionField = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        resultIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Please, enter expressions to compare in text fields belong...");
        setResizable(false);

        rightExpressionTree.setFocusable(false);
        jScrollPane1.setViewportView(rightExpressionTree);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setPreferredSize(new java.awt.Dimension(587, 400));

        leftExpressionTree.setFocusable(false);
        jScrollPane2.setViewportView(leftExpressionTree);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        logArea.setColumns(20);
        logArea.setRows(5);
        logArea.setEnabled(false);
        logArea.setFocusable(false);
        jScrollPane3.setViewportView(logArea);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        leftExpressionField.setText("a");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftExpressionField, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftExpressionField, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
        );

        rightExpressionField.setText("a");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rightExpressionField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rightExpressionField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(443, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(414, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(134, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(135, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField leftExpressionField;
    private javax.swing.JTree leftExpressionTree;
    private javax.swing.JTextArea logArea;
    private javax.swing.JLabel resultIcon;
    private javax.swing.JTextField rightExpressionField;
    private javax.swing.JTree rightExpressionTree;
    // End of variables declaration//GEN-END:variables

    public void insertUpdate(DocumentEvent de) {
        updateExpressionView(de);
    }

    public void removeUpdate(DocumentEvent de) {
        updateExpressionView(de);
    }

    public void changedUpdate(DocumentEvent de) {
        updateExpressionView(de);
    }

    /*
     * Обновляет пользовательский интерфейс для одного или обоих выражений.
     */
    private void updateExpressionView(int updateSide) {
        try {
            JTextField expressionField = null;
            JTree tree = null;
            Valid expressionValid = null; // Ссылка на флаг правильности выражения.

            switch (updateSide) {
                case (UpdateSide.LEFT): {
                    expressionField = leftExpressionField;
                    tree = leftExpressionTree;
                    expressionValid = leftExpressionValid;
                }
                break;
                case (UpdateSide.RIGHT): {
                    expressionField = rightExpressionField;
                    tree = rightExpressionTree;
                    expressionValid = rightExpressionValid;
                }
                break;
                case (UpdateSide.BOTH): {
                    updateExpressionView(UpdateSide.LEFT);
                    updateExpressionView(UpdateSide.RIGHT);
                }
                return;
                default: assert false;
            }

            assert ((expressionField != null) && (tree != null));

            TreeModel treeModel = null;
            try {
                treeModel = parser.parse(expressionField.getText().trim());
            } catch(SyntaxException ex) {
                //log(ex.getMessage());
            } catch(StringIndexOutOfBoundsException ex) {
              //  log("Unexpected end of expression.");
            }

            if (treeModel == null) {
                // Ошибка разрабора выражения
                expressionField.setBackground(Color.pink);
                setResultIcon(ResultIconType.UNKNOWN);
                tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
                expressionValid.setInvalid();
            } else {
                // Успешный разбор выражения
                expressionField.setBackground(Color.white);
                tree.setModel(treeModel);
                expressionValid.setValid();
            }

            if (leftExpressionValid.isValid() && rightExpressionValid.isValid()) {
                HashMap<Character, Character> correspondence = null;
                correspondence = comparator.compare((DefaultMutableTreeNode)leftExpressionTree.getModel().getRoot(),
                        (DefaultMutableTreeNode) rightExpressionTree.getModel().getRoot());
                if (correspondence != null) {
                    setResultIcon(ResultIconType.EQUALS);
                    
                    StringBuilder sb = new StringBuilder();
                    sb.append("Correspondence: {");

                    Iterator ci = correspondence.keySet().iterator();
                    while(ci.hasNext()) {
                        Character k = (Character) ci.next();
                        sb.append(String.format("[%c->%c] ", k, correspondence.get(k)));
                    }

                    sb.append("}");
                    log(sb.toString());
                } else {
                    setResultIcon(ResultIconType.NO_EQUALS);
                }
            }
        } catch(Throwable ex) {
            log(ex.getMessage());
            ex.printStackTrace();
            log("Internal error in program: " + ex.getMessage());
        }
    }

    /*
     * Сообщение в текстовое поле с журналом.
     */
    private void log(String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("H:m:s");
        logArea.setText(logArea.getText().concat(String.format("%s %s \n", sdf.format(new Date()), message)));
        System.out.print(message);
    }

    private ImageIcon createImageIcon(String path) {
        try {
            URL imgURL = new URL("file:" + path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                log("Couldn't find file: " + path);
                return null;
            }
        } catch (MalformedURLException ex) {
            log("Couldn't find file: " + path);
            return null;
        }
    }

    private void setResultIcon(int iconType) {
        ImageIcon image = null;
        String text = null;
        switch (iconType) {
            case (ResultIconType.EQUALS): {
                image = createImageIcon("img/equal_64x64.png");

                if (image == null) {
                    text = "equals";
                }
            }
            break;

            case (ResultIconType.NO_EQUALS): {
                image = createImageIcon("img/no_equal_64x64.png");

                if (image == null) {
                    text = "no equals";
                }
            }
            break;

            case (ResultIconType.UNKNOWN): {
                image = createImageIcon("img/unknown.png");

                if (image == null) {
                    text = "-";
                }
            }
            break;

            default:
                assert false;
        }

        assert image != null || text != null;

        if (image != null) {
            resultIcon.setIcon(image);
        } else {
            resultIcon.setText(text);
        }
    }

    private void updateExpressionView(DocumentEvent de) {
        if (de.getDocument().getProperty("name").equals("leftExpressionField")) {
            updateExpressionView(UpdateSide.LEFT);
        }

        if (de.getDocument().getProperty("name").equals("rightExpressionField")) {
            updateExpressionView(UpdateSide.RIGHT);
        }
    }
}
