package com.example.myapplication.fragments.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.database.models.UserModel
import com.example.myapplication.databinding.FragmentSignupBinding
import com.example.myapplication.utilits.PASSWORD
import com.example.myapplication.utilits.USERNAME
import com.example.myapplication.utilits.showToast
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: SignupFragmentViewModel
    private var isAllowed: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[SignupFragmentViewModel::class.java]

        binding.textInputUsername.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //NO OP
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //NO OP
            }

            override fun afterTextChanged(s: Editable?) {

                lifecycleScope.launch {
                    USERNAME = s.toString().trim()
                    if (viewModel.getUserByName(USERNAME)) {
                        isAllowed = false
                        Snackbar.make(
                            binding.root,
                            getString(R.string.this_username_already_exists_please_try_another_username),
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        isAllowed = true
                    }
                }
            }
        })

        binding.buttonSignUp.setOnClickListener {

            USERNAME = binding.textInputUsername.text.toString().trim()
            PASSWORD = binding.textInputUserPassword.text.toString().trim()

            if (USERNAME.isNotEmpty() && PASSWORD.isNotEmpty()) {

                lifecycleScope.launch {
                    if (isAllowed) {
                        viewModel.insert(UserModel(nameUser = USERNAME, passwordUser = PASSWORD))
                        showToast(getString(R.string.your_data_has_been_successfully_saved))
                        findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                    } else {
                        Snackbar.make(
                            binding.root,
                            getString(R.string.this_username_already_exists_please_try_another_username),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                showToast(getString(R.string.invalid_username_or_password))
            }
        }

        binding.textViewLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }
}