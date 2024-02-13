package kr.sik.tradebot.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ApiErrorResponseException extends Exception{
    private int code;
    private String name;
    private String message;

}
