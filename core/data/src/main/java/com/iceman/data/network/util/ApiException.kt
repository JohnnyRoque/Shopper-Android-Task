package com.iceman.data.network.util

import java.io.IOException

class ApiException(val errorBody: String, val errorCode: Int) : IOException(errorBody)
