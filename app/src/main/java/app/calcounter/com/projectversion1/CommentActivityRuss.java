package app.calcounter.com.projectversion1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class CommentActivityRuss extends AppCompatActivity {

    ImageView gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentruss);

        gallery = (ImageView)findViewById(R.id.galleryView);
    }

    public void openMaterials(View view)
    {
        startActivity(new Intent(this, MaterialActivityRuss.class));
    }

    public void addImage(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);          //opens camera
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        gallery.setImageBitmap(bitmap);
    }
}
