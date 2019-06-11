package com.bwie.okhttpdemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bwie.okhttpdemo.entity.PersonEntity;
import com.bwie.okhttpdemo.gen.DaoMaster;
import com.bwie.okhttpdemo.gen.DaoSession;
import com.bwie.okhttpdemo.gen.PersonEntityDao;

public class GreendaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
    }

    /**
     * 增加人员数据到sqlite表中
     * @param view
     */
    public void add(View view) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName("zhangsan");
        personEntity.setAge("100");

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
       DaoSession  daoSession = daoMaster.newSession();
        PersonEntityDao personEntityDao = daoSession.getPersonEntityDao();

        personEntityDao.insert(personEntity);

        PersonEntity personEntity2 = new PersonEntity();
        personEntity.setName("zhangsan");
        personEntity.setAge("100");

        personEntityDao.delete(personEntity2);

        personEntity.setName("lisi");
        personEntityDao.update(personEntity);

    }
}
