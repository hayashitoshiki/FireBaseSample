package com.myapp.firebasesample.presentation.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.firebasesample.databinding.FragmentDashboardBinding
import com.myapp.firebasesample.domain.model.entity.Employee
import com.myapp.firebasesample.domain.model.entity.Memo
import es.dmoral.toasty.Toasty
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModel()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setState()
        setEvent()
        setEffect()
        LinearLayoutManager(requireContext()).also{
            it.orientation = LinearLayoutManager.VERTICAL
            binding.listGlobal.layoutManager = it
        }
        LinearLayoutManager(requireContext()).also{
            it.orientation = LinearLayoutManager.VERTICAL
            binding.listPrivate.layoutManager = it
        }

        return binding.root
    }

    private fun setState() {
        viewModel.employeeList.observe(viewLifecycleOwner, { setEmployeeList(it)})
        viewModel.myMemoList.observe(viewLifecycleOwner, {setMyMemoList(it)})
    }
    // イベント設定
    private fun setEvent() {
        binding.btnAddData.setOnClickListener { viewModel.addEmployeeData() }
        binding.btnGetData.setOnClickListener { viewModel.getEmployeeData() }
        binding.btnAddPrivateData.setOnClickListener { viewModel.addMemoData() }
        binding.btnGetPrivateData.setOnClickListener { viewModel.getMemoData() }
    }

    // エフェクト設定
    private fun setEffect() {
        viewModel.errorEffect.observe(viewLifecycleOwner, { if (it.isNotEmpty()) showError(it) })
    }

    // 社員情報リスト設定
    private fun setEmployeeList(data: List<Employee>) {
        binding.listGlobal.adapter = EmployeeRecyclerViewAdapter(data)
    }

    // メモリスト設定
    private fun setMyMemoList(data: List<Memo>) {
        binding.listPrivate.adapter = MemoRecyclerViewAdapter(data)
    }

    // 失敗トースト表示
    private fun showError(message: String) {
        Toasty.error(requireContext(), message, Toast.LENGTH_SHORT, true).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}