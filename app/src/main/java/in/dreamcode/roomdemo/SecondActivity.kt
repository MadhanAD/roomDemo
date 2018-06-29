package `in`.dreamcode.roomdemo

import `in`.dreamcode.roomdemo.Dao.AppDatabase
import `in`.dreamcode.roomdemo.Dao.DbDao
import `in`.dreamcode.roomdemo.Dao.NoteModel
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    companion object {
        const val MODEL_ID ="MODEL_ID"
    }

    private var modelId: Long = 0
    private var mDb : DbDao ?= null
    private var mModel: NoteModel ?= null

    private var mTextView: TextView ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initViews()

        modelId = intent.getLongExtra(MODEL_ID,0)

        mDb = AppDatabase.getInstance(this@SecondActivity).dbDao()

        mModel = mDb?.getNoteById(modelId)

        mTextView?.text = mModel?.title

    }

    private fun initViews(){
        mTextView = findViewById(R.id.second_activity_text_view)
    }
}
