package com.braveheartcreations.myapplication

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

private const val FAKE_STRING = "HELLO_WORLD"

class RepositoryRawDataTest {
    private val context: Context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun checkDataIsNotNull() {
        val result = DataRepository.getImagesFromFile(context)
        assertNotNull(result)
    }

    @Test
    fun checkDataLength() {
        val result = DataRepository.getImagesFromFile(context)
        assertEquals(26, result.size)
    }
}