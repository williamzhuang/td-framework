package com.riforin.gameobjects;

import aurelienribon.tweenengine.TweenAccessor;

public class EnemyTween implements TweenAccessor<Enemy> {
	
	public static final int ALPHA = 1;
	public static final int xPOS = 2;
	public static final int yPOS = 3;

	@Override
	public int getValues(Enemy target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1;
		case xPOS:
			returnValues[0] = target.getX();
			return 2;
		case yPOS:
			returnValues[0] = target.getY();
			return 3;
		default:
			return 0;
		}
			
	}

	@Override
	public void setValues(Enemy target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case ALPHA:
			target.setColor(1, 1, 1, newValues[0]);
			break;
		case xPOS:
			target.setX(newValues[0]);
			break;
		case yPOS:
			target.setY(newValues[0]);
			break;
		}
		
	}

}
