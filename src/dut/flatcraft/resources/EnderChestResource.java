package dut.flatcraft.resources;

import javax.swing.*;

import dut.flatcraft.tools.ToolType;

public class EnderChestResource extends Resource {

    private static final long serialVersionUID = 1L;

    public EnderChestResource(String name, ImageIcon appaerance, int hardness, ToolType toolType) {
        super(name, appaerance, hardness, toolType);
    }

    @Override
    public ResourceInstance newInstance() {
        return new EnderChestResourceInstance(this);
    }

    @Override
    public ResourceInstance newInstance(JLabel label) {
        return new EnderChestResourceInstance(this, label);
    }
}