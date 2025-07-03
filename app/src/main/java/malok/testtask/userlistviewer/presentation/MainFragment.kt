package malok.testtask.userlistviewer.presentation

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import malok.testtask.userlistviewer.R
import malok.testtask.userlistviewer.domain.mapToListItemUi

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rcview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = UserAdapter(onItemClick = { userId ->
            viewModel.onUserClicked(userId)
        })

        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.users.observe(viewLifecycleOwner) { userList ->
            val uiList = userList?.map { user -> user.mapToListItemUi() } ?: emptyList()
            adapter.setUsers(uiList)
        }

        viewModel.navigateToUserDetail.observe(viewLifecycleOwner) { userId ->
            userId?.let {
                val bundle = Bundle().apply {
                    putString("userId", it)
                }
                findNavController().navigate(R.id.detailFragment, bundle)
                viewModel.onNavigated()
            }
        }

    }
}
