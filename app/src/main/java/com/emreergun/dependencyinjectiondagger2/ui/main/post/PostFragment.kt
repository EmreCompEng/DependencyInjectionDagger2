package com.emreergun.dependencyinjectiondagger2.ui.main.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emreergun.dependencyinjectiondagger2.R
import com.emreergun.dependencyinjectiondagger2.di.viewmodels.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class PostFragment : DaggerFragment() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var viewModel:PostViewModel

    // Inject viewMdoel
    @Inject
    lateinit var provideFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView=view.findViewById(R.id.postRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        viewModel=ViewModelProvider(this,provideFactory).get(PostViewModel::class.java)
        subscribeObservers()

    }

    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner) // Observe etmeden önce mutlaka temizle
        viewModel.observePosts().observe(viewLifecycleOwner,{postResource->
            when(postResource.status){
                PostResource.Status.LOADING->{
                    Log.d(TAG, "Loading....")
                }
                PostResource.Status.SUCCESS->{
                    Log.d(TAG, "Success...")
                }
                PostResource.Status.ERROR->{
                    Log.d(TAG, "Error => ${postResource.message}")
                }
            }
        })

    }

    companion object {
        private const val TAG = "PostFragment"
    }


}