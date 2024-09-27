package ru.romanov.aisautorepairshop.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(HttpStatus.CONFLICT)
public class ItemPartFoundException extends RuntimeException {
}
