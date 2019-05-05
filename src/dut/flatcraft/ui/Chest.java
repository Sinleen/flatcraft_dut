package dut.flatcraft.ui;

import static dut.flatcraft.MineUtils.DEFAULT_IMAGE_SIZE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.*;


public class Chest extends JPanel {

    private static final long serialVersionUID = 1L;

    private JPanel chestPanel;

    private MouseListener dndMouseListener = new MyMouseAdapter();
    private TransferHandler from;

    public Chest(){
        this.setLayout(new BorderLayout());
        chestPanel = new JPanel();
        chestPanel.setLayout(new GridLayout(3,9));
        from = new AllowChestCopyOrMoveResource(this);
        createGrid();
    }

    private void createGrid(){
        TransferHandler to = new AcceptChestResourceTransfert(this);
        JPanel chestCell;
        for (int i = 0; i < 27; i++) {
            chestCell = new JPanel();
            chestPanel.add(chestCell);
            chestCell.setTransferHandler(to);
            chestCell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            chestCell.setPreferredSize(new Dimension(DEFAULT_IMAGE_SIZE + 10, DEFAULT_IMAGE_SIZE + 10));
        }
        add(BorderLayout.CENTER, chestPanel);
    }

    void register(ResourceContainerUI ui) {
        ui.setTransferHandler(from);
        ui.addMouseListener(dndMouseListener);
    }

}
