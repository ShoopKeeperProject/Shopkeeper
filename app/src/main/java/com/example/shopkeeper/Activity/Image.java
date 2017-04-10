package com.example.shopkeeper.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.example.shopkeeper.Manager.ShooperKeeperException;
import com.example.shopkeeper.Model.Category;
import com.example.shopkeeper.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.example.shopkeeper.Manager.ProductManager;
import com.example.shopkeeper.Manager.CallBack;


public class Image extends Activity {

	private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
	private Button btnSelect;
	private ImageView ivImage;
	private String userChoosenTask;
	ZoomControls simpleZoomControls;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		btnSelect = (Button) findViewById(R.id.btnSelectPhoto);
		btnSelect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectImage();
			}
		});
		ivImage = (ImageView) findViewById(R.id.ivImage);

		simpleZoomControls = (ZoomControls) findViewById(R.id.simpleZoomControl); // initiate a ZoomControls

		// perform setOnZoomInClickListener event on ZoomControls
		simpleZoomControls.setOnZoomInClickListener(new View.OnClickListener() {
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onClick(View v) {
				// calculate current scale x and y value of ImageView
				float x = ivImage.getScaleX();
				float y = ivImage.getScaleY();
				// set increased value of scale x and y to perform zoom in functionality
				ivImage.setScaleX((float) (x + 0.5));
				ivImage.setScaleY((float) (y + 0.5));
			}
		});
		simpleZoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onClick(View v) {
				// calculate current scale x and y value of ImageView
				float x = ivImage.getScaleX();
				float y = ivImage.getScaleY();
				// set decreased value of scale x and y to perform zoom out functionality
				ivImage.setScaleX((float) (x - 0.5));
				ivImage.setScaleY((float) (y - 0.5));
			}
		});
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {
			case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					if(userChoosenTask.equals("Take Photo"))
						cameraIntent();
					else if(userChoosenTask.equals("Choose from Library"))
						galleryIntent();
				} else {
					//code for deny
				}
				break;
		}
	}

	private void selectImage() {
		final CharSequence[] items = { "Take Photo", "Choose from Library",
				"Cancel" };

		AlertDialog.Builder builder = new AlertDialog.Builder(Image.this);
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				boolean result=Utility.checkPermission(Image.this);

				if (items[item].equals("Take Photo")) {
					userChoosenTask ="Take Photo";
					if(result)
						cameraIntent();

				} else if (items[item].equals("Choose from Library")) {
					userChoosenTask ="Choose from Library";
					if(result)
						galleryIntent();

				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}

	private void galleryIntent()
	{
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);//
		startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
	}

	private void cameraIntent()
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, REQUEST_CAMERA);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == SELECT_FILE)
				onSelectFromGalleryResult(data);
			else if (requestCode == REQUEST_CAMERA)
				onCaptureImageResult(data);
		}
	}

	private void onCaptureImageResult(Intent data) {
		Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

		final File destination = new File(Environment.getExternalStorageDirectory(),
				System.currentTimeMillis() + ".jpg");

		FileOutputStream fo;
		try {
			destination.createNewFile();
			fo = new FileOutputStream(destination);
			fo.write(bytes.toByteArray());
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//****use uploadImage() to get the ****

		//Toast.makeText(getBaseContext(), (CharSequence) destination, Toast.LENGTH_LONG).show();
		Log.d("image", "img: " + destination.getAbsolutePath());
		Uri uri = Uri.parse("file://" + destination.getAbsolutePath());

		ProductManager.getInstance().uploadImage(uri, new CallBack<String>() {
			@Override
			public void onResponse(String result, ShooperKeeperException ex) {
				if (ex != null) {
					ex.printStackTrace();
					return;
				}
				Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
			}
		});
		//for testing if the image is taken
		ivImage.setImageBitmap(thumbnail);
	}

	@SuppressWarnings("deprecation")
	private void onSelectFromGalleryResult(Intent data) {

		Bitmap bm=null;
		if (data != null) {
			try {
				bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//****should be return to david page and give him the image****
		ivImage.setImageBitmap(bm);
	}
}
