package dut.flatcraft.resources;

import dut.flatcraft.ui.Chest;

import javax.swing.*;
import java.awt.*;

public class EnderChestResourceInstance extends ResourceInstance {

    private static JFrame frame;

    private static final long serialVersionUID = 1L;

    private static Chest chest = null;

    public EnderChestResourceInstance(Resource type) {
        super(type);
        if(chest == null){
            chest = new Chest();
        }
    }

    public EnderChestResourceInstance(Resource type, JLabel label) {
        super(type, label);
        if(chest == null){
            chest = new Chest();
        }
    }

    public static void setFrame(JFrame frame) {
        EnderChestResourceInstance.frame = frame;
    }

    @Override
    public boolean execute(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JDialog dialog = new JDialog(frame);
        dialog.setTitle("Coffre de l'end");
        dialog.add(chest);
        dialog.setLocation(screenSize.width / 2 - chest.getWidth() / 2, screenSize.height / 2 - chest.getHeight() / 2);
        dialog.pack();
        dialog.setVisible(true);
        return true;
    }
}