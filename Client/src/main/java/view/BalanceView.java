package view;

import utils.StockColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.*;

/**
 * Balance view
 */
public class BalanceView extends JPanel {
    private final static String LOAD = "Load";
    private final static String SELECT_AMOUNT = "Select Amount: ";
    private JLabel jlCurrent;
    private JButton jbLoad;
    private JComboBox<String> jcbAmount;

    /**
     * Creates the balance view
     */
    public BalanceView() {
        StockColors color = new StockColors();
        //Balance
        this.setBackground(color.getBLACK());
        this.setLayout(new GridLayout(3, 1));
        jlCurrent = new JLabel(SELECT_AMOUNT, CENTER);
        Font font = new Font("Segoe UI", Font.PLAIN, 30);
        jlCurrent.setFont(font);
        jlCurrent.setForeground(color.getWHITE());

        //Amount
        JPanel jpAmount = new JPanel(new FlowLayout());
        jpAmount.setBackground(color.getBLACK());

        JPanel jpMoney = new JPanel(new FlowLayout());
        jpMoney.setBackground(color.getBLACK());
        String[] amount = {"10.00$", "20.00$", "30.00$", "40.00$"};
        jcbAmount = new JComboBox<>(amount);
        Font fontOptions = new Font("Segoe UI", Font.PLAIN, 28);
        jcbAmount.setFont(fontOptions);
        jcbAmount.setPreferredSize(new Dimension(150, 50));

        jpMoney.add(jcbAmount);
        jpAmount.add(jpMoney);

        //Load
        Border borderOptions = BorderFactory.createLineBorder(color.getBLACK(), 1, true);
        JPanel jpLoadButton = new JPanel(new FlowLayout());
        jpLoadButton.setBackground(color.getBLACK());

        jbLoad = new JButton(LOAD);
        jbLoad.setBackground(color.getBLUE());
        jbLoad.setBorder(borderOptions);
        jbLoad.setForeground(color.getBLACK());
        jbLoad.setFont(fontOptions);
        jbLoad.setPreferredSize(new Dimension(200, 50));

        jpLoadButton.add(jbLoad, BorderLayout.CENTER);


        this.add(jlCurrent);
        this.add(jpAmount);
        this.add(jpLoadButton);
    }

    /**
     * Adds actionListener to load button
     *
     * @param actionListener ActionListener
     */
    public void registerController(ActionListener actionListener) {
        jbLoad.addActionListener(actionListener);
        jbLoad.setActionCommand(LOAD);
    }

    /**
     * Gets the amount selected
     *
     * @return amount selected
     */
    public String getAmount() {
        return jcbAmount.getSelectedItem().toString();
    }

    /**
     * Update the current balance
     *
     * @param amount new amount
     */
    public void updateCurrentBalance(String amount) {
        jlCurrent.setText("You have: " + amount + "$");
    }

    /**
     * Shows first option in the list
     */
    public void refreshComboboxBalance() {
        jcbAmount.setSelectedIndex(0);
    }
}
