package com.example.mydailycalories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn : Button = findViewById(R.id.btnid) //calculate button
        val cal : TextView = findViewById(R.id.caltxt) //resulted calories
        val weight : EditText = findViewById(R.id.weightnum) //weight edit text
        val height : EditText = findViewById(R.id.highetnum) //height edit text
        val age : EditText = findViewById(R.id.agenum) //age edit text
        var gender_flag : String ="Male" //default gender spinner
        var activity_flag : String ="Little or no exercise" //default activity level spinner
        val gender_spinner : Spinner = findViewById(R.id.genderspin) // gender spinner
        var gender_options = arrayOf("Male","Female") //gender list
        val activity_spinner : Spinner = findViewById(R.id.activityspin) //activity spinner
        var activity_options = arrayOf("Little or no exercise","1-3 days/week","3-5 days/week","6-7 days/week") //activity list
        gender_spinner.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,gender_options) //read from gender list
        activity_spinner.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,activity_options) //read from activity list
        btn.setOnClickListener{view ->
            var w : Float =weight.text.toString().toFloat() //read from edit texts
            var h : Float =height.text.toString().toFloat()
            var a : Int =age.text.toString().toInt()
            var BMR : Double //base metabolic rate (calories you burn naturally)
            if(gender_flag == "Male"){ //BMR based on gender
                BMR = 66.47+(13.75*w)+(5.003*h)-(6.755*a)
            }
            else{
                BMR = 655.1+(9.563*w)+(1.85*h)-(4.676*a)
            }
            cal.text = when (activity_flag){ //calories needed based on activity level
                "Little or no exercise" -> (BMR*1.2).toInt()
                "1-3 days/week" -> (BMR*1.375).toInt()
                "3-5 days/week" -> (BMR*1.55).toInt()
                "6-7 days/week" -> (BMR*1.725).toInt()
                else -> "Select activity level"
            }.toString()
        }
        gender_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{ //when gender spinner item selected
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                gender_flag=gender_options.get(p2) //put selected item in gender flag
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        activity_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{ //when activity spinner item selected
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                activity_flag=activity_options.get(p2) //put selected item in activity flag
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}