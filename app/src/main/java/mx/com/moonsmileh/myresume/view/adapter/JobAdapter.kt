package mx.com.moonsmileh.myresume.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_job_item.view.*
import mx.com.moonsmileh.myresume.R
import mx.com.moonsmileh.myresume.model.Job

class JobAdapter : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    var jobs = listOf<Job>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.cardview_job_item, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount() = jobs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentJob = jobs[position]
        holder.tvCompanyName.text = currentJob.company
        holder.loadViewWithUrl(currentJob.imageUrl)

        holder.tvPosition.text = currentJob.position
        holder.tvJobDescription.text = currentJob.description
        holder.tvPeriod.text = currentJob.period
        holder.tvAchievements.text = currentJob.achievements

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivCompanyLogo: ImageView = view.iv_company
        val tvCompanyName: TextView = view.tv_company_name
        val tvPosition: TextView = view.tv_position
        val tvPeriod: TextView = view.tv_period
        val tvJobDescription: TextView = view.tv_job_description
        val tvAchievements: TextView = view.tv_job_achievments

        fun loadViewWithUrl(url: String) {
            Picasso.get().load(url).into(ivCompanyLogo)
        }
    }


}