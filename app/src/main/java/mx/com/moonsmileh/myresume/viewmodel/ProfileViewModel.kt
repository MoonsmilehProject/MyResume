package mx.com.moonsmileh.myresume.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.com.moonsmileh.myresume.data.callback.RepositoryCallback
import mx.com.moonsmileh.myresume.data.repository.ResumeRepository
import mx.com.moonsmileh.myresume.data.response.ProfileResponse


class ProfileViewModel : ViewModel(), RepositoryCallback {
    private val skills = MutableLiveData<ProfileResponse>()
    private val repository = ResumeRepository()

    val response: LiveData<ProfileResponse>
        get() = skills

    init {
        fetchEmployees()
    }

    private fun fetchEmployees() {
        repository.getProfile(this)
    }

    override fun onProfileSuccess(profile: ProfileResponse?) {
        skills.value = profile
    }

    override fun onProfileFailure() {
        skills.value = null
    }
}
