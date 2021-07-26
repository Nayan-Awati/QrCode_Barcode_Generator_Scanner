package com.example.qrandbarcodescanner

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class GenerateQRCode: AppCompatActivity() {

    private  lateinit var qr_code : ImageView
    private  lateinit var etText : EditText
    private lateinit var btnGenerateQRCode : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.generate_qr_code)



        qr_code = findViewById(R.id.QRCode)
        etText = findViewById(R.id.etText)
        btnGenerateQRCode = findViewById(R.id.generateButton)

        btnGenerateQRCode.setOnClickListener{
            val data = etText.text.toString().trim()
            if(data.isEmpty()){
                Toast.makeText(this,"Enter some data", Toast.LENGTH_SHORT).show()

            }else{
                val writer = QRCodeWriter()
                try {
                    val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE,512,512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                    for(x in 0 until width){
                        for(y in 0 until height){
                            bmp.setPixel(x,y, if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    qr_code.setImageBitmap(bmp)


                }catch (e: WriterException){
                    e.printStackTrace()
                }
            }

        }

    }



}