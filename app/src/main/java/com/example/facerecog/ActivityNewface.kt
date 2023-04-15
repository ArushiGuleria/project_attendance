package com.example.facerecog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage

//val storage = FirebaseStorage.getInstance()
//val storageRef = storage.reference


class ActivityNewface : AppCompatActivity() {

    val PICK_VIDEO_REQUEST_CODE = 1
    //val SEND_TO_SERVER_REQUEST_CODE= 3
    lateinit var selected_video_text_view: TextView
    //lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newface)

        selected_video_text_view = findViewById(R.id.selected_video_text_view)

        var editTextname = findViewById<EditText>(R.id.editText1)
        editTextname.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val text1 = editTextname.text.toString()
                // Do something with the text
                true
            } else {
                false
            }
        }
        val editTextroll = findViewById<EditText>(R.id.editText2)
        editTextroll.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val text2 = editTextroll.text.toString()
                // Do something with the text
                true
            } else {
                false
            }
        }

        val button11 = findViewById<Button>(R.id.buttonvideo)
        button11.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_VIDEO_REQUEST_CODE)
        }

        val button12 = findViewById<Button>(R.id.buttonsubmit)
        //button12.setOnClickListener {
            // send to server    val intent=
           //startActivityForResult(intent,  SEND_TO_SERVER_REQUEST_CODE)
       // }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_VIDEO_REQUEST_CODE && resultCode == RESULT_OK && data != null) {


            val selectedVideoUri = data!!.data!!
            val videoRef = storageRef.child("videos/${selectedVideoUri.lastPathSegment}")
            val uploadTask = videoRef.putFile(selectedVideoUri)


            selected_video_text_view.text = "Selected video: $selectedVideoUri"
            selected_video_text_view.visibility = View.VISIBLE

            uploadTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Get the download URL of the uploaded video
                    videoRef.downloadUrl.addOnSuccessListener { uri ->
                        val videoUrl = uri.toString()
                        // Use the video URL as needed
                    }.addOnFailureListener { exception ->
                        // Handle any errors while getting the download URL
                    }
                } else {
                    // Handle any errors while uploading the video
                }
            }
        }
       // else if(requestCode==  SEND_TO_SERVER_REQUEST_CODE){
        //    //send to server
       // }
    }
}