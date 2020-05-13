package com.pos.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.pos.repository.InventoryRepo
import com.pos.service.POSService
import com.pos.view.ProductWebDisplay
import com.pos.model.Price
import com.pos.model.Product
import com.pos.model.request.CartRequest
import com.pos.model.view.CartViewModel
import com.pos.model.view.WebDisplayViewModel
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.web.bind.annotation.*
import com.pos.repository.InventoryInMemoryRepo
import java.time.LocalDateTime

@RestController()
@RequestMapping("api")
class PosController(
    val template : NamedParameterJdbcTemplate
){
    private val objectMapper = ObjectMapper()
    private val display = ProductWebDisplay()
    private val posService : POSService = POSService(
        InventoryInMemoryRepo(
            mapOf(
                "12345" to Product(1, "12345", "Fish", "Fresh water fish", Price(
                    1250
                )),
                "67890" to Product(1, "67890", "Pork", "Airpork", Price(
                        990
                ))
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
        return ResponseEntity<String>(objectMapper.writeValueAsString(display.getVM()), HttpStatus.OK)
    }

    @GetMapping("barcodedb/{barcode}", produces = ["application/json"])
    fun barcodedb(@PathVariable barcode : String) : ResponseEntity<String>{
        logger.trace("onBarcodeDB $barcode")
        posServiceDB.onBarcode(barcode)
        return  ResponseEntity<String>(objectMapper.writeValueAsString(display.getVM()), HttpStatus.OK)
    }

    @GetMapping("getallinventory", produces = ["application/json"])
    fun allInventory() : ResponseEntity<String>{
        logger.trace("GetAllInventory")
        val vm = posService.getAllInventory()
        return ResponseEntity<String>(objectMapper.writeValueAsString(vm), HttpStatus.OK)
    }

    @GetMapping("getallinventorydb", produces = ["application/json"])
    fun allInventoryDb() : ResponseEntity<String>{
        logger.trace("GetAllInventoryDB")
        val vm = posServiceDB.getAllInventory()
        return  ResponseEntity<String>(objectMapper.writeValueAsString(vm), HttpStatus.OK)
    }

    @PostMapping("addCart", consumes = ["application/json"])
    fun addCart(@RequestBody cart : CartRequest) : ResponseEntity<String>{
        logger.trace("addCart ${objectMapper.writeValueAsString(cart)}")

        posService.onBarcode(cart.barcode)
        val cartVM = CartViewModel(cart.clientID, LocalDateTime.now(),display.getVM().response!!)
        tempCartCache.add(cartVM)

        val vm = WebDisplayViewModel(true, "", "")
        return  ResponseEntity<String>(objectMapper.writeValueAsString(vm), HttpStatus.OK)
    }

    @GetMapping("getallcart", produces = ["application/json"])
    fun allCart() : ResponseEntity<String>{
        logger.trace("GetAllCart")
        val vm = WebDisplayViewModel(true, "", tempCartCache.toList())
        return ResponseEntity<String>(objectMapper.writeValueAsString(vm), HttpStatus.OK)
    }

    companion object{
        private val logger = LoggerFactory.getLogger(PosController::class.java)
        private var tempCartCache = mutableListOf<CartViewModel>()
    }
}