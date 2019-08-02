package mx.com.moonsmileh.myresume.view.fragment

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun showOrHideLoader(isVisible: Boolean, progressbar: ProgressBar) {
        progressbar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}