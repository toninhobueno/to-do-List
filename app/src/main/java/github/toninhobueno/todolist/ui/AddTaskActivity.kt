package github.toninhobueno.todolist.ui


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import github.toninhobueno.todolist.databinding.ActivityAddTaskBinding
import github.toninhobueno.todolist.extentions.format
import github.toninhobueno.todolist.extentions.text
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import github.toninhobueno.todolist.datasource.TaskDataSource
import github.toninhobueno.todolist.model.Task
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


        binding.taskTime.editText?.setOnClickListener{
           val timePicker = MaterialTimePicker.Builder()
               .setTimeFormat(TimeFormat.CLOCK_24H)
               .build()

            timePicker.addOnPositiveButtonClickListener{

               val minute = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute

               val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour


                binding.taskTime.text = "$hour:$minute"
            }
            timePicker.show(supportFragmentManager,null)

        }

        binding.btnCancel.setOnClickListener {
           finish()
        }
        binding.btnCreateTask.setOnClickListener {
            val task = Task(
                title = binding.taskTitle.text ,
                description = binding.taskDescription.text ,
                date = binding.taskDate.text,
                hour = binding.taskTime.text

            )
           TaskDataSource.insertTask(task)
           finish()
        }

    }
}