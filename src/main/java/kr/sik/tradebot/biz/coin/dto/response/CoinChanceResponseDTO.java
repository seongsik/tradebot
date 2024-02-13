package kr.sik.tradebot.biz.coin.dto.response;

import kr.sik.tradebot.biz.base.dto.BaseResponseDTO;
import lombok.ToString;
import org.modelmapper.PropertyMap;

@ToString
public class CoinChanceResponseDTO extends BaseResponseDTO {
    public String bid_fee; // 매수 수수료 비율	NumberString
    public String ask_fee; //	매도 수수료 비율	NumberString
    public String[] ask_types; //	매도 주문 지원 방식	Array[String]
    public String[] bid_types; //	매수 주문 지원 방식	Array[String]

    public Market market; // 마켓에 대한 정보
    public bidAccount bid_account; // 매수 시 사용하는 화폐의 계좌 상태
    public AskCoount ask_account; // 매도 시 사용하는 화폐의 계좌 상태	Object

    @Override
    protected PropertyMap getPropertyMap() {
        return null;
    }

    class Market {
        String id; //	마켓의 유일 키	String
        String name; //	마켓 이름	String
        String[] order_types; //	지원 주문 방식 (만료)	Array[String]
        String[] order_sides; //	지원 주문 종류	Array[String]
        String max_total; //	최대 매도/매수 금액	NumberString
        String state; //	마켓 운영 상태	String

        Bid bid; // 매수 시 제약사항	Object
        Ask ask; // 매도 시 제약사항	Object

        // 매수 시 제약사항	Object
        class Bid {
            String currency; //	화폐를 의미하는 영문 대문자 코드	String
            String price_unit; //	주문금액 단위	String
            String min_total; //	최소 매도/매수 금액	Number
        }


        // 매도 시 제약사항	Object
        class Ask {
            String currency; //	화폐를 의미하는 영문 대문자 코드	String
            String price_unit; //	주문금액 단위	String
            String min_total; //	최소 매도/매수 금액	Number
        }

    }

    private class bidAccount {
        String currency; //	화폐를 의미하는 영문 대문자 코드	String
        String balance; //	주문가능 금액/수량	NumberString
        String locked; //	주문 중 묶여있는 금액/수량	NumberString
        String avg_buy_price; //	매수평균가	NumberString
        String avg_buy_price_modified; //	매수평균가 수정 여부	Boolean
        String unit_currency; //	평단가 기준 화폐	String
    }

    private class AskCoount {
        String currency; //	화폐를 의미하는 영문 대문자 코드	String
        String balance; //	주문가능 금액/수량	NumberString
        String locked; //	주문 중 묶여있는 금액/수량	NumberString
        String avg_buy_price; //	매수평균가	NumberString
        String avg_buy_price_modified; //	매수평균가 수정 여부	Boolean
        String unit_currency; //	평단가 기준 화폐	String
    }
}
