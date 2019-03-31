package io.vertx.example

import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.codec.BodyCodec
import io.vertx.kotlin.ext.web.client.WebClientOptions

class JokeServiceImpl(vertx: Vertx) : JokeService {

    private val client by lazy {
        val options = WebClientOptions(ssl = true)
        WebClient.create(vertx, options)
    }

    override fun fetchJoke(handler: Handler<AsyncResult<JsonObject>>) {
        client.get(443, "icanhazdadjoke.com", "/")
            .putHeader("Accept", "application/json")
            .`as`(BodyCodec.jsonObject())
            .send {
                if (it.failed()) {
                    handler.handle(Future.failedFuture(it.cause()))
                } else {
                    handler.handle(Future.succeededFuture(it.result().body()))
                }
            }
    }

}