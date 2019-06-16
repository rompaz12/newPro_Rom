package comr.example.student.romproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    Button back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        back2=(Button)findViewById(R.id.back2);
        back2.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (back2 == v) {
            Intent intent = new Intent(Main3Activity.this, MainActivity.class);
            startActivity(intent);

        }
    }

}