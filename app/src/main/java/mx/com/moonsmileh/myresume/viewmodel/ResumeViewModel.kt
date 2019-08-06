package mx.com.moonsmileh.myresume.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.com.moonsmileh.myresume.data.network.ResumeAPI
import mx.com.moonsmileh.myresume.data.response.JobResponse
import mx.com.moonsmileh.myresume.model.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResumeViewModel : ViewModel() {
    private val jobsResponse = MutableLiveData<List<Job>>()

    val response: LiveData<List<Job>>
        get() = jobsResponse

    init {
        fetchEmployees()
    }

    private fun fetchEmployees() {
        ResumeAPI.retrofitService.fetchJobs().enqueue(object : Callback<JobResponse> {
            override fun onFailure(call: Call<JobResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<JobResponse>, response: Response<JobResponse>) {
                if (response.isSuccessful)
                    jobsResponse.value = response.body()?.jobs
            }
        })
    }
}