package com.binar.ariefaryudisyidik.challengegoldchapter7.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.binar.ariefaryudisyidik.challengegoldchapter7.R
import com.binar.ariefaryudisyidik.challengegoldchapter7.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLoginStatus().observe(viewLifecycleOwner) { isLogin ->
            if (isLogin) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        binding.apply {
            btnLogin.setOnClickListener {
                login()
            }
            tvRegister.setOnClickListener {
                register()
            }
        }
    }

    private fun login() {
        binding.apply {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            viewModel.login(email, password)

            viewModel.checkUser.observe(viewLifecycleOwner) { user ->
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        requireContext(), "Field cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (user == null) {
                    Toast.makeText(requireContext(), "User does not exist", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.saveUserDataStore(user.id, true)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }
    }

    private fun register() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}