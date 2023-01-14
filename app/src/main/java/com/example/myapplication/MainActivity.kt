package com.example.myapplication
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickAddName(view: View?) {
// Add a new student record
        val values = ContentValues()
        values.put(
            PetProvider.petitem,
            (findViewById<View>(R.id.editText2) as EditText).text.toString()
        )
        values.put(
            PetProvider.Price,
            (findViewById<View>(R.id.editText3) as EditText).text.toString()
        )
        val uri = contentResolver.insert(
            PetProvider.CONTENT_URI, values
        )
        Toast.makeText(baseContext, uri.toString(), Toast.LENGTH_LONG).show()
    }


    @SuppressLint("Range")
    fun onClickRetrieveStudents(view: View?) {
        // Retrieve student records
        val URL = "content://com.example.MyApplication.StudentsProvider"
        val students = Uri.parse(URL)
        //\  val c = contentResolver!!.query(students,null,null,null,"name"
        var c = contentResolver.query(students, null, null, null, null)
        //val //c = managedQuery(students, null, null, null, "name")
        if (c != null) {
            if (c?.moveToFirst()) {
                do {

                    Toast.makeText(this,
                        c.getString(c.getColumnIndex(PetProvider._ID)) + ", " + c.getString(c.getColumnIndex(
                            PetProvider.petitem)) + ", " + c.getString(c.getColumnIndex(
                            PetProvider.Price)),
                        Toast.LENGTH_SHORT).show()
                } while (c.moveToNext())
            }
        }
    }
}
