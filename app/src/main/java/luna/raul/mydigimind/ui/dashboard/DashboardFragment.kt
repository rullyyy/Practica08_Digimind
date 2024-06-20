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
import luna.raul.mydigimind.ui.Task
import luna.raul.mydigimind.ui.home.HomeFragment

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

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

            if (checkMonday.isChecked){
                days.add("Monday")
            }
            if (checkTuesday.isChecked){
                days.add("Tuesday")
            }
            if (checkWednesday.isChecked){
                days.add("Wednesday")
            }
            if (checkThursday.isChecked){
                days.add("Thursday")
            }
            if (checkFriday.isChecked){
                days.add("Friday")
            }
            if (checkSaturday.isChecked){
                days.add("Saturday")
            }
            if (checkSunday.isChecked){
                days.add("Sunday")
            }
            var task = Task(title, days, time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "New Task Added!", Toast.LENGTH_SHORT).show()


        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}