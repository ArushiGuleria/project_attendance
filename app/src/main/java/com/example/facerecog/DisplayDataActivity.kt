//activity display data
package com.example.facerecog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facerecog.databinding.ActivityDisplayDataBinding
import com.example.facerecog.databinding.ActivityMainBinding


class DisplayDataActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    //private lateinit var dataList//: MutableList<attendanceData>
    // public lateinit var size: Int
    lateinit var dataList: MutableList<attendanceData>
    lateinit var aadapter: ItemAdapter
    private lateinit var binding: ActivityDisplayDataBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_display_data)

        binding = ActivityDisplayDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val view = binding.root

        var daList= intent.getSerializableExtra("itemList") as Array<attendanceData>
        dataList = daList.toMutableList<attendanceData>()
        Log.d("display", "List contents: $dataList")
        //size= dataList.size



        recyclerView = findViewById(R.id.myrecyclerView)


        // val binding = ActivityMainBinding.inflate(layoutInflater)

// Set up RecyclerView adapter
        var adapter = ItemAdapter(this,dataList)
        binding.myrecyclerView.adapter = adapter
        binding.myrecyclerView.layoutManager = LinearLayoutManager(this)

       // val itemDecoration = ItemAdapter.CustomItemDecoration(6)
       // binding.myrecyclerView.addItemDecoration(itemDecoration)

        //val bundle = intent.extras
        //dataList = bundle?.getSerializable("itemList") as MutableList<attendanceData>

        //binding.myrecyclerView.layoutManager = LinearLayoutManager(this)

        // val myAdapter = MyAdapter(dataList)
        //recyclerView.adapter = myAdapter
    }

    /* class DataAdapter(private val dataList: List<attendanceData>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
             return ViewHolder(view)
         }

         override fun onBindViewHolder(holder: ViewHolder, position: Int) {
             val currentItem = dataList[position]
             holder.textView1.text = currentItem.id
             holder.textView2.text = currentItem.name
         }

         override fun getItemCount() = dataList.size

         inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
             val textView1: TextView = itemView.findViewById(R.id.textView1)
             val textView2: TextView = itemView.findViewById(R.id.textView2)
         }
     }  */
    /* class MyAdapter(private val myList: MutableList<attendanceData>) :
         RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

         inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
             val textView1: TextView = itemView.findViewById(R.id.textView1)
             val textView2: TextView = itemView.findViewById(R.id.textView2)
         }

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
             val itemView = LayoutInflater.from(parent.context)
                 .inflate(R.layout.list_item, parent, false)
             return MyViewHolder(itemView)
         }

         override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
             for(i in 0 until (myList.size-1)) {
                 val currentItem = myList[i]
                 holder.textView1.text = currentItem.id
                 holder.textView2.text = currentItem.name
             }
         }

         override fun getItemCount() = myList.size
     }  */

    //setUpAdapter(){
    //  aadapter = ItemAdapter(this,dataList)
    //b.recyclerView.adapter = aadapter
    //b.foodItemsRV.layoutManager = LinearLayoutManager(this)
    //}

}
