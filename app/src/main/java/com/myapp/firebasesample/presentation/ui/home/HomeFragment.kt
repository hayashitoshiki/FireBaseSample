package com.myapp.firebasesample.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.dmoral.toasty.Toasty
import com.myapp.firebasesample.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setEvent()
        setEffect()
        return binding.root

    }

    // イベント設定
    private fun setEvent() {
        binding.btnSignIn.setOnClickListener { viewModel.signIn() }
        binding.btnSignOut.setOnClickListener { viewModel.signOut() }
        binding.btnSignUp.setOnClickListener { viewModel.signUp() }
        binding.btnDelete.setOnClickListener { viewModel.delete() }
    }

    // エフェクト設定
    private fun setEffect() {
        viewModel.errorEffect.observe(viewLifecycleOwner, { if (it.isNotEmpty()) showError(it) })
        viewModel.successEffect.observe(viewLifecycleOwner, { if (it.isNotEmpty())showSuccess(it) })

    }

    // 成功トースト表示
    private fun showSuccess(message: String) {
        Toasty.success(requireContext(), message, Toast.LENGTH_SHORT, true).show()
    }

    // 失敗トースト表示
    private fun showError(message: String) {
        Toasty.error(requireContext(), message, Toast.LENGTH_SHORT, true).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onDestroy()
        _binding = null
    }
}