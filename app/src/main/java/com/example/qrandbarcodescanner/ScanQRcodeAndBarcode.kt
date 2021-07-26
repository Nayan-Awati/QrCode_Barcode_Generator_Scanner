package com.example.qrandbarcodescanner

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.scan_qr_and_barcode.*

class ScanQRcodeAndBarcode:AppCompatActivity() {

    private lateinit var  btScan : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_qr_and_barcode)

        
            //Initialize intent integration

            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setPrompt("For flash use volume up key")
            //set beep
            intentIntegrator.setBeepEnabled(true)
            //locked orientation
            intentIntegrator.setOrientationLocked(true)
            //set capture activity
            intentIntegrator.setCaptureActivity(Capture::class.java)
            //Initiate scan
            intentIntegrator.initiateScan()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intentResult = IntentIntegrator.parseActivityResult(
                            requestCode,resultCode,data
        )
        //check condition
        if(intentResult.contents != null){
            //when result content is not null
            //Initialize alert dialog
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Result")
            //set message
            builder.setMessage(intentResult.contents)
            //set positive button
            val positiveButtonClick = {dialogInterface: DialogInterface, i:Int ->
                dialogInterface.dismiss()
            }
            builder.setPositiveButton("Ok", DialogInterface.OnClickListener(function = positiveButtonClick ) )

            //show alert dialog
            builder.show()


        }
        else{
            // when result content is null
            // display
            Toast.makeText(getApplicationContext()
                            , "OOPS... You did not scan anything", Toast.LENGTH_SHORT).show()
        }
    }
}