package com.example.myapplication.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.utilits.PASSWORD
import com.example.myapplication.utilits.TYPE_ROOM
import com.example.myapplication.utilits.USERNAME
import com.example.myapplication.utilits.showToast
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginFragmentViewModel::class.java]
        viewModel.initDatabase(TYPE_ROOM)

        binding.buttonLogIn.setOnClickListener {

            USERNAME = binding.textInputUsername.text.toString().trim()
            PASSWORD = binding.textInputUserPassword.text.toString().trim()

            if (USERNAME.isNotEmpty() && PASSWORD.isNotEmpty()) {

                lifecycleScope.launch {
                    if (viewModel.getUserByNameAndPassword(USERNAME, PASSWORD)) {
                        showToast("Hello, $USERNAME, you have successfully logged in!")
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                        clearFields()
                    } else {
                        showToast(getString(R.string.invalid_username_or_password))
                    }
                }
            } else {
                showToast(getString(R.string.invalid_username_or_password))
            }
        }

        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            clearFields()
        }
    }

    private fun clearFields() {
        binding.textInputUsername.text?.clear()
        binding.textInputUserPassword.text?.clear()
    }
}