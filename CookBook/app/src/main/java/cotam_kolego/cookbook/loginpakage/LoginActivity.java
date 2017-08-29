package cotam_kolego.cookbook.loginpakage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cotam_kolego.cookbook.App;
import cotam_kolego.cookbook.MainPackage.MainActivity;
import cotam_kolego.cookbook.R;
import cotam_kolego.cookbook.registerPackage.RegisterActivity;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.emailText)
    EditText emailText;
    @BindView(R.id.passwordText)
    EditText passwordText;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.RegisterButton)
    Button RegisterButton;
    private LoginMenager loginMenager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginMenager = ((App) getApplication()).getLoginMenager();


    }

    @Override
    protected void onStart() {
        super.onStart();

        loginMenager.onAttach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginMenager.onStop();
    }



    @OnClick(R.id.RegisterButton)
    public void onRegisterClicked() {

        startActivity(new Intent(this, RegisterActivity.class));

    }

    @OnClick(R.id.loginButton)
    public void onLoginClicked() {

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        boolean hasErr = false;
        if(email.isEmpty()){
            emailText.setError("This Field can\'t be empty");
            hasErr = true;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Wrong e-mail address");
            hasErr = true;
        }

        if(password.isEmpty()){
            passwordText.setError("This Field can\'t be empty");
            hasErr = true;
        }else if(password.length() < 8){
            passwordText.setError("password too short");
            hasErr = true;
        }

        if(!hasErr){

            loginMenager.login(email,password);

        }

    }

    public void loginSucces() {

        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    public void loginFailed(String error) {
        Toast.makeText(LoginActivity.this, "" + error, Toast.LENGTH_SHORT).show();
    }
}

