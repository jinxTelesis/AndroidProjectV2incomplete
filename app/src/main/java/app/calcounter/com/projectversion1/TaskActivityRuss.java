package app.calcounter.com.projectversion1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TaskActivityRuss extends AppCompatActivity
{
    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskruss);


    }



    public void openComments(View view)
    {
        startActivity(new Intent(this, CommentActivityRuss.class));
    }

    public void openMaterials(View view)
    {
        startActivity(new Intent(this, MaterialActivityRuss.class));
    }

    public void taskStart(View view)
    {TextView status = (TextView)findViewById(R.id.textView9);
        status.setText("Status: In Progress");
    }

    public void taskStop(View view)
    {TextView status = (TextView)findViewById(R.id.textView9);
        status.setText("Status: Incomplete");
    }

    public void taskComplete(View view)
    {TextView status = (TextView)findViewById(R.id.textView9);
        status.setText("Status: Complete");
    }
}
