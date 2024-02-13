package kr.sik.tradebot.biz.coin.dto.request;

import kr.sik.tradebot.biz.base.dto.BaseRequestDTO;
import lombok.ToString;
import org.modelmapper.PropertyMap;

@ToString
public class CoinCancelRequestDTO extends BaseRequestDTO {
    public String uuid; //	취소할 주문의 UUID	String
    public String identifier; //	조회용 사용자 지정값	String



    @Override
    protected PropertyMap getPropertyMap() {
        return null;
    }

}
