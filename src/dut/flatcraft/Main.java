package dut.flatcraft;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dut.flatcraft.map.MapGenerator;
import dut.flatcraft.map.SimpleGenerator;
import dut.flatcraft.map.TerrilDecorator;
import dut.flatcraft.map.TreeDecorator;
import dut.flatcraft.resources.ChestResourceInstance;
import dut.flatcraft.resources.EnderChestResourceInstance;
import dut.flatcraft.ui.CraftTable;
import dut.flatcraft.ui.Furnace;
import dut.flatcraft.ui.MyGrid;

public class Main {



	private static void positionCraftTable(Dimension screenSize, JButton button, JDialog dialog) {
		if (dialog.isVisible()) {
			dialog.setVisible(false);
		} else {
			Point pos = button.getLocation();
			dialog.setLocation(pos.x + button.getWidth() - dialog.getWidth(),
					screenSize.height - 70 - dialog.getHeight());
			dialog.setVisible(true);
		}
	}

	private static void positionFurnace(Dimension screenSize, JButton button, JDialog dialog) {
		if (dialog.isVisible()) {
			dialog.setVisible(false);
		} else {
			Point pos = button.getLocation();
			dialog.setLocation(pos.x, screenSize.height - 70 - dialog.getHeight());
			dialog.setVisible(true);
		}
	}

	public static void main(String[] args) {
		MapGenerator generator =new TerrilDecorator(new TreeDecorator(new SimpleGenerator(), 50, 7), 5);

		JFrame frame = new JFrame("FLATCRAFT 2019 - Student project - F1 to get help");

		ChestResourceInstance.setFrame(frame);
		EnderChestResourceInstance.setFrame(frame);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		MyGrid grid = new MyGrid((screenSize.height - 150) / 20, 120, new ResourceCellFactory(), generator);
		JScrollPane scrollpane = new JScrollPane(grid, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.getVerticalScrollBar().setUnitIncrement(40);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(40);
		scrollpane.setDoubleBuffered(true);
		frame.add(BorderLayout.CENTER, scrollpane);

		JPanel south = new JPanel();

		JDialog craft = new JDialog(frame, "Craft Table");
		craft.add(new CraftTable(grid.getPlayer()));
		craft.pack();
		craft.setLocation(screenSize.width / 2 - craft.getWidth() / 2, screenSize.height / 2 - craft.getHeight() / 2);

		JDialog cook = new JDialog(frame, "Furnace");
		cook.add(new Furnace(grid.getPlayer()));
		cook.pack();

		JButton cookButton = new JButton(MineUtils.getImage("furnace_front"));
		cookButton.addActionListener(e -> positionFurnace(screenSize, cookButton, cook));
		cookButton.setFocusable(false);
		cookButton.setToolTipText("Furnace");
		south.add(cookButton);

		south.add(grid.getPlayer().getInventoryUI());

		JButton craftButton = new JButton("Craft");
		craftButton.addActionListener(e -> positionCraftTable(screenSize, craftButton, craft));
		craftButton.setFocusable(false);
		craftButton.setToolTipText("Craft Table");
		south.add(craftButton);



		frame.add(BorderLayout.SOUTH, south);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
