package com.example.loginapp.modules.login.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.loginapp.R
import com.example.loginapp.databinding.ActivityMainBinding
import com.example.loginapp.modules.profile.presentation.ProfileActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityMainBinding

    private fun updateUI(viewState: LoginViewModel.ViewState.Success) {
        binding.progress.visibility = INVISIBLE
        val data  = HashMap<String, Any>()
        data["fullName"] = viewState.firstName + " " + viewState.lastName
        data["email"] = viewState.email
        data["image"] = viewState.image

        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("dataMap", data)
        startActivity(intent)
    }

    private fun loadError(errorMessage: String) {
        binding.progress.visibility = INVISIBLE
        Toast.makeText(applicationContext, "An error occurred. Please Try again",
            Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view  = binding.root
        setContentView(view)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        lifecycleScope.launch {
            viewModel.getSavedCred()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect {viewState ->
                    when(viewState){
                        is LoginViewModel.ViewState.LoadingState -> binding.progress.visibility = VISIBLE
                        is LoginViewModel.ViewState.Empty ->{}
                        is LoginViewModel.ViewState.LoginCred -> {
                            binding.usersname.setText(viewState.username)
                            binding.password.setText(viewState.password)
                        }
                        is LoginViewModel.ViewState.Success -> updateUI(viewState)
                        is LoginViewModel.ViewState.Error -> loadError(viewState.errorMessage)
                    }
                }
            }
        }

        binding.rememberMe.setOnClickListener {
            if(binding.checkbox.isChecked){
                viewModel.deleteCred()
                Log.d("TAG", "onCreate: delete")
            }else{
                viewModel.saveCred(
                    binding.usersname.text.toString().trim(),
                    binding.password.text.toString().trim()
                )
            }
        }

        binding.signIn.setOnClickListener {
            viewModel.login(
                binding.usersname.text.toString().trim(),
                binding.password.text.toString().trim()
            )
        }
    }
}