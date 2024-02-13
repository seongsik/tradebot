package kr.sik.tradebot.biz.coin.dto.request;

import kr.sik.tradebot.biz.base.dto.BaseRequestDTO;
import lombok.ToString;
import org.modelmapper.PropertyMap;

@ToString
public class CoinAccountRequestDTO extends BaseRequestDTO {
    @Override
    protected PropertyMap getPropertyMap() {
        return null;
    }
}
