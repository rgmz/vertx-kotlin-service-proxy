package io.vertx.example

import io.vertx.core.AbstractVerticle
import io.vertx.core.Handler
import io.vertx.serviceproxy.ServiceBinder

class JokeVerticle : AbstractVerticle() {
  override fun start() {
    // Register the service
    registerService()

    vertx.setPeriodic(5000) {
      // Get a reference to JokeService proxy
      JokeService.createProxy(vertx, "service.joke")
        .fetchJoke(Handler {
          if (it.succeeded()) {
            println(it.result().getString("joke"))
            println("🤣")
          }
        })
    }

  }

  private fun registerService() {
    // Get a reference to JokeService instance
    val service = JokeService.create(vertx)
    ServiceBinder(vertx)
      .setAddress("service.joke")
      .register(JokeService::class.java, service)
  }
}
