package com.armyenjoyers.hospital.controller

import com.armyenjoyers.hospital.model.ErrorDescription
import com.armyenjoyers.hospital.security.exception.JwtUsernameNotFoundException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JwtUsernameNotFoundException::class)
    fun handleUsernameNotFound(ex: JwtUsernameNotFoundException): ErrorDescription? {
        return ErrorDescription(
            "User wasn't found (username=${ex.username})",
            HttpStatus.NOT_FOUND.value()
        )
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(
            ErrorDescription(
                ex.message ?: "",
                HttpStatus.BAD_REQUEST.value()
            )
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonProcessingException::class)
    fun handleJsonProcessing(ex: JsonProcessingException): ErrorDescription? {
        val builder: StringBuilder = StringBuilder(ex.originalMessage.toString() + System.lineSeparator())

        builder.append("Source: ${ex.getLocation().getSourceRef()} \n")
        builder.append("Line: ${ex.getLocation().getLineNr()} \n")
        builder.append("Column: ${ex.getLocation().getColumnNr()} \n")

        if (ex is InvalidFormatException) {
            builder.append("Value: ${ex.value} \n")
            builder.append("Value type: ${ex.javaClass.canonicalName} \n")
            builder.append("Target type: ${ex.targetType.canonicalName} \n")
        } else if (ex is UnrecognizedPropertyException) {
            builder.append("Property name: ${ex.propertyName} \n")
            builder.append("Known properties: ${ex.knownPropertyIds} \n")
        }
        return ErrorDescription(
            builder.toString(),
            HttpStatus.NOT_FOUND.value()
        )
    }

}