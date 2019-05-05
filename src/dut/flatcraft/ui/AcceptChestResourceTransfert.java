package dut.flatcraft.ui;

import dut.flatcraft.resources.ResourceContainer;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class AcceptChestResourceTransfert extends TransferHandler {

    private static final long serialVersionUID = 1L;

    private final Chest chest;

    AcceptChestResourceTransfert(Chest chest){ this.chest = chest; }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDataFlavorSupported(ResourceContainer.RESOURCE_FLAVOR);
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (support.isDrop()) {
            JPanel source = (JPanel) support.getComponent();
            try {
                ResourceContainerUI comp;
                ResourceContainer rc = (ResourceContainer) support.getTransferable()
                        .getTransferData(ResourceContainer.RESOURCE_FLAVOR);

                if (support.getDropAction() == MOVE || rc.getQuantity() == 1) {
                    comp = new ResourceContainerUI(rc);
                } else {
                    SelectChestQuantity selector = new SelectChestQuantity(rc.getQuantity());
                    JOptionPane pane = new JOptionPane(selector);
                    JDialog dialog = pane.createDialog(null,"Selectionner la quantité");
                    dialog.setVisible(true);
                    pane.setValue(selector.getQuantity());
                    int paneValue = (int) pane.getValue();
                    comp = new ResourceContainerUI(rc.getBlock().getType(), paneValue);
                }
                source.removeAll();
                this.chest.register(comp);
                source.add(comp);
                source.revalidate();
                source.repaint();
                return true;
            } catch (Exception e) {
                Logger.getAnonymousLogger().warning(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }


}
