package com.learning.samplerxjava.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.learning.samplerxjava.R
import com.learning.samplerxjava.model.Entry
import io.reactivex.rxjava3.core.Observable
import java.math.BigDecimal
import java.util.*

class RxJavaActivity : AppCompatActivity() {

    @BindView(R.id.textview_title)
    lateinit var textView: TextView

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    private lateinit var linerLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rxjava_activity)

        setUpData()
    }

    private fun setUpData() {

        ButterKnife.bind(this)

        linerLayoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter()
        recyclerView.layoutManager = linerLayoutManager
        recyclerView.adapter = adapter

        Observable.just("Trying to set Data Via RX Java")
            .subscribe { t -> textView.text = t }

        val entry1 = Entry("AAA", BigDecimal.valueOf(1500), Date())
        val entry2 = Entry("BBB", BigDecimal.valueOf(100), Date())
        val entry3 = Entry("GCC", BigDecimal.valueOf(500), Date())
        val entry4 = Entry("ADDED", BigDecimal.valueOf(100), Date())
        val entry5 = Entry("EEEE", BigDecimal.valueOf(150), Date())
        val entry6 = Entry("FAFF", BigDecimal.valueOf(15000), Date())
        val entry7 = Entry("WII", BigDecimal.valueOf(0), Date())
        val entry8 = Entry("HAJJ", BigDecimal.valueOf(10), Date())
        val entry9 = Entry("KKK", BigDecimal.valueOf(500), Date())
        val entry10 = Entry("L¬LL", BigDecimal.valueOf(1500), Date())
        Observable.just(
            entry1,
            entry2,
            entry3,
            entry4,
            entry5,
            entry6,
            entry7,
            entry8,
            entry9,
            entry10
        ).subscribe { t -> adapter.addList(t) }

    }
}