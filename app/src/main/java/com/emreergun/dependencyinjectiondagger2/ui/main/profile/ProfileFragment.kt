package com.emreergun.dependencyinjectiondagger2.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.emreergun.dependencyinjectiondagger2.R
import com.emreergun.dependencyinjectiondagger2.di.viewmodels.ViewModelFactory
import com.emreergun.dependencyinjectiondagger2.models.User
import com.emreergun.dependencyinjectiondagger2.ui.auth.AuthResource
import dagger.android.support.DaggerFragment
import java.lang.StringBuilder
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    private lateinit var viewModel:ProfileViewModel
    private lateinit var textView:TextView

    // Dependency Injection
    @Inject
    lateinit var providerFactory: ViewModelFactory
    // Dependency Injection


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ProfileFragment created.... ")
        textView=view.findViewById(R.id.textViewProfile)
        viewModel=ViewModelProvider(this,providerFactory).get(ProfileViewModel::class.java)

        subscribeObservers()
    }


    private fun subscribeObservers(){
        // Fragmentlerın kendi yaşam dögüleri olduğu için activty'lerden farklı bir şekilde yapıldı
        // bu yöntemle yap her zaman

        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner) // Fragmentlerda Mutlaka ilk başta silmek gerekir ( memory leak)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner,{authResource->
            if (authResource!=null){
                when(authResource.status){
                    AuthResource.AuthStatus.AUTHENTICATED ->{
                        setUserDetails(authResource.data)
                    }
                    AuthResource.AuthStatus.ERROR ->{

                    }
                    else->{

                    }
                }
            }
        })
    }

    private fun setUserDetails(user: User?) {
        if (user!=null){
            Log.d(TAG, "setUserDetails: ")
            val sb=StringBuilder()
            sb.appendLine("User ID      :${user.id}")
            sb.appendLine("User Name    :${user.name}")
            sb.appendLine("User email :${user.email}")
            sb.appendLine("User Name    :${user.name}")

            textView.text=sb.toString()
        }
    }


    companion object {
        private const val TAG = "ProfileFragment"
    }

}