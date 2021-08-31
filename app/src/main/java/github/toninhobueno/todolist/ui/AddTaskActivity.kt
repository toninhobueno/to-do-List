package github.toninhobueno.todolist.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import github.toninhobueno.todolist.databinding.ActivityAddTaskBinding
import github.toninhobueno.todolist.extentions.format
import github.toninhobueno.todolist.extentions.text
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)


        insertListeners()
    }

    private fun insertListeners() {
        binding.taskDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offSet = timeZone.getOffset(Date().time) * -1
                binding.taskDate.text = Date(it + offSet).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

    }
}