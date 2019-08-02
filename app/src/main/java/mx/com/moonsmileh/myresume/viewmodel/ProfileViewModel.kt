package mx.com.moonsmileh.myresume.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.com.moonsmileh.myresume.data.network.ResumeAPI
import mx.com.moonsmileh.myresume.data.response.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileViewModel : ViewModel() {
    private val skills = MutableLiveData<ProfileResponse>()

    val response: LiveData<ProfileResponse>
        get() = skills

    init {
        fetchEmployees()
    }

    private fun fetchEmployees() {
        ResumeAPI.retrofitService.fetchProfile().enqueue(object : Callback<ProfileResponse> {
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e("onFailure Fetching", "By " + t.message)
            }

            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful)
                    skills.value = response.body()
            }
        })
    }
}