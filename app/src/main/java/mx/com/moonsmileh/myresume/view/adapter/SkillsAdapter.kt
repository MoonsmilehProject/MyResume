package mx.com.moonsmileh.myresume.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview_skill_item.view.*
import mx.com.moonsmileh.myresume.R

class SkillsAdapter : RecyclerView.Adapter<SkillsAdapter.ViewHolder>() {

    var skills = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skill = skills[position]
        holder.tvSkill.text = skill
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.cardview_skill_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = skills.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSkill = view.textview_skill
    }


}