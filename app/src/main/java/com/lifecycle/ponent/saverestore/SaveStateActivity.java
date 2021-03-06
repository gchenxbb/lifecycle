package com.lifecycle.ponent.saverestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lifecycle.ponent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 在第二个Activity中配置转屏，观察Activity重建时RecycleView的状态
 * 异常销毁时，顺序:onPause -> onStop -> onSaveInstanceState ->  onDestroy
 * 进入后台时,（home 或 被压住 ）顺序:onPause -> onStop -> onSaveInstanceState
 * 重建启动时，顺序:onCreate -> onStart -> onRestoreInstanceState -> onResume
 */
public class SaveStateActivity extends AppCompatActivity {
    private String TAG = "SaveStateActivityTAG";
    private RecycleViewAdapter mRecycleViewAdapter;
    private RecyclerView mRecyvleView;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getClass().getSimpleName() + " onCreate");
        setContentView(R.layout.activity_save);
        initView();
    }

    private void initView() {
        initData();
        mRecyvleView = (RecyclerView) findViewById(R.id.rlv_two);
        mRecycleViewAdapter = new RecycleViewAdapter(this, mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyvleView.setLayoutManager(layoutManager);
        mRecyvleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyvleView.setAdapter(mRecycleViewAdapter);

        findViewById(R.id.tv_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaveStateActivity.this, SaveSecondActivity.class));
            }
        });
    }

    private void initData() {
        mList.add("-1");
        mList.add("-2");
        mList.add("-3");
        mList.add("-4");
        mList.add("-5");
        mList.add("-6");
        mList.add("-7");
        mList.add("-8");
        mList.add("-9");
        mList.add("-10");
        mList.add("-11");
        mList.add("-12");
        mList.add("-13");
        mList.add("-14");
        mList.add("-15");
        mList.add("-16");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);//savedInstanceStatet一定存在
        Log.d(TAG, getClass().getSimpleName() + " onRestoreInstanceState " + savedInstanceState.getString("savedTest"));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("savedTest", "savedTextHi");
        Log.d(TAG, getClass().getSimpleName() + " onSaveInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, getClass().getSimpleName() + " onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, getClass().getSimpleName() + " onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, getClass().getSimpleName() + " onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, getClass().getSimpleName() + " onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, getClass().getSimpleName() + " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, getClass().getSimpleName() + " onDestroy");
    }
}
