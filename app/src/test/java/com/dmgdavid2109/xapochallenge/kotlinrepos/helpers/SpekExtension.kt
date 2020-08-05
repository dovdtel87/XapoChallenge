package com.dmgdavid2109.xapochallenge.kotlinrepos.helpers

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.spekframework.spek2.dsl.LifecycleAware
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.lifecycle.MemoizedValue

@ExperimentalCoroutinesApi
val LifecycleAware.testDispatcher : TestCoroutineDispatcher
    get() = TestCoroutineDispatcher()

@ExperimentalCoroutinesApi
fun LifecycleAware.withTestCoroutine() : TestCoroutineDispatcher {
    beforeEachTest {
        Dispatchers.setMain(testDispatcher)
    }

    afterEachTest {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    return testDispatcher
}

@ExperimentalCoroutinesApi
fun LifecycleAware.beforeEachTestBlocking(block: suspend () -> Unit) =
    beforeEachTest { testDispatcher.runBlockingTest { block() } }

inline fun <reified T: Any> LifecycleAware.mock(
    mode: CachingMode = CachingMode.TEST,
    noinline  block: T.() -> Unit = {}
): MemoizedValue<T> {
    return memoized(mode) {
        mockk<T>(relaxed = true, block = block)
    }
}

fun LifecycleAware.withInstantTaskExecutor() {
    beforeEachTest {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }
        })
    }

    afterEachTest { ArchTaskExecutor.getInstance().setDelegate(null) }
}
