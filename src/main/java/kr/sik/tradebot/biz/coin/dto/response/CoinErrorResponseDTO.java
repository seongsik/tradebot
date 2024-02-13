package kr.sik.tradebot.biz.coin.dto.response;


import lombok.ToString;

@ToString
public class CoinErrorResponseDTO {
    public int code;
    public String name;
    public String message;
}
