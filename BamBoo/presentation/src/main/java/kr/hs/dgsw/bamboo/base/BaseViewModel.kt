package kr.hs.dgsw.bamboo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {
    private val disposable = CompositeDisposable()
    val onErrorEvent = MutableLiveData<Throwable>()

    fun addDisposable(single: Single<*>, observer: DisposableSingleObserver<*>) {
        disposable.add(single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer as SingleObserver<Any>) as Disposable
        )
    }

    fun addDisposable(complete: Completable, observer: DisposableCompletableObserver) {
        disposable.add(complete.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun errorHandler(e: Throwable){
        e.printStackTrace()
        onErrorEvent.value = e
    }
}