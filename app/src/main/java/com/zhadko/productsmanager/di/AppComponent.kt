package com.zhadko.productsmanager.di

import android.content.Context
import com.zhadko.productsmanager.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [BindModule::class, ProvideModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}