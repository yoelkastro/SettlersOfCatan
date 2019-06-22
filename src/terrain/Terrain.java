package terrain;

<<<<<<< HEAD
import game.Resource;

import javafx.scene.paint.Color;

public enum Terrain {

	FOREST(Color.DARKOLIVEGREEN, Resource.WOOD),
	FIELD(Color.BURLYWOOD, Resource.GRAIN),
	MOUNTAIN(Color.DARKGREY, Resource.STONE),
	PASTURE(Color.LAWNGREEN, Resource.WOOL),
	HILL(Color.CHOCOLATE, Resource.CLAY),
	DESERT(Color.KHAKI, null); // TO-DO: Texture

	final private Color color;
	final private Resource resource;
	
	final static Terrain[] standardTerrain = {Terrain.FOREST, Terrain.FOREST, Terrain.FOREST, Terrain.FOREST, Terrain.FIELD, Terrain.FIELD, Terrain.FIELD, Terrain.FIELD, Terrain.MOUNTAIN, Terrain.MOUNTAIN, Terrain.MOUNTAIN, 
			Terrain.PASTURE, Terrain.PASTURE, Terrain.PASTURE, Terrain.PASTURE, Terrain.HILL, Terrain.HILL, Terrain.HILL, Terrain.DESERT};
	
	Terrain(Color color, Resource resource){
		this.color = color;
		this.resource = resource;
	}
	
	public Color getColor(){
		return this.color;
=======
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.Resource;
import javafx.scene.image.Image;

public enum Terrain {

	FOREST("res/tiles/forest.png", Resource.WOOD),
	FIELD("res/tiles/field.png", Resource.GRAIN),
	MOUNTAIN("res/tiles/mountain.png", Resource.STONE),
	PASTURE("res/tiles/pasture.png", Resource.WOOL),
	HILL("res/tiles/hill.png", Resource.CLAY),
	DESERT("res/tiles/desert.png", null);

	private Image tex;
	final private Resource resource;
	
	final static Terrain[] standardTerrain = {Terrain.FOREST, Terrain.FOREST, Terrain.FOREST, Terrain.FOREST, Terrain.FIELD, Terrain.FIELD, Terrain.FIELD, Terrain.FIELD, Terrain.MOUNTAIN, Terrain.MOUNTAIN, Terrain.MOUNTAIN, 
			Terrain.PASTURE, Terrain.PASTURE, Terrain.PASTURE, Terrain.PASTURE, Terrain.HILL, Terrain.HILL, Terrain.HILL, Terrain.DESERT};
	
	Terrain(String path, Resource resource){
		try {
			this.tex = new Image(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.resource = resource;
	}
	
	public Image getTexture(){
		return this.tex;
>>>>>>> branch 'master' of https://github.com/KOK29364/SettlersOfCatan
	}
	
	public Resource getResource(){
		return this.resource;
	}
	
}
