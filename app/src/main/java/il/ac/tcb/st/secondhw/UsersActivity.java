package il.ac.tcb.st.secondhw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import il.ac.tcb.st.secondhw.databinding.ActivityMainBinding;
import il.ac.tcb.st.secondhw.databinding.ActivityUsersBinding;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;


public class UsersActivity extends AppCompatActivity {
    private ActivityUsersBinding binding;
    ArrayList<MyPicks> saveCollection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView recyclerView = binding.myRecyclerView;
        saveCollection = (ArrayList<MyPicks>) this.getIntent().getSerializableExtra("content");
        PL_RecyclerViewAdpater adpater = new PL_RecyclerViewAdpater(this,saveCollection);
        recyclerView.setAdapter(adpater);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}