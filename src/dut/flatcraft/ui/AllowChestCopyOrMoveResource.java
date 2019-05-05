package dut.flatcraft.ui;

import dut.flatcraft.resources.ResourceContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;

public class AllowChestCopyOrMoveResource extends TransferHandler {

    private static final long serialVersionUID = 1L;

    private final Chest chest;

    AllowChestCopyOrMoveResource(Chest chest){ this.chest = chest; }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE | COPY;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        return ((ResourceContainerUI) c).getResourceContainer().clone();
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        Container container = source.getParent();
        ResourceContainer rc = ((ResourceContainerUI) source).getResourceContainer();
        if (action == MOVE || rc.getQuantity() == 0) {
            container.remove(source);
            container.revalidate();
            container.repaint();
        } else if (action == COPY) {
            if (rc.getQuantity() == 1) {
                rc.consume(1);
                container.removeAll();
            } else {
                rc.consume(rc.getQuantity() / 2);
            }
            container.revalidate();
            container.repaint();
        }
    }
}
