package com.secondhand.view.entities;

import java.beans.PropertyChangeListener;

/* TODO: (?)
   Right now the only entites that implements PropertyChangeListener is:
   PlayerView and PowerUpView
   but we may want to make IEntityView extends PropertyChangeListener if more EntityViews will implement this.
*/
public interface IEntityView extends PropertyChangeListener{

	
}
