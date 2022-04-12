package com.blusky.blusky.view.vehicle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blusky.blusky.R;
import com.blusky.blusky.constants.App;
import com.blusky.blusky.view.auth.ForgetOtpActivity;
import com.blusky.blusky.view_model.AddVehicleViewModel;
import com.blusky.blusky.view_model.ForgetViewModel;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.marozzi.roundbutton.RoundButton;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddVehicleActivity extends AppCompatActivity implements View.OnClickListener{

    EditText registration_no,vehicle_type,make,color;
    ImageView image;
    Button selectImage;
    RoundButton submit;
    Activity activity;
    String[] listItems = {"one", "two", "three", "four", "five"};
    RequestBody requestFile;
    MultipartBody.Part body;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        activity=this;
        initView();
    }

    private void initView() {
        registration_no=findViewById(R.id.registration_no);
        vehicle_type=findViewById(R.id.vehicle_type);
        vehicle_type.setOnClickListener(this);
        make=findViewById(R.id.make);
        color=findViewById(R.id.color);
        image=findViewById(R.id.image);
        selectImage=findViewById(R.id.selectImage);
        selectImage.setOnClickListener(this);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit) {
            validateField();
        }
        if (v.getId() == R.id.vehicle_type){
            selectVehicleType();
        }
        if (v.getId() == R.id.selectImage){
            ImagePicker.create(this)
                    .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.
                    .folderMode(true) // folder mode (false by default)
                    .toolbarFolderTitle("Folder") // folder selection title
                    .toolbarImageTitle("Tap to select") // image selection title
                    .toolbarArrowColor(Color.BLACK) // Toolbar 'up' arrow color
                    .includeVideo(true) // Show video on image picker
                    .single() // single mode
                    .limit(1) // max images can be selected (99 by default)
                    .showCamera(true) // show camera or not (true by default)
                    .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                    .enableLog(false) // disabling log
                    .start(); // start image picker activity with request code
        }
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {

            List<Image> images = ImagePicker.getImages(data);
            Image image = ImagePicker.getFirstImageOrNull(data);
            File file=new File(images.get(0).getPath());
            Log.e("file-->", file+"");
            requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            body =
                    MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void selectVehicleType() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Choose vehicle type");
        builder.setItems(listItems, (dialog, which) -> vehicle_type.setText(listItems[which]));
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void validateField() {

        if (registration_no.getText().toString().length()==0){
            registration_no.setError("registration no can't be empty");
        }else if (vehicle_type.getText().toString().length()==0){
            vehicle_type.setError("Select vehicle type");
        }else {
            callAddVehicleApi("abc","abc","abc", body,"abc","black","yes");
        }

    }


    private void callAddVehicleApi(String token, String registration_no, String vehicle_type, MultipartBody.Part image, String make, String color, String primary) {

        // View Model
        AddVehicleViewModel forgetModel = ViewModelProviders.of(this).get(AddVehicleViewModel.class);
        forgetModel.getAddVehicleResponseLiveData(token,registration_no,vehicle_type,image,make,color,primary).observe(this, response -> {
            if (response != null) {
                Log.e("articleResponse ", App.getGson().toJson(response)+"  -->");
                if (response.getSuccess()){


                }else {

                }

            }
        });
    }

}