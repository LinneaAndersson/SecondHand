package com.secondhand.view.entities;

import java.beans.PropertyChangeListener;

public interface IEntityView extends PropertyChangeListener {

	public void updateView(String name);
	
}
