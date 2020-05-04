package com.pos.controller

import com.pos.repository.InventoryRepo
import com.pos.service.POSService
import com.pos.view.WebDisplay
import com.pos.model.Price
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.web.bind.annotation.*
import com.pos.repository.InventoryInMemoryRepo

@RestController()
@RequestMapping("api")
class PosController(
    val template : NamedParameterJdbcTemplate
){
    private val display = WebDisplay()
    private val posService : POSService = POSService(
        InventoryInMemoryRepo(
            mapOf(
                "12345" to Price(
                    1250
                )
            )
        ),
        display
    )
    private val posServiceDB : POSService = POSService(
        InventoryRepo(template),
        display
    )

    @RequestMapping(value = ["/test"], method = [RequestMethod.GET], produces = ["application/json"])
    fun test() : ResponseEntity<String>{
        return  ResponseEntity<String>("test", HttpStatus.OK)
    }

    @GetMapping("test2")
    fun test2() : ResponseEntity<String>{
        return  ResponseEntity<String>("test", HttpStatus.OK)
    }

    @GetMapping("exception")
    fun testException(): ResponseEntity<String>{
        throw Exception("Test exception")
    }

    @GetMapping("barcode/{barcode}", produces = ["application/json"])
    fun barcode(@PathVariable barcode : String) : ResponseEntity<String>{
        logger.trace("onBarcode $barcode")
        posService.onBarcode(barcode)
        return  ResponseEntity<String>(display.toJson(), HttpStatus.OK)
    }

    @GetMapping("barcodedb/{barcode}", produces = ["application/json"])
    fun barcodedb(@PathVariable barcode : String) : ResponseEntity<String>{
        logger.trace("onBarcodeDB $barcode")
        posServiceDB.onBarcode(barcode)
        return  ResponseEntity<String>(display.toJson(), HttpStatus.OK)
    }

    companion object{
        private val logger = LoggerFactory.getLogger(PosController::class.java)
    }
}