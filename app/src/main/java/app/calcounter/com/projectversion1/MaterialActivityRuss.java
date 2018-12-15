package app.calcounter.com.projectversion1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MaterialActivityRuss extends AppCompatActivity
{
TextView material;
EditText description, price;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materialruss);
    }


    public void addMaterial(View view)
    {
        String editTextValue = description.getText().toString();
        material = (TextView)findViewById(R.id.textView15);
        material.setText(editTextValue);


    }
}
