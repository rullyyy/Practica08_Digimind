package luna.raul.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import luna.raul.mydigimind.R
import luna.raul.mydigimind.ui.Task
import luna.raul.mydigimind.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var adapter: TaskAdapter? = null
    companion object{
        var tasks = ArrayList<Task>()
        var first = true
    }
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(first){
            fillTask()
            first = false
        }


        adapter = TaskAdapter(root.context, tasks)

        val gridView: GridView = root.findViewById(R.id.gridview)

        gridView.adapter = adapter

        return root
    }

    fun fillTask(){
        tasks.add(Task("Practice 1", arrayListOf("Monday, Sunday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday"), "17:00"))
        tasks.add(Task("Practice 3", arrayListOf("Monday, Tuesday"), "21:30"))
        tasks.add(Task("Practice 4", arrayListOf("Wednesday"), "21:00"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"), "14:30"))
        tasks.add(Task("Practice 6", arrayListOf("Monday, Thursday"), "11:11"))
        tasks.add(Task("Practice 7", arrayListOf("Friday, Saturday"), "00:00"))


    }

    private class TaskAdapter: BaseAdapter{
        var tasks = ArrayList<Task>()
        var context: Context? = null

        constructor(context: Context, tasks: ArrayList<Task>){
            this.context = context
            this.tasks = tasks
        }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var task = tasks[position]
            var inflater = LayoutInflater.from(context)
            var view = inflater.inflate(R.layout.task_view, null)

            var tv_title: TextView = view.findViewById(R.id.tv_title)
            var tv_days: TextView = view.findViewById(R.id.tv_days)
            var tv_time: TextView = view.findViewById(R.id.tv_time)

            tv_title.setText(task.title)
            tv_time.setText(task.time)
            tv_days.setText(task.days.toString())

            return view
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}