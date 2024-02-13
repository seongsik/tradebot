package kr.sik.tradebot.biz.coin.dto.request;

import kr.sik.tradebot.biz.base.dto.BaseRequestDTO;
import lombok.ToString;
import org.modelmapper.PropertyMap;


@ToString
public class CoinOrderRequestDTO extends BaseRequestDTO {

    /***************************************
     * COIN ORDER REQUEST
     ***************************************/
    public String market;      // *	마켓 ID (필수)	String
    public String side;         // *	주문 종류 (필수)
                                // - bid : 매수
                                // - ask : 매도	String
    public String volume;      // *	주문량 (지정가, 시장가 매도 시 필수)	NumberString
    public String price;       // *	주문 가격. (지정가, 시장가 매수 시 필수)
                                // ex) KRW-BTC 마켓에서 1BTC당 1,000 KRW로 거래할 경우, 값은 1000 이 된다.
                                // ex) KRW-BTC 마켓에서 1BTC당 매도 1호가가 500 KRW 인 경우, 시장가 매수 시 값을 1000으로 세팅하면 2BTC가 매수된다.
                                //         (수수료가 존재하거나 매도 1호가의 수량에 따라 상이할 수 있음)	NumberString
    public String ord_type;    // *	주문 타입 (필수)
                                // - limit : 지정가 주문
                                // - price : 시장가 주문(매수)
                                // - market : 시장가 주문(매도)	String
    public String identifier; //	조회용 사용자 지정값 (선택)	String (Uniq 값 사용)



    @Override
    protected PropertyMap getPropertyMap() {
        return null;
    }

}
