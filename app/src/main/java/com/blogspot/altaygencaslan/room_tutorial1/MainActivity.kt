package com.blogspot.altaygencaslan.room_tutorial1

import android.arch.persistence.room.Room
import java.util.Calendar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.blogspot.altaygencaslan.room_tutorial1.Room.DataAdapter.Adapter
import com.blogspot.altaygencaslan.room_tutorial1.Room.DataBase.AppDatabase
import com.blogspot.altaygencaslan.room_tutorial1.Room.Entity.Employe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var _db: AppDatabase? = null
    lateinit var _adapter: Adapter
    lateinit var listOfEmploye: List<Employe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Initialize()
    }

    private fun Initialize() {
        if (_db == null) {
            _db = AppDatabase.getDbContext(this)
            LoadData()

            srlMain.setOnRefreshListener {
                LoadData()
            }

            Toast.makeText(this, "Run Initialize", Toast.LENGTH_SHORT).show()
        }
    }

    private fun LoadData() {
        listOfEmploye = _db!!.employeDao().getAllEmployes()
        _adapter = Adapter(this, R.layout.lv_employe, listOfEmploye)
        lvEmploye.adapter = _adapter

        srlMain.isRefreshing = false
    }

    fun fabAdd_Click(v: View) {
        var calendar: Calendar = Calendar.getInstance()
        var miliseconds: String = calendar.get(Calendar.MILLISECOND).toString()
        var date: String = calendar.get(Calendar.DATE).toString()

        _db!!.employeDao()
            .insertNewEmploye(Employe("$miliseconds", "$date"))
    }
}
