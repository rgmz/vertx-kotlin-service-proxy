package io.vertx.example

import io.vertx.codegen.annotations.GenIgnore
import io.vertx.codegen.annotations.ProxyGen
import io.vertx.codegen.annotations.VertxGen
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject

@VertxGen
@ProxyGen
interface JokeService {

    @GenIgnore
    companion object {
        @JvmStatic
        fun create(vertx: Vertx): JokeService = JokeServiceImpl(vertx)

        @JvmStatic
        fun createProxy(vertx: Vertx, address: String): JokeService =
            JokeServiceVertxEBProxy(vertx, address)
    }

    fun fetchJoke(handler: Handler<AsyncResult<JsonObject>>)
}
