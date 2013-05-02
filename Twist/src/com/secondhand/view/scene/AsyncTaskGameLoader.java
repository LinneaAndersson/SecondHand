package com.secondhand.view.scene;


import android.os.AsyncTask;

/**
 * Used to perform loading on a background thread in the loading scene.
 */
public class AsyncTaskGameLoader extends
		AsyncTask<IAsyncCallback, Integer, Boolean> {

	private IAsyncCallback[] params;

	@Override
	protected Boolean doInBackground(final IAsyncCallback... params) {
		this.params = params;
		final int count = params.length;
		for (int i = 0; i < count; i++) {
			params[i].work();
		}
		return true;
	}

	@Override
	protected void onPostExecute(final Boolean result) {
		final int count = this.params.length;
		for (int i = 0; i < count; i++) {
			this.params[i].onWorkComplete();
		}
	}
}
