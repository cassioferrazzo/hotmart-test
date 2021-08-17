package com.br.cassioferrazzo.hotmarttest.data.api.location

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.br.cassioferrazzo.hotmarttest.R
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationRepositoryImpl
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationsApi
import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

class LocationRepositoryTests {

    private val context: Context by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext
    }

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LocationsApi::class.java)

    private val repository = LocationRepositoryImpl(api)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getLocationDetail_Success() {
        mockWebServer.enqueueResponse(R.raw.sample_location_detail_response_ok, 200)
        runBlocking {
            assertTrue(
                when(val locationDetail =  repository.getLocationDetail(1)){
                    is ResultWrapper.Success -> true
                    else -> false
                }
            )
        }
    }
    @Test
    fun getLocationDetail_JSON_PARSE_Error() {
        mockWebServer.enqueueResponse(R.raw.sample_location_detail_response_error, 200)
        runBlocking {
            assertTrue(
                when(val locationDetail =  repository.getLocationDetail(1)){
                    is ResultWrapper.Success -> false
                    else -> true
                }
            )
        }
    }

    @Test
    fun simulateAnErrorForCITest(){
        assertTrue(false)
    }
    private fun MockWebServer.enqueueResponse(fileResourceId: Int, code: Int) {
        val inputStream = context.resources.openRawResource(fileResourceId)

        val source = inputStream?.let { inputStream.source().buffer() }
        source?.let {
            enqueue(
                MockResponse()
                    .setResponseCode(code)
                    .setBody(source.readString(StandardCharsets.UTF_8))
            )
        }
    }
}