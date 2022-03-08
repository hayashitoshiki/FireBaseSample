package com.myapp.firebasesample.presentation.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.myapp.firebasesample.databinding.FragmentNotificationsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.Intent
import android.provider.Settings
import com.myapp.firebasesample.presentation.util.NoticeManager


class NotificationsFragment : Fragment() {

    private val viewModel: NotificationsViewModel by viewModel()
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.switch2.also{ switch2 ->
            switch2.setOnCheckedChangeListener { _, checked -> viewModel.changeFcm(checked) }
            viewModel.fcmText.observe(viewLifecycleOwner, Observer { switch2.text = it })
            viewModel.isFcmChecked.observe(viewLifecycleOwner, Observer { switch2.isChecked = it })
        }

        binding.button1.setOnClickListener {
            val i = Intent()
            i.action = Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS
            i.putExtra(Settings.EXTRA_APP_PACKAGE,  requireContext().packageName)
            i.putExtra(Settings.EXTRA_CHANNEL_ID, NoticeManager.CHANNELS.FCM.id)
            requireContext().startActivity(i)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}