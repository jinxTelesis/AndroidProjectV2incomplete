package app.calcounter.com.projectversion1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }

    public void openComments(View view)
    {
        startActivity(new Intent(this, CommentActivity.class));
    }

    public void openMaterials(View view)
    {
        startActivity(new Intent(this, MaterialActivity.class));
    }
}
