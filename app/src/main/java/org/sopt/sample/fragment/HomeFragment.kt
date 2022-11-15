package org.sopt.sample.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.adapter.RecyclerAdapter
import org.sopt.sample.data.remote.*
import org.sopt.sample.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding!!) { "${this::class.java.simpleName}에서 에러가 발생했습니다." }
    private val userService: UserDataService = UserServicePool.userDataService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle? ){
        super.onViewCreated(view, savedInstanceState)
        val adapter = RecyclerAdapter(requireContext())
        binding.rvRepos.adapter = adapter
        userService.userData().enqueue(object : Callback<ResponseUserDTO> {
            override fun onResponse(
                call: Call<ResponseUserDTO>,
                response: Response<ResponseUserDTO>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        adapter.setRepoList(it.data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUserDTO>, t: Throwable) {
                Log.e("stellar", "fail")
            }

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}