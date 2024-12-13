package com.iceman.data.network.service

import android.util.Log
import com.iceman.data.network.request.ConfirmTripRequest
import com.iceman.data.network.request.EstimateTripRequest
import com.iceman.data.network.response.ApiResponse
import com.iceman.data.network.response.ConfirmTrip
import com.iceman.data.network.response.EstimateTrip
import com.iceman.data.network.response.RideOverview
import com.squareup.moshi.Moshi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws"

interface ShopperApiService {
    @FormUrlEncoded
    @POST("/ride/estimate")
    suspend fun estimateTrip(
        @Field("customer_id") customerId: String,
        @Field("origin") origin: String,
        @Field("destination") destination: String
    ): EstimateTrip

    @PATCH("/ride/confirm")
    suspend fun confirmRide(
        @Body confirmTripRequest: ConfirmTripRequest
    ): ConfirmTrip

    @GET("/ride/{customer_id}")
    suspend fun getRides(
        @Path("customer_id") customerId: String,
        @Query("driver_id") driverId: String
    ): RideOverview
}

class ShopperNetworkApiImpl(client: OkHttpClient) : NetworkDataSource {
    val contentType = "application/json".toMediaType()
    val json = Json { ignoreUnknownKeys = true }
    private val networkApi = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(json.asConverterFactory(contentType))
        client(client)
    }.build().create(ShopperApiService::class.java)

    override suspend fun estimateTrip(estimateTripRequest: EstimateTripRequest): EstimateTrip {
        return networkApi.estimateTrip(
            customerId = estimateTripRequest.customerId,
            origin = estimateTripRequest.origin,
            destination = estimateTripRequest.destination
        )
    }

    override suspend fun confirmRide(confirmTripRequest: ConfirmTripRequest): ConfirmTrip {
        return networkApi.confirmRide(confirmTripRequest)
    }

    override suspend fun getRides(
        customerId: String,
        driverId: String
    ): RideOverview {
        return networkApi.getRides(customerId, driverId)
    }
}



