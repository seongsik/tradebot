package kr.sik.tradebot.biz.coin.dto.response;

import kr.sik.tradebot.biz.base.dto.BaseResponseDTO;
import lombok.ToString;
import org.modelmapper.PropertyMap;


@ToString
public class CoinCancelResponseDTO extends BaseResponseDTO {

    public String uuid; //	주문의 고유 아이디	String
    public String side; //	주문 종류	String
    public String ord_type; //	주문 방식	String
    public String price; //	주문 당시 화폐 가격	NumberString
    public String state; //	주문 상태	String
    public String market; //	마켓의 유일키	String
    public String created_at; //	주문 생성 시간	String
    public String volume; //	사용자가 입력한 주문 양	NumberString
    public String remaining_volume; //	체결 후 남은 주문 양	NumberString
    public String reserved_fee; //	수수료로 예약된 비용	NumberString
    public String remaining_fee; //	남은 수수료	NumberString
    public String paid_fee; //	사용된 수수료	NumberString
    public String locked; //	거래에 사용중인 비용	NumberString
    public String executed_volume; //	체결된 양	NumberString
    public String trades_count; //	해당 주문에 걸린 체결 수	Integer



    @Override
    protected PropertyMap getPropertyMap() {
        return null;
    }

}
