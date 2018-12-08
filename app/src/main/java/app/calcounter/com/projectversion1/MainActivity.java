package app.calcounter.com.projectversion1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // copy this into every activity
    // copy this into every activity
    // copy this into every activity
    // copy this into every activity
    private static final int RC_SIGN_IN = 123;
    List<AuthUI.IdpConfig> providers;
    // copy this into every activity
    // copy this into every activity
    // copy this into every activity
    // copy this into every activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // copy this into every activity
        // copy this into every activity
        // copy this into every activity
        // copy this into every activity
        // copy this into every activity

        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
        FirebaseAuth auth = FirebaseAuth.getInstance(); // this go into each intent?
        if (auth.getCurrentUser() != null) {
            // already signed in
        } else {
            // AuthUI is built in but does not exist yet?
            startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance().createSignInIntentBuilder().build(),
                    RC_SIGN_IN);
        }
        // copy this into every activity
        // copy this into every activity
        // copy this into every activity
        // copy this into every activity
        // copy this into every activity


    }
}
