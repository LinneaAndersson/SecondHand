package com.secondhand.controller;

public interface IAsyncCallback {
	public void work();
	
	public void onWorkComplete();
}
