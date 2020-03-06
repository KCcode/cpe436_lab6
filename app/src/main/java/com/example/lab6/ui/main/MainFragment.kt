package com.example.lab6.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter : StringAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.data.observe(viewLifecycleOwner, object : Observer<List<String>>{
            override fun onChanged(t: List<String>?) {
                adapter.notifyDataSetChanged()
            }
        })
        viewModel.query()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = StringAdapter()
        recyclerView.adapter = adapter
    }

    inner class StringAdapter : RecyclerView.Adapter<StringAdapter.StringViewHolder>(){
        inner class StringViewHolder(view:View) : RecyclerView.ViewHolder(view){
            private val textView = view as TextView
            fun bind(text:String){
                textView.text = text
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
            return StringViewHolder(view)
        }

        override fun getItemCount()= viewModel.data.value?.size ?: 0

        override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
            holder.bind(viewModel.data.value?.get(position) ?: "")
        }
    }

}
