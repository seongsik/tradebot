package kr.sik.tradebot.biz.coin.service;

import kr.sik.tradebot.biz.base.service.BaseApiService;
import kr.sik.tradebot.biz.coin.dto.request.CoinAccountRequestDTO;
import kr.sik.tradebot.biz.coin.dto.request.CoinCancelRequestDTO;
import kr.sik.tradebot.biz.coin.dto.request.CoinChanceRequestDTO;
import kr.sik.tradebot.biz.coin.dto.request.CoinOrderRequestDTO;
import kr.sik.tradebot.biz.coin.dto.response.CoinAccountResponseDTO;
import kr.sik.tradebot.biz.coin.dto.response.CoinCancelResponseDTO;
import kr.sik.tradebot.biz.coin.dto.response.CoinChanceResponseDTO;
import kr.sik.tradebot.biz.coin.dto.response.CoinOrderResponseDTO;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface CoinApiService extends BaseApiService {

    List<CoinAccountResponseDTO> getAccountList(CoinAccountRequestDTO param) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    List<CoinChanceResponseDTO> getDataList(CoinChanceRequestDTO param);

    CoinChanceResponseDTO getData(CoinChanceRequestDTO param);

    CoinOrderResponseDTO buy(CoinOrderRequestDTO param);

    CoinOrderResponseDTO sell(CoinOrderRequestDTO param);

    CoinCancelResponseDTO cancel(CoinCancelRequestDTO param);
}
