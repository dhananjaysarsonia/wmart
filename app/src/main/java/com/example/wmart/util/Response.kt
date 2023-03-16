package com.example.wmart.util

import com.example.wmart.model.Country

sealed class Response {
    class Empty() : Response()
    class Loading() : Response()
    class Error(val msg : String) : Response()
    class Loaded(val list: List<Country>) : Response()

}
