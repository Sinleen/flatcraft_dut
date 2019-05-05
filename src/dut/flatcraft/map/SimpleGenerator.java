package dut.flatcraft.map;

import dut.flatcraft.CellFactory;
import dut.flatcraft.GameMap;
import dut.flatcraft.MapRegistry;

/**
 * Generates a map in three parts:
 *
 * <ul>
 * <li>first half of the map is the sky</li>
 * <li>then we have one line of grass</li>
 * <li>then the remaining of the map is for the soil</li>
 * </ul>
 *
 * @author leberre
 *
 */
public class SimpleGenerator implements MapGenerator {


	@Override
	public GameMap generate(int width, int heigh, CellFactory factory) {
		SimpleGameMap map = MapRegistry.makeMap(width, heigh);
		int halfHeigh = heigh / 2;
		for (int i = 0; i < halfHeigh; i++) {
			for (int j = 0; j < width; j++) {
				map.setAt(i, j, factory.createSky());
			}
		}

		int x = RAND.nextInt(5);
		for (int j=0;j<width;j++){
			if (x>2) x -= RAND.nextInt(3);
			else x += RAND.nextInt(3);

			if (j<width/3) map.setAt(halfHeigh-x, j, factory.createSnow());
			if ((j>=width/3)&&(j<width/3*2))map.setAt(halfHeigh-x, j, factory.createGrass());
			if ((j>=width/3*2)&&(j<width))  map.setAt(halfHeigh-x, j, factory.createSand());

			for (int i=halfHeigh-x+1;i<=halfHeigh;i++){
				map.setAt(i, j, factory.createSoil()); 
			}
		}

		for (int i = halfHeigh+1; i < heigh; i++) {
			for (int j = 0; j < width; j++) {
				if (i>heigh-3) map.setAt(i, j, factory.createDeepSoil());
				else map.setAt(i, j, factory.createSoil());
			}
		}

		return map;
	}

}




