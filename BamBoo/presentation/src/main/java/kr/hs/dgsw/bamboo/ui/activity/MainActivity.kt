package kr.hs.dgsw.bamboo.ui.activity

import androidx.activity.viewModels
import kr.hs.dgsw.bamboo.R
import kr.hs.dgsw.bamboo.base.BaseActivity
import kr.hs.dgsw.bamboo.databinding.ActivityMainBinding
import kr.hs.dgsw.bamboo.viewmodel.MainViewModel

class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel>(){
    override val mViewModel: MainViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun observeViewModel() {
        with(mViewModel){

        }
    }

}