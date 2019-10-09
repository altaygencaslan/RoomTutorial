package com.blogspot.altaygencaslan.room_tutorial1.Room.DataAdapter

import android.app.AlertDialog
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.blogspot.altaygencaslan.room_tutorial1.R
import com.blogspot.altaygencaslan.room_tutorial1.Room.DataBase.AppDatabase
import com.blogspot.altaygencaslan.room_tutorial1.Room.Entity.Employe
import kotlinx.android.synthetic.main.lv_employe.view.*

class Adapter(context: Context, resource: Int, objects: List<Employe>) :
    ArrayAdapter<Employe>(context, resource, objects) {
    var resource = resource

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var retView: View = LayoutInflater.from(context).inflate(resource, null)
        lateinit var txtId: TextView
        lateinit var txtFullName: TextView
        lateinit var txtTitle: TextView
        lateinit var btnDelete: ImageButton

        var employe: Employe = getItem(position)
        if (convertView == null) {
            txtId = retView.findViewById<TextView>(R.id.tvId)
            txtFullName = retView.findViewById<TextView>(R.id.tvName)
            txtTitle = retView.findViewById<TextView>(R.id.tvTitle)
            btnDelete = retView.findViewById<ImageButton>(R.id.btnDelete)


        } else {
            txtId = convertView.findViewById<TextView>(R.id.tvId)
            txtFullName = convertView.findViewById<TextView>(R.id.tvName)
            txtTitle = convertView.findViewById<TextView>(R.id.tvTitle)
            btnDelete = convertView.findViewById<ImageButton>(R.id.btnDelete)
        }

        txtId.text = employe.uid.toString()
        txtFullName.text = employe.fullname
        txtTitle.text = employe.title
        btnDelete.setOnClickListener {
            var id: Int = employe.uid

            var builderConfirm: AlertDialog.Builder = AlertDialog.Builder(context);
            builderConfirm.setCancelable(true)
            builderConfirm.setTitle("Confirm Header")
            builderConfirm.setMessage("$id no'lu ${employe.fullname} kaydını silmek istediğniizden emin misiniz?")
            builderConfirm.setPositiveButton("Evet") { dialog, which ->
                AppDatabase.getDbContext(context).employeDao().deleteEmployeById(id)
                Toast.makeText(context, "${employe.fullname} kaydı başarıyla silindi", Toast.LENGTH_LONG).show()
            }
            builderConfirm.show()
        }

        if (convertView == null)
            return retView
        else
            return convertView
    }
}