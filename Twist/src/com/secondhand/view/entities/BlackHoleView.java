package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import com.secondhand.model.BlackHole;

public class BlackHoleView implements IEntityView{
	
	BlackHole mBlackHole;

	public BlackHoleView(BlackHole blackHole){
		mBlackHole = blackHole;
	}
	@Override
	public void propertyChange(final PropertyChangeEvent event) {
	}
}
