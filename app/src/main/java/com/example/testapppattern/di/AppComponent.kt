package com.example.testapppattern.di

import android.content.Context
import com.example.testapppattern.core.di.ApplicationContext
import com.example.testapppattern.core.di.app.AGlobal
import com.example.testapppattern.core.di.app.AppDependencies
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class],
)
interface AppComponent : AppDependencies {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext context: Context): AppComponent
    }
}

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideAGlobal(@ApplicationContext context: Context): AGlobal {
        return AGlobal(appPackageName = context.packageName)
    }
}
