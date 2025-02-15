package com.example.eventum.exception.resolver

import com.example.eventum.exception.model.ExceptionData
import com.example.eventum.exception.type.NotFoundException
import com.example.eventum.exception.type.FieldTakenException
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionResolver {
    @ExceptionHandler(FieldTakenException::class)
    fun alreadyExistsExceptionHandler(exception: Exception, response: HttpServletResponse): ExceptionData {
        response.status = 400
        return ExceptionData(
            "Данное поле уже занято",
            400
        )
    }


    @ExceptionHandler(NotFoundException::class)
    fun notFoundExceptionHandler(exception: Exception, response: HttpServletResponse): ExceptionData {
        response.status = 404
        return ExceptionData(
            "Данная ячейка не существует",
            404
        )
    }
    @ExceptionHandler(Exception::class)
    fun exceptionHandler(exception: Exception, response: HttpServletResponse): ExceptionData {
        response.status = 500
        println(exception.message)
        println(response.toString())
        return ExceptionData(
            "Что-то пошло не так",
            500
        )
    }
}