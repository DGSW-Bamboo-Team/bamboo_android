package kr.hs.dgsw.bamboo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kr.hs.dgsw.bamboo.BR

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    protected lateinit var mBinding: VB
    protected abstract val mViewModel: VM
    protected abstract val layoutRes: Int

    protected abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        observeViewModel()
    }

    private fun initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, layoutRes)
        mBinding.setVariable(BR.vm, mViewModel)
        mBinding.setVariable(BR.activity, this)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBinding.isInitialized) mBinding.unbind()
    }
}