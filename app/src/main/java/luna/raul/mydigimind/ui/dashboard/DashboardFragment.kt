package luna.raul.mydigimind.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import luna.raul.mydigimind.R
import luna.raul.mydigimind.databinding.FragmentDashboardBinding
import com.google.firebase.firestore.FirebaseFirestore
import luna.raul.mydigimind.ui.Task
import luna.raul.mydigimind.ui.home.HomeFragment

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val database = FirebaseFirestore.getInstance()
    private val collection = database.collection("actividades")
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val btn_save: Button = root.findViewById(R.id.done)
        val et_title: EditText = root.findViewById(R.id.name)
        val et_time: EditText = root.findViewById(R.id.time)
        val checkMonday: CheckBox = root.findViewById(R.id.monday)
        val checkTuesday: CheckBox = root.findViewById(R.id.tuesday)
        val checkWednesday: CheckBox = root.findViewById(R.id.wednesday)
        val checkThursday: CheckBox = root.findViewById(R.id.thursday)
        val checkFriday: CheckBox = root.findViewById(R.id.friday)
        val checkSaturday: CheckBox = root.findViewById(R.id.saturday)
        val checkSunday: CheckBox = root.findViewById(R.id.sunday)

        btn_save.setOnClickListener {
            var days = ArrayList<String>()
            var title = et_title.text.toString()
            var time = et_time.text.toString()

            var monday: Boolean = false
            var tuesday: Boolean = false
            var wednesday: Boolean = false
            var thursday: Boolean = false
            var friday: Boolean = false
            var saturday: Boolean = false
            var sunday: Boolean = false

            if(checkMonday.isChecked){
                days.add("Monday")
                monday = true
            }
            if(checkTuesday.isChecked){
                days.add("Tuesday")
                tuesday = true
            }
            if(checkWednesday.isChecked){
                days.add("Wednesday")
                wednesday = true
            }
            if(checkThursday.isChecked){
                days.add("Thursday")
                thursday = true
            }
            if(checkFriday.isChecked){
                days.add("Friday")
                friday = true
            }
            if(checkSaturday.isChecked){
                days.add("Saturday")
                saturday = true
            }
            if(checkSunday.isChecked){
                days.add("Sunday")
                sunday = true
            }

            var task = Task(title, days, time, monday, tuesday, wednesday, thursday, friday, saturday, sunday)

            HomeFragment.tasks.add(task)

            collection.add(task).addOnSuccessListener { documentReference ->
                Toast.makeText(root.context, "New Task Added!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { e ->
                Toast.makeText(root.context, "Error: Task not Added", Toast.LENGTH_SHORT).show()
            }


        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}