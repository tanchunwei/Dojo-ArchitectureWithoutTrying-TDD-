package com.pos

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class Application {
    companion object {
        @JvmStatic
        fun main(args : Array<String>){
            runApplication<Application>(*args) {
                setBannerMode(Banner.Mode.OFF)
            }
        }
    }
}