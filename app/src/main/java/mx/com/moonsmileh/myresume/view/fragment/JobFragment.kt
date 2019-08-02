package mx.com.moonsmileh.myresume.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.com.moonsmileh.myresume.R
import mx.com.moonsmileh.myresume.view.adapter.JobAdapter
import mx.com.moonsmileh.myresume.viewmodel.ResumeViewModel

class JobFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: JobAdapter
    private lateinit var viewLayoutManager: LinearLayoutManager
    private lateinit var viewModel: ResumeViewModel
    private lateinit var progressbar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_job, container, false)

        viewLayoutManager = LinearLayoutManager(activity)
        progressbar = view.findViewById(R.id.progressbar_loader)
        viewAdapter = JobAdapter()
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_jobs).apply {
            layoutManager = viewLayoutManager
            adapter = viewAdapter
        }

        viewModel = ViewModelProviders.of(this).get(ResumeViewModel::class.java)
        observeJobList()

        return view
    }

    private fun observeJobList() {
        viewModel.response.observe(this, Observer { jobs ->
            viewAdapter.jobs = jobs
            viewAdapter.notifyDataSetChanged()
            showOrHideLoader(false, progressbar)
        })
    }


}