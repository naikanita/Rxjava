package com.learning.samplerxjava.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.learning.samplerxjava.R
import com.learning.samplerxjava.model.Entry
import io.reactivex.rxjava3.subjects.PublishSubject
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val itemClick: PublishSubject<View> = PublishSubject.create()
    private var listValues = mutableListOf<Entry>()

    inner class ViewHolder : RecyclerView.ViewHolder {
        @BindView(R.id.item_name)
        lateinit var itemName: TextView

        @BindView(R.id.item_price)
        lateinit var itemPrice: TextView

        @BindView(R.id.item_date)
        lateinit var itemDate: TextView

        val PRICE_FORMAT = DecimalFormat("$#0.00")

        fun setName(name: String) {
            itemName.text = name
        }

        fun setPrice(value: BigDecimal) {
            itemPrice.text = PRICE_FORMAT.format(value.toDouble())
        }

        fun setDate(date: Date) {
            itemDate.text = android.text.format.DateFormat.format("yyyy-MM-dd hh:mm", date)
        }

        constructor(itemView: View) : super(itemView) {
            //initialize
            ButterKnife.bind(this, itemView)

            itemView.setOnClickListener { v ->
                itemClick.onNext(v)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry: Entry = listValues[position]
        holder.setName("Item:" + position + "\n" + "\n" + entry.name)
        holder.setPrice(entry.price)
        holder.setDate(entry.date)
    }

    override fun getItemCount(): Int {
        return listValues.size
    }

    fun addList(value: Entry) {
        listValues.add(value)
        //starts from 0
        notifyItemInserted(listValues.size - 1)
    }
}
