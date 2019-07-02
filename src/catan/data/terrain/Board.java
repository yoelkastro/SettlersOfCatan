package catan.data.terrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import catan.data.GameMode;
import catan.io.ResourceLoader;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;


public class Board {

	private ResourceLoader resources;

	private GameMode gameMode;
	private Tile[][] tiles;


	/**
	 * Creates a new Board object.
	 * 
	 * @param gameMode  The current expansion set being played.
	 * @param resources The common ResourceLoader object, so that the Board may grab
	 *                  assets easily.
	 */
	public Board(GameMode gameMode, ResourceLoader resources) {
		this.gameMode = gameMode;
		this.resources = resources;
		tiles = this.generateTerrain();
	}


	/**
	 * Renders the Board onto the screen.
	 * 
	 * @param gc     The GraphicsContext of the canvas.
	 * @param offset How "shifted" the Board is.
	 * @param zoom   How zoomed in the user currently is.
	 */
	public void render(GraphicsContext gc, Point2D offset, double zoom) {
		switch (gameMode) {
			case BASE_GAME:
				for (int y = 0; y < tiles.length; y++) {
					for (int x = 0; x < tiles[y].length; x++) {
						final Point2D axialToPixel = Tile.axialToPixel(tiles[y][x].getCoords(), zoom, offset);
						final double hexCenterX = axialToPixel.getX();
						final double hexCenterY = axialToPixel.getY();

						Point2D hexCenter = new Point2D(hexCenterX, hexCenterY);
						tiles[y][x].render(gc, hexCenter, zoom);
					}

				}
				break;
			case SEAFARERS:
				break;
			case CITIES_AND_KNIGHTS:
				break;
			case TRADERS_AND_BARBARIANS:
				break;
			case EXPLORERS_AND_PIRATES:
				break;
		}
	}


	/**
	 * Generates a suitable Tile configuration for the current game mode.
	 * 
	 * @return The generated Tile[][] array.
	 */
	private Tile[][] generateTerrain() {
		switch (gameMode) {
			case BASE_GAME:
				Tile[][] t = new Tile[7][];

				// @formatter:off
				ArrayList<Terrain> standardTerrain = new ArrayList<>(Arrays.asList(
					Terrain.FOREST, Terrain.FOREST, Terrain.FOREST, Terrain.FOREST,
					Terrain.FIELD, Terrain.FIELD, Terrain.FIELD, Terrain.FIELD,
					Terrain.MOUNTAIN, Terrain.MOUNTAIN, Terrain.MOUNTAIN,
					Terrain.PASTURE, Terrain.PASTURE, Terrain.PASTURE, Terrain.PASTURE,
					Terrain.HILL, Terrain.HILL, Terrain.HILL,
					Terrain.DESERT
				));
				// @formatter:on

				for (int y = 0; y < t.length; y++) {
					t[y] = new Tile[7 - Math.abs(3 - y)];
				}

				Random rand = new Random();
				for (int y = 0; y < t.length; y++) {
					for (int x = 0; x < t[y].length; x++) {
						final Point2D coords = new Point2D(x + Math.max((3 - y), 0), y);
						Terrain nextTerrain;

						if (y == 0 || y == t.length - 1 || x == 0 || x == t[y].length - 1) {
							nextTerrain = Terrain.WATER;
						} else {
							nextTerrain = standardTerrain.get(rand.nextInt(standardTerrain.size()));
							standardTerrain.remove(nextTerrain);
						}

						t[y][x] = new Tile(nextTerrain, coords, resources);
					}
				}
				return t;
			case SEAFARERS:
				break;
			case CITIES_AND_KNIGHTS:
				break;
			case TRADERS_AND_BARBARIANS:
				break;
			case EXPLORERS_AND_PIRATES:
				break;
		}

		return null;
	}


	/**
	 * The Tile[][] array.
	 * 
	 * @return The Tile[][] array.
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * Returns the Tile that matches the given axial coordinates.
	 * 
	 * @param axial The axial coordinates being searched for.
	 * 
	 * @return The Tile that matches the given axial coordinates.
	 */
	public Tile getTile(Point2D axial) {
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				final Point2D tileCoords = tiles[y][x].getCoords();
				if (axial.getX() == tileCoords.getX() && axial.getY() == tileCoords.getY()) return tiles[y][x];
			}
		}

		return null;
	}

}
