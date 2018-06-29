package `in`.dreamcode.roomdemo

import `in`.dreamcode.roomdemo.Dao.AppDatabase
import `in`.dreamcode.roomdemo.Dao.DbDao
import `in`.dreamcode.roomdemo.Dao.NoteModel
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onClick(view: View?) {

        val noteModel = NoteModel()
        noteModel.title = mInputText?.text.toString()
        noteModel.dataTime = System.currentTimeMillis()
        noteModel.description = noteModel.title.plus(noteModel.dataTime)
        val insertStatus = mDbDao?.insertNote(noteModel)
        log("insert status ".plus(insertStatus))
        getAllNotes()
        mInputText?.setText("")
    }

    private fun log(info: String) {
        Log.e("MainActivity ", info)
    }

    private var mInputText: EditText? = null
    private var mBtn: Button? = null
    private var mRcView: RecyclerView? = null

    private var noteList: MutableList<NoteModel> = ArrayList()
    private var noteAdapter: RvAdapter? = null


    private var mDbDao: DbDao? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        getAllNotes()

        mBtn?.setOnClickListener(this@MainActivity)
    }

    private fun initViews() {

        mDbDao = AppDatabase.getInstance(applicationContext).dbDao()

        mInputText = findViewById(R.id.editText)
        mBtn = findViewById(R.id.button)
        mRcView = findViewById(R.id.recycler_view)

        val layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false)

        mRcView?.layoutManager = layoutManager

        noteAdapter = RvAdapter(noteList, this@MainActivity)
        mRcView?.adapter = noteAdapter

        noteAdapter?.setOnItemClickListener(mOnItemClickListener)

    }

    private fun getAllNotes() {
        val listData = mDbDao?.getAllNote() as List<NoteModel>

        noteList.clear()
        for (i in listData.indices) {
            noteList.add(listData[i])
        }

        noteAdapter?.notifyDataSetChanged()
    }

    private val mOnItemClickListener: RvAdapter.OnItemClickListener = object : RvAdapter.OnItemClickListener {
        override fun onClick(position: Int) {
            val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)
            secondIntent.putExtra(SecondActivity.MODEL_ID, noteList[position].id)
            startActivity(secondIntent)
        }
    }

}