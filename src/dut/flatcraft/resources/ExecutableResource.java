package dut.flatcraft.resources;

import javax.swing.*;

import dut.flatcraft.tools.ToolType;

public class ExecutableResource extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExecutableResource(String name, ImageIcon appearance, int hardness, ToolType toolType) {
		super(name, appearance, hardness, toolType);
	}

	@Override
	public ExecutableResourceInstance newInstance() {
		return new ExecutableResourceInstance(this);
	}

	@Override
	public ExecutableResourceInstance newInstance(JLabel label) {
		return new ExecutableResourceInstance(this, label);
	}

}
