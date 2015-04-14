package com.e0403.rtgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scene {

	private List<DrawableEntity> entities;
	private List<DrawableEntity> entitiesToDelete;
	
	public Scene()
	{
		this.entities = new ArrayList<DrawableEntity>();
		entitiesToDelete = new ArrayList<DrawableEntity>();
	}
	
	public final List<DrawableEntity> getEntities(){
		return this.entities;
	}
	
	public void addEntity(DrawableEntity entity) {
		this.entities.add(entity);
	}
	
	public void removeEntity(DrawableEntity entity) {
		entitiesToDelete.add(entity);
	}
	
	public void updateScene()
	{
		Iterator<DrawableEntity> itRemove = entitiesToDelete.iterator();
		Iterator<DrawableEntity> itScene = entities.iterator();
		DrawableEntity eRemove = null;
		DrawableEntity eScene = null;
		while(itRemove.hasNext())
		{
		    eRemove = itRemove.next();
			while(itScene.hasNext())
			{
				eScene = itScene.next();
				if(eScene.equals(eRemove))
				{
					itRemove.remove();
					itScene.remove();
					break;
				}
			}
		}
		entitiesToDelete.clear();
	}
	
}
