package com.secondhand.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

import android.content.Context;

import com.secondhand.controller.SceneManager;
import com.secondhand.debug.MyDebug;

// Used to serialize all the data of EntityManager to a file
public class EntityManagerSerializer {

	// the file to serialize to
	private static final String FILE_NAME = "game_world_entities.dat";

	
	// reference to all the data managed by EntityManager
	private List<Entity> entityList;
	private List<Enemy> enemyList;
	private Stack<Entity> scheduledForDeletionEntities;
	private Player player;

	// construct from the serialization file.
	public EntityManagerSerializer() {
		deserialize();
	}
	
	public EntityManagerSerializer(
			final Player player,
			final List<Entity> entityList,
			final List<Enemy> enemyList,
			final Stack<Entity> scheduledForDeletionEntities) {
		this.enemyList = enemyList;
		this.entityList = entityList;
		this.scheduledForDeletionEntities = scheduledForDeletionEntities;
		this.player = player;
		
		// for debugging reasons.
	}
	
	private void deserialize() {
		try {
			final DataInputStream out = new DataInputStream(new BufferedInputStream(
					SceneManager.getInstance().getContext().openFileInput(FILE_NAME)));
			
			this.player = Player.readFromStream(out);
			
		} catch (final FileNotFoundException e) {
			MyDebug.e("Could not open serialization file for writing", e);
		} catch (final IOException e) {
			MyDebug.e("Could not write to serialization file. ", e);
		}
	}
	
	public void serialize() {	
		try {
			final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
				SceneManager.getInstance().getContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE)));
			
			player.writeToStream(out);
			
		} catch (final FileNotFoundException e) {
			MyDebug.e("Could not open serialization file for writing", e);
		} catch (final IOException e) {
			MyDebug.e("Could not write to serialization file. ", e);
		}
		
		deserialize();
	}
}
