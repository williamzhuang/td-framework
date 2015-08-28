package com.riforin.gameobjects;

import com.badlogic.gdx.Gdx;

import aurelienribon.tweenengine.TweenAccessor;

public class EnemyAccessor implements TweenAccessor<Enemy> {
	
	public static final int POSITION_XY = 1;

	@Override
	public int getValues(Enemy target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case POSITION_XY:
			returnValues[0] = target.getX();
			returnValues[1] = target.getY();
			return 2;
		default:
			return 0;
		}
			
	}

	@Override
	public void setValues(Enemy target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case POSITION_XY:
			target.setX(newValues[0]);
			target.setY(newValues[1]);
			break;
		default: 
			assert false;
			break;
		}
		
	}

}
