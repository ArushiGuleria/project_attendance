package com.example.facerecog
//activity attend
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
//import com.example.facerecog.databinding.ActivityDisplayBinding
import com.example.facerecog.databinding.ActivityDisplayDataBinding
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONObject

val storage = FirebaseStorage.getInstance()
val storageRef = storage.reference


class ActivityAttend : AppCompatActivity() {
    lateinit var videoUri: Uri
    var videoUrl: String= " "
    val PICK_VIDEO_REQUEST_CODE = 1
    //val REQUEST_CODE_BUTTON_22 = 2

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attend)


        textView = findViewById(R.id.text_view)
        val button21 = findViewById<Button>(R.id.button_video)
        button21.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "video/*"

// Launch the intent and wait for a result
            startActivityForResult(intent, PICK_VIDEO_REQUEST_CODE)


        }

        val button22 = findViewById<Button>(R.id.button_submit)
        button22.setOnClickListener {
            //send video to server
            //url is in variable videoUrl
            //val intent = Intent(this, DisplayDataActivity::class.java)
            //startActivity(intent)



            fetchDataFromServer(videoUrl)

            //}
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_VIDEO_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            textView.text = "File selected"
            textView.visibility = View.VISIBLE
            // Get the URI of the selected video
            videoUri = data!!.data!!

            // Create a reference to the FirebaseStorage location where the video will be uploaded
            val videoRef = storageRef.child("videos/${videoUri.lastPathSegment}")

            // Upload the video to FirebaseStorage
            val uploadTask = videoRef.putFile(videoUri)

            // Monitor the upload progress and handle any errors
            uploadTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Get the download URL of the uploaded video
                    videoRef.downloadUrl.addOnSuccessListener { uri ->
                        videoUrl = uri.toString()
                        // Use the video URL as needed
                    }.addOnFailureListener { exception ->
                        // Handle any errors while getting the download URL
                    }
                } else {
                    // error handle when uploading video
                }
            }
        }
        //else if(requestCode== REQUEST_CODE_BUTTON_22)
        //start activity to upload to server
    }

    private fun fetchDataFromServer(videoUrl: String) {

        // videoUrl is from firebase storage url


        val queue = Volley.newRequestQueue(this)
        val url = "https://api.github.com/users/octocat"//"https://jsonplaceholder.typicode.com/comments"//"https://headers.jsontest.com/"
        val itemList = mutableListOf<attendanceData>()

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->


                val iterator = response.keys()
                while (iterator.hasNext()) {
                    val id = iterator.next()
                    val name = response.get(id)   as? String
                    itemList.add(attendanceData(id, name))

                    /* if name == "0" continue
                    then display ony the contents of the keys that is the roll numbers present
                    which it is already doing
                    * */
                }

                val intent = Intent(this, DisplayDataActivity::class.java)
                intent.putExtra("itemList", itemList.toTypedArray())
                this.startActivity(intent)


            },
            { error ->
                // display exception name
                Toast.makeText(
                    this,
                    "Error fetching data: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        queue.add(jsonObjectRequest)
    }
}