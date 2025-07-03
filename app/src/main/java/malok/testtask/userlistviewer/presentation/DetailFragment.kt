package malok.testtask.userlistviewer.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import malok.testtask.userlistviewer.R
import malok.testtask.userlistviewer.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getString("userId") ?: return


        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        val user = mainViewModel.users.value?.find { it.id == userId }

        if (user == null) {
            // Можно показать ошибку или заглушку
            return
        }

        with(binding) {
            Picasso.get().load(user.avatarUrl).placeholder(R.drawable.ic_launcher_background)
                .into(detailAvatar)
            detailFullName.text = user.fullName
            detailGender.text = user.gender
            detailEmail.text = user.email
            detailPhone.text = user.phone
            detailCell.text = user.cell
            detailAddress.text = user.address
            detailDob.text = user.dateOfBirth
            detailAge.text = user.age.toString()
            detailRegistered.text = user.registeredDate
            detailNationality.text = user.nationality
        }
        binding.detailEmail.setOnClickListener { openEmail(user.email) }
        binding.detailPhone.setOnClickListener { dialPhone(user.phone) }
        binding.detailCell.setOnClickListener { dialPhone(user.cell) }
        binding.detailAddress.setOnClickListener { openMap(user.address) }
    }

    private fun openEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
        }
        startActivity(Intent.createChooser(intent, "Send Email"))
    }

    private fun dialPhone(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        startActivity(intent)
    }

    private fun openMap(address: String) {
        val uri = Uri.parse("geo:0,0?q=${Uri.encode(address)}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
