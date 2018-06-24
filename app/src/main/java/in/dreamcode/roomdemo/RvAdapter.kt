package `in`.dreamcode.roomdemo

import `in`.dreamcode.roomdemo.Dao.NoteModel
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

class RvAdapter(private var noteList: List<NoteModel>, private var context: Context) : RecyclerView.Adapter<RvAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(context).inflate(R.layout.row_recycler_view,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val model: NoteModel = getItem(position)
        holder.titleText.text = model.title
    }

    private fun getItem(position: Int): NoteModel {
        return noteList[position]
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.row_text_view_title)
    }
}