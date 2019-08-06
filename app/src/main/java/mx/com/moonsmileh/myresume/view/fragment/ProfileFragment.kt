package mx.com.moonsmileh.myresume.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import mx.com.moonsmileh.myresume.R
import mx.com.moonsmileh.myresume.view.adapter.SkillsAdapter
import mx.com.moonsmileh.myresume.viewmodel.ProfileViewModel

class ProfileFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: SkillsAdapter
    private lateinit var viewLayoutManager: LinearLayoutManager
    private lateinit var viewModel: ProfileViewModel
    private lateinit var progressbar: ProgressBar
    private lateinit var tvResume: TextView
    private lateinit var ivAndroid: ImageView
    private lateinit var btnContact: FloatingActionButton


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        viewLayoutManager = LinearLayoutManager(activity)
        tvResume = view.findViewById(R.id.textview_resume)
        ivAndroid = view.findViewById(R.id.imageview_android)
        progressbar = view.findViewById(R.id.progressbar_loader)
        btnContact = view.findViewById(R.id.fab_contact)


        viewAdapter = SkillsAdapter()
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_skills).apply {
            layoutManager = viewLayoutManager
            adapter = viewAdapter
        }

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        observeSkillList()


        btnContact.setOnClickListener {
            sendEmail()
        }

        return view
    }


    private fun observeSkillList() {
        viewModel.response.observe(this, Observer { profile ->
            viewAdapter.skills = profile.skills
            tvResume.text = profile.resume
            ivAndroid.visibility = View.VISIBLE
            viewAdapter.notifyDataSetChanged()
            showOrHideLoader(false, progressbar)

        })
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.email))
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body))

        startActivity(intent)
    }


}