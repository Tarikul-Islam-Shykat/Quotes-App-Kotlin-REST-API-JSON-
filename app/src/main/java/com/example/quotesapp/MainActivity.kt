package com.example.quotesapp

import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.quotesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding
    var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RequestManager(this).GetAllQuotes(listener)
        dialog = ProgressDialog(this)
        dialog?.setTitle("Loading")
        dialog?.show()
    }
    private  val listener: QuptesResponseListener = object: QuptesResponseListener{
        override fun didFecth(response: List<QuotesModel>, message: String) {
            dialog?.dismiss()
            binding.recyclerHome.setHasFixedSize(true)
            //binding.recyclerHome.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL) // this for grid layout,
            binding.recyclerHome.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val adapter = QuotesAdapter(this@MainActivity, response,copy_Listener )
            binding.recyclerHome.adapter = adapter
        }
        override fun didError(message: String) {
            dialog?.dismiss()
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    private  val copy_Listener: copyListener = object : copyListener{
        override fun onCopyClicked(text: String) {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("copied data", text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(applicationContext, "Copied", Toast.LENGTH_SHORT).show()
        }
    }
}