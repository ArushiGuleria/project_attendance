package com.example.facerecog

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.facerecog.databinding.ListItemBinding

class ItemAdapter(private val context: Context, private val itemList:MutableList<attendanceData>)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(context),parent,false)
        binding.root.setPadding(0, 1, 0, 1)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    class ItemViewHolder(itemLayoutBinding: ListItemBinding)
        : RecyclerView.ViewHolder(itemLayoutBinding.root){

        private val binding = itemLayoutBinding

        fun bind(item: attendanceData){
            binding.textView1.text = item.id
            binding.textView2.text = item.name
            // binding.foodItemPriceTV.text = "Rs. ${foodItem.price}"
        }

    }
    class CustomItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = verticalSpaceHeight
        }
    }
}