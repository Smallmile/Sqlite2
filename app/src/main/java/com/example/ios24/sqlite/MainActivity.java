package com.example.ios24.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button createBtn;
    private Button insertBtn;
    private Button updateBtn;
    private Button queryBtn;
    private Button deleteBtn;
    private Button modifyBtn;
    private DBHelper dbHelper ;
    SQLiteDatabase db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBtn = findViewById(R.id.createDatabase);
        updateBtn = findViewById(R.id.updateDatabase);
        insertBtn = findViewById(R.id.insert);
        modifyBtn = findViewById(R.id.update);
        queryBtn = findViewById(R.id.query);
        deleteBtn = findViewById(R.id.delete);

        createBtn.setOnClickListener(new CreateListener());
        updateBtn.setOnClickListener(new UpdateListener());




    }

    class CreateListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            dbHelper = new DBHelper(MainActivity.this,"stu_db",null,1);
            db = dbHelper.getReadableDatabase();
        }
    }

    class UpdateListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            DBHelper dbHelper = new DBHelper(MainActivity.this,"stu_db",null,2);


    }
    }
    class InsertListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            db = dbHelper.getWritableDatabase();
            ContentValues cv =new ContentValues();
            cv.put("id",1);
            cv.put("sname","测试姓名1");
            cv.put("sage",10);
            cv.put("ssex","男");
            db.insert("stu",null,cv);
            db.close();


        }
    }

    class QueryListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Cursor cursor = db.query("stu",new String[]{"id","sname","sage","ssex"},
                    "id=?",new String[]{"1"},null,null,null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("sname"));
                String age = cursor.getString(cursor.getColumnIndex("sage"));
                String sex = cursor.getString(cursor.getColumnIndex("ssex"));


            }
            db.close();

        }
    }

    class ModifyListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ContentValues cv = new ContentValues();
            cv.put("sage","32");
            String whereClause = "id = ?";
            String []whereArgs  = {String.valueOf(1)};
            db.update("stu",cv,whereClause,whereArgs);
        }
    }
}
