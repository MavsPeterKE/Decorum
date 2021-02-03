package com.example.decorum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.ArchitectView.ArchitectUrlListener;
import com.wikitude.architect.ArchitectView.CaptureScreenCallback;
import com.wikitude.architect.ArchitectView.SensorAccuracyChangeListener;
import com.wikitude.architect.StartupConfiguration.CameraPosition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class SampleCamActivity extends AbstractArchitectCamActivity implements SensorEventListener {

	/**
	 * last time the calibration toast was shown, this avoids too many toast shown when compass needs calibration
	 */
	private long lastCalibrationToastShownTimeMillis = System.currentTimeMillis();

	@Override
	public String getARchitectWorldPath() {
		return "render_furniture/index.html";
	}

	@Override
	public String getActivityTitle() {
		return "RenderFurniture";
	}

	@Override
	public int getContentViewId() {
		return R.layout.activity_render_furniture;
	}

	@Override
	public int getArchitectViewId() {
		return R.id.architectView;
	}

	@Override
	public String getWikitudeSDKLicenseKey() {
		return com.example.decorum.WikitudeSDKConstants.WIKITUDE_SDK_KEY;
	}

	@Override
	public SensorAccuracyChangeListener getSensorAccuracyListener() {
		return new SensorAccuracyChangeListener() {
			@Override
			public void onCompassAccuracyChanged( int accuracy ) {
				/* UNRELIABLE = 0, LOW = 1, MEDIUM = 2, HIGH = 3 */
				if ( accuracy < SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM && com.example.decorum.SampleCamActivity.this != null && !com.example.decorum.SampleCamActivity.this.isFinishing() && System.currentTimeMillis() - com.example.decorum.SampleCamActivity.this.lastCalibrationToastShownTimeMillis > 5 * 1000) {
					Toast.makeText( com.example.decorum.SampleCamActivity.this, R.string.compass_accuracy_low, Toast.LENGTH_LONG ).show();
					com.example.decorum.SampleCamActivity.this.lastCalibrationToastShownTimeMillis = System.currentTimeMillis();
				}
			}
		};
	}

	@Override
	public ArchitectUrlListener getUrlListener() {
		return new ArchitectUrlListener() {

			@Override
			public boolean urlWasInvoked(String uriString) {
				Uri invokedUri = Uri.parse(uriString);

				// pressed "More" button on POI-detail panel
				if ("markerselected".equalsIgnoreCase(invokedUri.getHost())) {
//					final Intent poiDetailIntent = new Intent(SampleCamActivity.this, SamplePoiDetailActivity.class);
//					poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_ID, String.valueOf(invokedUri.getQueryParameter("id")) );
//					poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_TITILE, String.valueOf(invokedUri.getQueryParameter("title")) );
//					poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_DESCR, String.valueOf(invokedUri.getQueryParameter("description")) );
//					SampleCamActivity.this.startActivity(poiDetailIntent);
//					return true;
					return false;
				}

				// pressed snapshot button. check if host is button to fetch e.g. 'architectsdk://button?action=captureScreen', you may add more checks if more buttons are used inside AR scene
				else if ("button".equalsIgnoreCase(invokedUri.getHost())) {
					com.example.decorum.SampleCamActivity.this.architectView.captureScreen(ArchitectView.CaptureScreenCallback.CAPTURE_MODE_CAM_AND_WEBVIEW, new CaptureScreenCallback() {

						@Override
						public void onScreenCaptured(final Bitmap screenCapture) {
							// store screenCapture into external cache directory
							final File screenCaptureFile = new File(Environment.getExternalStorageDirectory().toString(), "screenCapture_" + System.currentTimeMillis() + ".jpg");

							// 1. Save bitmap to file & compress to jpeg. You may use PNG too
							try {
								final FileOutputStream out = new FileOutputStream(screenCaptureFile);
								screenCapture.compress(Bitmap.CompressFormat.JPEG, 90, out);
								out.flush();
								out.close();

								// 2. create send intent
								final Intent share = new Intent(Intent.ACTION_SEND);
								share.setType("image/jpg");
								share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(screenCaptureFile));

								// 3. launch intent-chooser
								final String chooserTitle = "Share Snaphot";
								com.example.decorum.SampleCamActivity.this.startActivity(Intent.createChooser(share, chooserTitle));

							} catch (final Exception e) {
								// should not occur when all permissions are set
								com.example.decorum.SampleCamActivity.this.runOnUiThread(new Runnable() {

									@Override
									public void run() {
										// show toast message in case something went wrong
										Toast.makeText(com.example.decorum.SampleCamActivity.this, "Unexpected error, " + e, Toast.LENGTH_LONG).show();
									}
								});
							}
						}
					});
				}
				return true;
			}
		};
	}

	@Override
	public ILocationProvider getLocationProvider(final LocationListener locationListener) {
		return new LocationProvider(this, locationListener);
	}


	@Override
	public float getInitialCullingDistanceMeters() {
		// you need to adjust this in case your POIs are more than 50km away from user here while loading or in JS code (compare 'AR.context.scene.cullingDistance')
		return com.example.decorum.ArchitectViewHolderInterface.CULLING_DISTANCE_DEFAULT_METERS;
	}

	@Override
	protected boolean hasGeo() {
		return true;
	}

	@Override
	protected boolean hasIR() {
		return true;
	}

	@Override
	protected CameraPosition getCameraPosition() {

		return CameraPosition.DEFAULT;
	}

	/* Set up Compass listener */
	private SensorManager mSensorManager;
	Sensor accelerometer;
	Sensor magnetometer;
	float azimuth;
	private String jsonString;

	public void onAccuracyChanged(Sensor sensor, int accuracy) { }

	float[] mGravity;
	float[] mGeomagnetic;
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
			mGravity = event.values;
		if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
			mGeomagnetic = event.values;
		if (mGravity != null && mGeomagnetic != null) {
			float R[] = new float[9];
			float I[] = new float[9];
			boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
			if (success) {
				float orientation[] = new float[3];
				SensorManager.getOrientation(R, orientation);
				float newAzimuth = (orientation[0] < 0) ? 2 * (float) Math.PI + orientation[0] : orientation[0];
				float diffAzimuth = newAzimuth - getIntent().getExtras().getFloat(com.example.decorum.BrowseFurnitureActivity.AZIMUTH);
				azimuth = diffAzimuth;
				String[] callJSArg = {Float.toString(azimuth)};
				callJavaScript("World.setBearingExternally", callJSArg);
				try {
					writeBearingToJSON(azimuth);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getJSON() {
		return jsonString;
	}

	ServerSocket serverSocket;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}

		Thread socketServerThread = new Thread(new SocketServerThread(this));
		socketServerThread.start();
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		String[] filename = {"\"" + getIntent().getExtras().getString(com.example.decorum.BrowseFurnitureActivity.SELECTED_FURNITURE) + "\""};
		Log.d("selected_furniture", getIntent().getExtras().getString(com.example.decorum.BrowseFurnitureActivity.SELECTED_FURNITURE));
		callJavaScript("World.init", filename);
	}

	@Override
	public void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
		mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}
	private void writeBearingToJSON(Float azimuth) throws IOException {
		File file = new File(getExternalCacheDir(), "bearing.json");
		FileOutputStream stream = new FileOutputStream(file);
		jsonString = "{\"bearing\": " + azimuth + ",\n" +
				"\"selection\": \"" + getIntent().getExtras().getString(com.example.decorum.BrowseFurnitureActivity.SELECTED_FURNITURE)+ "\"}";
		try {
			stream.write(jsonString.getBytes());
		} finally {
			stream.close();
		}
	}

	@Override
	protected  void onDestroy() {
		super.onDestroy();

		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
