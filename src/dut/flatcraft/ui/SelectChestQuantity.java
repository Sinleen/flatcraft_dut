package dut.flatcraft.ui;

import javax.swing.*;
import java.awt.*;

public class SelectChestQuantity extends JPanel {

    private static final long serialVersionUID = 1L;

    private int quantity;
    private final int containerQuantity;
    private JLabel currentQuantity;

    public SelectChestQuantity(int quantity){

        this.setLayout(new BorderLayout());
        JPanel chestQuantity = new JPanel();
        chestQuantity.setLayout(new GridLayout(3,1));

        this.quantity = quantity;
        containerQuantity = quantity;
        currentQuantity = new JLabel("" + quantity, SwingConstants.CENTER);
        JButton more = new JButton("+");
        JButton less = new JButton("-");
        this.setLayout(new BorderLayout());

        Dimension d = new Dimension(50,50);

        more.setPreferredSize(d);
        less.setPreferredSize(d);
        currentQuantity.setPreferredSize(d);

        more.addActionListener(e -> onClickMoreButton());
        less.addActionListener(e -> onClickLessButton());

        chestQuantity.add(more);
        chestQuantity.add(less);
        chestQuantity.add(currentQuantity);

        add(BorderLayout.CENTER, chestQuantity);
    }

    public int getQuantity(){
        return quantity;
    }

    private void onClickMoreButton(){
        if(quantity < containerQuantity) {
            quantity++;
            currentQuantity.setText("" + quantity);
            currentQuantity.repaint();
        }
    }

    private void onClickLessButton(){
        if(quantity > 0){
            quantity--;
            currentQuantity.setText("" + quantity);
            currentQuantity.repaint();
        }
    }
}
