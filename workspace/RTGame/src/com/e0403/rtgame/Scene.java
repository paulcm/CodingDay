package com.e0403.rtgame;

import java.util.ArrayList;
import java.util.List;

public class Scene {

	private List<DrawableEntity> entities;
	
	public Scene()
	{
		this.entities = new ArrayList<DrawableEntity>();
	}
	
	public final List<DrawableEntity> getEntities(){
		return this.entities;
	}
	
	public void addEntity(DrawableEntity entity) {
		this.entities.add(entity);
	}
	
	public void removeEntity(DrawableEntity entity) {
		this.entities.remove(entity);
	}
	

}
