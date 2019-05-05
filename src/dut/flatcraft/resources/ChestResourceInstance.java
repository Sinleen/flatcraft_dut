package dut.flatcraft.resources;

import dut.flatcraft.ui.Chest;

import javax.swing.*;
import java.awt.*;

public class ChestResourceInstance extends ResourceInstance {

    private static JFrame frame;

    public static void setFrame(JFrame frame) {
        ChestResourceInstance.frame = frame;
    }

    private static final long serialVersionUID = 1L;

    private Chest chest = new Chest();

    public ChestResourceInstance(Resource type) { super(type); }

    public ChestResourceInstance(Resource type, JLabel label) { super(type, label); }


    @Override
    public boolean execute(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JDialog dialog = new JDialog(frame);
        dialog.setTitle("Coffre");
        dialog.add(chest);
        dialog.setLocation(screenSize.width / 2 - chest.getWidth() / 2, screenSize.height / 2 - chest.getHeight() / 2);
        dialog.pack();
        dialog.setVisible(true);
        return true;
    }
}
