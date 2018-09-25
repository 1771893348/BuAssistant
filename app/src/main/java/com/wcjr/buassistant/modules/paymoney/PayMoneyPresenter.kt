package com.wcjr.buassistant.modules.paymoney

import com.wcjr.buassistant.interfaces.InteractorCallBack
import com.wcjr.buassistant.usecase.base.CaseInteractor
import com.wcjr.buassistant.usecase.LoginCase

/**
 * @author wgw
 * @time 2018/9/19 9:57
 * @class describe
 */
class PayMoneyPresenter : PayMoneyContract.Presenter{
    override fun start() {
        val logincase = LoginCase()
        logincase.requestValues.name = "wgw"
        logincase.requestValues.pwd="123456"
        CaseInteractor.exeCase(logincase,object: InteractorCallBack<LoginCase.LoginEntity> {
            override fun onNext(t: LoginCase.LoginEntity?) {
            }

            override fun accept(t: LoginCase.LoginEntity?) {
            }

            override fun onError(t: Throwable?) {
            }

            override fun onComplete() {
            }

        })
    }

    override fun destory() {
    }

}