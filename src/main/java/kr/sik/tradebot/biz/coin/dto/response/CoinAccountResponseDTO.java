package kr.sik.tradebot.biz.coin.dto.response;

import kr.sik.tradebot.biz.base.dto.BaseResponseDTO;
import lombok.ToString;
import org.modelmapper.PropertyMap;

@ToString
public class CoinAccountResponseDTO extends BaseResponseDTO {
    public String currency; //	화폐를 의미하는 영문 대문자 코드	String
    public String balance; //	주문가능 금액/수량	NumberString
    public String locked; //	주문 중 묶여있는 금액/수량	NumberString
    public String avg_buy_price; //	매수평균가	NumberString
    public String avg_buy_price_modified; //	매수평균가 수정 여부	Boolean
    public String unit_currency; //	평단가 기준 화폐	String

    @Override
    protected PropertyMap getPropertyMap() {
        return null;
    }
}
