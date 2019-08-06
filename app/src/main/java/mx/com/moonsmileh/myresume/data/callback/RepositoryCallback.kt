package mx.com.moonsmileh.myresume.data.callback

import mx.com.moonsmileh.myresume.data.response.ProfileResponse

interface RepositoryCallback{
    fun onProfileSuccess(profile: ProfileResponse?)
    fun onProfileFailure()
}