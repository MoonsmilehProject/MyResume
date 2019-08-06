package mx.com.moonsmileh.myresume.data.repository

import mx.com.moonsmileh.myresume.data.callback.RepositoryCallback
import mx.com.moonsmileh.myresume.data.network.ResumeAPI
import mx.com.moonsmileh.myresume.data.response.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResumeRepository {

    fun getProfile(callback: RepositoryCallback){
        ResumeAPI.retrofitService.fetchProfile().enqueue(object : Callback<ProfileResponse> {
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                callback.onProfileFailure()
            }

            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful)
                    callback.onProfileSuccess(response.body())
            }
        })
    }
}