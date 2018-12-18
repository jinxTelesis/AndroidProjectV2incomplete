package app.calcounter.com.projectversion1;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class InspectionActivityRuss extends AppCompatActivity
{
    ArrayList<String> location = new ArrayList<String>();




    String actualLocation;
    int locationSelected = 0;
    ImageView gallery;
//Button btn_generate = (Button)findViewById(R.id.generate);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspectionruss);
        LinearLayout  linearLayout;

        gallery = (ImageView)findViewById(R.id.galleryView);

        final Switch kit_ceiling_switch = (Switch) findViewById(R.id.ceilingSwitch);
        final Switch kit_door_switch = (Switch) findViewById(R.id.doorSwitch);
        final Switch kit_floor_switch = (Switch) findViewById(R.id.floorSwitch);
        final Switch kit_fridge_switch = (Switch) findViewById(R.id.fridgeSwitch);
        final Switch kit_stove_switch = (Switch) findViewById(R.id.stoveSwitch);
        final Switch kit_microwave_switch = (Switch) findViewById(R.id.microwaveSwitch);
        final Switch kit_dishwasher_switch = (Switch) findViewById(R.id.dishwasherSwitch);
        final Switch kit_appliance_switch = (Switch) findViewById(R.id.applianceSwitch);
        final Switch kit_plumbing_switch = (Switch) findViewById(R.id.plumbingSwitch);
        final Switch kit_cabinets_switch = (Switch) findViewById(R.id.cabinetSwitch);
        final Switch kit_electrical_switch = (Switch) findViewById(R.id.electricalSwitch);
        final Switch kit_windows_switch = (Switch) findViewById(R.id.windowSwitch);
        final Switch kit_other_switch = (Switch) findViewById(R.id.kitchenOtherSwitch);

        Switch din_door_switch = (Switch) findViewById(R.id.diningDoorSwitch);
        Switch din_wall_switch = (Switch) findViewById(R.id.diningWallSwitch);
        Switch din_ceiling_switch = (Switch) findViewById(R.id.diningCeilingSwitch);
        Switch din_floor_switch = (Switch) findViewById(R.id.diningFloorSwitch);
        Switch din_electrical_switch = (Switch) findViewById(R.id.diningElectricalSwitch);
        Switch din_windows_switch = (Switch) findViewById(R.id.diningWindowsSwitch);
        Switch din_furniture_switch = (Switch) findViewById(R.id.diningFurnitureSwitch);
        Switch din_other_switch = (Switch) findViewById(R.id.diningOtherSwitch);

        Switch liv_door_switch = (Switch) findViewById(R.id.livingDoorSwitch);
        Switch liv_wall_switch = (Switch) findViewById(R.id.livingWallSwitch);
        Switch liv_ceiling_switch = (Switch) findViewById(R.id.livingCeilingSwitch);
        Switch liv_floor_switch = (Switch) findViewById(R.id.livingFloorSwitch);
        Switch liv_electrical_switch = (Switch) findViewById(R.id.livingElectricalSwitch);
        Switch liv_windows_switch = (Switch) findViewById(R.id.livingWindowSwitch);
        Switch liv_furniture_switch = (Switch) findViewById(R.id.livingFurnitureSwitch);

        Switch den_door_switch = (Switch) findViewById(R.id.denDoorSwitch);
        Switch den_wall_switch = (Switch) findViewById(R.id.denWallSwitch);
        Switch den_ceiling_switch = (Switch) findViewById(R.id.denCeilingSwitch);
        Switch den_floor_switch = (Switch) findViewById(R.id.denFloorSwitch);
        Switch den_electrical_switch = (Switch) findViewById(R.id.denElectricalSwitch);
        Switch den_windows_switch = (Switch) findViewById(R.id.denWindowSwitch);
        Switch den_furniture_switch = (Switch) findViewById(R.id.denFurnitureSwitch);

        EditText kit_ceiling_et = (EditText) findViewById(R.id.etCeiling);
        EditText kit_door_et = (EditText) findViewById(R.id.etDoor);
        EditText kit_floor_et = (EditText) findViewById(R.id.etFloor);
        EditText kit_fridge_et = (EditText) findViewById(R.id.etFridge);
        EditText kit_stove_et = (EditText) findViewById(R.id.etStove);
        EditText kit_microwave_et = (EditText) findViewById(R.id.etMicrowave);
        EditText kit_dishwasher_et = (EditText) findViewById(R.id.etDishwasher);
        EditText kit_appliances_et = (EditText) findViewById(R.id.etAppliance);
        EditText kit_plumbing_et = (EditText) findViewById(R.id.etPlumbing);
        EditText kit_cabinets_et = (EditText) findViewById(R.id.etCabinets);
        EditText kit_windows_et = (EditText) findViewById(R.id.etWindows);
        EditText kit_other_et = (EditText) findViewById(R.id.etKitchenOther);

        EditText din_door_et = (EditText) findViewById(R.id.etDiningDoor);
        EditText din_walls_et = (EditText) findViewById(R.id.etDiningWalls);
        EditText din_ceiling_et = (EditText) findViewById(R.id.etDiningCeiling);
        EditText din_floor_et = (EditText) findViewById(R.id.etDiningFloor);
        EditText din_electrical_et = (EditText) findViewById(R.id.etDiningElectrical);
        EditText din_windows_et = (EditText) findViewById(R.id.etDiningWindows);
        EditText din_furniture_et = (EditText) findViewById(R.id.etDiningFurniture);
        EditText din_other_et = (EditText) findViewById(R.id.etDiningOther);

        EditText liv_door_et = (EditText) findViewById(R.id.etLivingDoor);
        EditText liv_walls_et = (EditText) findViewById(R.id.etLivingWalls);
        EditText liv_ceiling_et = (EditText) findViewById(R.id.etLivingCeiling);
        EditText liv_floor_et = (EditText) findViewById(R.id.etLivingFloor);
        EditText liv_electrical_et = (EditText) findViewById(R.id.etLivingElectrical);
        EditText liv_windows_et = (EditText) findViewById(R.id.etLivingWindows);
        EditText liv_furniture_et = (EditText) findViewById(R.id.etLivingFurniture);

        EditText den_door_et = (EditText) findViewById(R.id.etDenDoor);
        EditText den_walls_et = (EditText) findViewById(R.id.etDenWalls);
        EditText den_ceiling_et = (EditText) findViewById(R.id.etDenCeiling);
        EditText den_floor_et = (EditText) findViewById(R.id.etDenFloor);
        EditText den_electrical_et = (EditText) findViewById(R.id.etDenElectrical);
        EditText den_windows_et = (EditText) findViewById(R.id.etDenWindows);
        EditText den_furniture_et = (EditText) findViewById(R.id.etDenFurniture);

        final String[] location_array = {"Select a Location","226 Feustal St", "43 Ketcham Drive", "24 Penataquit St", "17 First Ave"};//initialize locations

        Spinner spinner = (Spinner) findViewById(R.id.location_spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_itemruss, location_array);   //create adapter for the priority spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locationSelected = position;
                //actualLocation =
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinner.setAdapter(adapter);


        CompoundButton.OnCheckedChangeListener multiListener = new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton v, boolean isChecked)   //listens to change in yes/no switch
            {
                Intent intent;
                switch (v.getId())
                {
                    case R.id.ceilingSwitch:
                        if(kit_ceiling_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);          //opens camera
                        }break;

                    case R.id.doorSwitch:
                        if(kit_door_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                    case R.id.floorSwitch:
                        if(kit_floor_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                    case R.id.fridgeSwitch:
                        if(kit_fridge_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                    case R.id.stoveSwitch:
                        if(kit_stove_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                    case R.id.microwaveSwitch:
                        if(kit_microwave_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                    case R.id.dishwasherSwitch:
                        if(kit_dishwasher_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;
                    case R.id.applianceSwitch:
                        if(kit_appliance_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                    case R.id.plumbingSwitch:
                        if(kit_plumbing_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                    case R.id.cabinetSwitch:
                        if(kit_cabinets_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                    case R.id.electricalSwitch:
                        if(kit_electrical_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                    case R.id.windowSwitch:
                        if(kit_windows_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                    case R.id.kitchenOtherSwitch:
                        if(kit_other_switch.isChecked())
                        {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }break;

                }
            }
        };
        ((Switch) findViewById(R.id.ceilingSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.doorSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.floorSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.fridgeSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.stoveSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.microwaveSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.dishwasherSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.applianceSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.plumbingSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.cabinetSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.electricalSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.windowSwitch)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.kitchenOtherSwitch)).setOnCheckedChangeListener(multiListener);





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        gallery.setImageBitmap(bitmap);
    }

    public void showMessage(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }



    public void generateInspection(View view)
    {
        if(locationSelected==0)showMessage("Select a location");
    }



    public void submitInspection(View view)
    {
        if(locationSelected==0)showMessage("Select a location");
        else {
            startActivity(new Intent(this, AdministrativeActivityRuss.class));
        }

    }
}

