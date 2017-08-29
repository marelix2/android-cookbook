package cotam_kolego.cookbook.registerPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cotam_kolego.cookbook.App;
import cotam_kolego.cookbook.MainPackage.MainActivity;
import cotam_kolego.cookbook.R;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.nameEditText)
    EditText nameEditText;
    @BindView(R.id.surnameEditText)
    EditText surnameEditText;
    @BindView(R.id.emailEditText)
    EditText emailEditText;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;

    private RegisterMenager registerMenager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerMenager = ((App)getApplication()).getRegisterMenager();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_register){
            tryToRegister();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerMenager.onAttach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        registerMenager.onstop();
    }

    private void tryToRegister() {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String firstName = nameEditText.getText().toString();
        String lastName = surnameEditText.getText().toString();

        boolean hasErr = false;

        if (firstName.isEmpty()) {
            nameEditText.setError("This Field can\'t be empty");
            hasErr = true;
        }

        if (lastName.isEmpty()) {
            surnameEditText.setError("This Field can\'t be empty");
            hasErr = true;
        }

        if (email.isEmpty()) {
            emailEditText.setError("This Field can\'t be empty");
            hasErr = true;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Wrong e-mail address");
            hasErr = true;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("This Field can\'t be empty");
            hasErr = true;
        } else if (password.length() < 8) {
            passwordEditText.setError("password too short");
            hasErr = true;
        }

        if (!hasErr) {

            registerMenager.register(firstName,lastName,email,password);

        }

    }

    public void registerSuccesful() {

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void showError(String Error) {


    }

    public void showProgress(boolean b) {
    }
}
