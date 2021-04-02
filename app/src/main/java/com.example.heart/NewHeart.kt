package heart

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.heart.R

class NewHeart : AppCompatActivity(){

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newheart)
        val editSys = findViewById<EditText>(R.id.editsys)
        val editDys = findViewById<EditText>(R.id.editdys)
        val editPul =  findViewById<EditText>(R.id.editpul)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editSys.text) || TextUtils.isEmpty(editDys.text) || TextUtils.isEmpty(editPul.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY, ("" + editSys.text + "/" + editDys.text + "/" + editPul.text))
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.heart.extra.REPLY"
    }

}