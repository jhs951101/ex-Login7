package spcompany.sharping.loginsystem.controller

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class HttpController {

    fun get(url: String, params: Map<String, String>): String? {
        var result: String? = null
        var wait = true

        val thread = Thread {
            try {

                var urlWithParams = url
                var first = true

                for(key in params.keys){
                    var ch: String

                    if(first){
                        ch = "?"
                        first = false
                    }
                    else{
                        ch = "&"
                    }

                    urlWithParams += (ch + key + "=" + params[key])
                }

                val urlLibrary = URL(urlWithParams)
                val conn = urlLibrary.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"

                BufferedReader(InputStreamReader(conn.inputStream)).use { br ->
                    var line: String?
                    while (br.readLine().also { line = it } != null) {
                        result += line
                    }
                    result = result?.replaceFirst("null", "")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                wait = false
            }
        }

        thread.start()
        while(wait){ println(wait) }  // 삭제하지 말 것. 실행 시 에러가 남.
        thread.interrupt()

        return result
    }

    fun post(url: String, params: Map<String, String>): String? {
        var result: String? = null
        var wait = true

        val thread = Thread {
            try {
                val urlLibrary = URL(url)

                var i = 0
                var data = ""

                if (params != null){
                    data += "{"

                    for (key in params.keys){
                        data += String.format(" \"%s\" : \"%s\" ", key, params[key])

                        if (i != params.size-1){
                            data += ","
                        }

                        i++
                    }

                    data += "}"
                }

                val conn = urlLibrary.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.doOutput = true
                conn.setRequestProperty("Content-Type", "application/json")
                conn.setRequestProperty("Content-Length", data.length.toString())
                conn.useCaches = false

                DataOutputStream(conn.outputStream).use { it.writeBytes(data) }
                BufferedReader(InputStreamReader(conn.inputStream)).use { br ->
                    var line: String?
                    while (br.readLine().also { line = it } != null) {
                        result += line
                    }
                    result = result?.replaceFirst("null", "")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                wait = false
            }
        }

        thread.start()
        while(wait){ println(wait) }  // 삭제하지 말 것. 실행 시 에러가 남.
        thread.interrupt()

        return result
    }
}