package io.vertx.example

import io.vertx.core.AbstractVerticle
import io.vertx.core.Handler
import io.vertx.serviceproxy.ServiceBinder

class JokeVerticle : AbstractVerticle() {
    override fun start() {
        // Register the service
        val service = JokeServiceFactory.create(vertx)
        ServiceBinder(vertx)
            .setAddress("service.joke")
            .register(JokeService::class.java, service)

        vertx.setPeriodic(5000) {
            service.fetchJoke(Handler {
                if (it.succeeded()) {
                    println(it.result().getString("joke"))
                    println("🤣")
                }
            })
        }

    }
}
