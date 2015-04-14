package com.e0403.rtgame;


public abstract class AbstractLivingEntitiy extends AbstractDrawableEntity implements LivingEntity {

	private boolean isAlive;
	
	public AbstractLivingEntitiy()
	{
		isAlive = true;
	}
	
	
	@Override
	public boolean isAlive() {
	  return isAlive;
	}


	@Override
	public void kill() {
		this.isAlive = false;
	}

}
