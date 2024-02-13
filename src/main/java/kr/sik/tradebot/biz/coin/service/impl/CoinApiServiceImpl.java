package kr.sik.tradebot.biz.coin.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import kr.sik.tradebot.biz.coin.dto.request.CoinAccountRequestDTO;
import kr.sik.tradebot.biz.coin.dto.request.CoinCancelRequestDTO;
import kr.sik.tradebot.biz.coin.dto.request.CoinChanceRequestDTO;
import kr.sik.tradebot.biz.coin.dto.request.CoinOrderRequestDTO;
import kr.sik.tradebot.biz.coin.dto.response.*;
import kr.sik.tradebot.biz.coin.service.CoinApiService;
import kr.sik.tradebot.common.exception.ApiErrorResponseException;
import kr.sik.tradebot.common.util.QueryStringBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoinApiServiceImpl implements CoinApiService {
    private static final Logger logger = LoggerFactory.getLogger(CoinApiServiceImpl.class);


    @Value("${upbit.api.url}")
    private String apiUrl;

    @Value("${upbit.api.accessKey}")
    private String accessKey;

    @Value("${upbit.api.secretKey}")
    private String secretKey;

    private final Gson gson;


    @Override
    public List<CoinAccountResponseDTO> getAccountList(CoinAccountRequestDTO param) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String uri = "/v1/accounts";
        String queryString = QueryStringBuilder.buildQueryString(param);

        String jsonStr = callExchangeApi(uri, HttpMethod.GET, queryString);

        Type listType = new TypeToken<List<CoinAccountResponseDTO>>() {}.getType();
        List<CoinAccountResponseDTO> list = gson.fromJson(jsonStr, listType);

        return list;
    }

    @Override
    public List<CoinChanceResponseDTO> getDataList(CoinChanceRequestDTO param) {
        return null;
    }

    @Override
    public CoinChanceResponseDTO getData(CoinChanceRequestDTO param) {
        return null;
    }


    @Override
    public CoinOrderResponseDTO buy(CoinOrderRequestDTO param) {
        return null;
    }

    @Override
    public CoinOrderResponseDTO sell(CoinOrderRequestDTO param) {
        return null;
    }

    @Override
    public CoinCancelResponseDTO cancel(CoinCancelRequestDTO param) {
        return null;
    }






    private String callExchangeApi(String uri, HttpMethod method, String queryString) throws UnsupportedEncodingException, NoSuchAlgorithmException, ApiErrorResponseException {
        logger.debug("callExchangeApi : {}", apiUrl+uri);

        String authenticationToken = this.generateApiToken(queryString);

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authenticationToken);

        // 요청 본문 데이터 (필요한 경우)
        String requestBody = "{\"key\": \"value\"}";

        // RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 요청 엔티티 생성
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // HTTP 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl+uri, method, requestEntity, String.class);

        // 응답 출력
        HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
        String responseBody = responseEntity.getBody();

        logger.debug("HTTP Status {}", statusCode);
        logger.debug(responseBody);
        if(! statusCode.is2xxSuccessful() ) {
            CoinErrorResponseDTO errorDTO = gson.fromJson(responseBody, CoinErrorResponseDTO.class);
            throw new ApiErrorResponseException(statusCode.value(), errorDTO.name, errorDTO.message);
        }

        return responseBody;
    }

    private String callQuotationApi(String uri, HttpMethod method, String queryString) throws IOException, ApiErrorResponseException {
        logger.debug("callQuotationApi : {}", apiUrl+uri);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiUrl + uri + "?" + queryString)
                .get()
                .addHeader("accept", "application/json")
                .build();


        // 응답 출력
        Response response = client.newCall(request).execute();
        int statusCode = response.code();
        String responseBody = response.body().toString();

        logger.debug("HTTP Status {}", statusCode);
        logger.debug(responseBody);
        if(200 != statusCode) {
            throw new ApiErrorResponseException(statusCode, "error occured", "error occured");
        }

        return responseBody;

    }





    private String generateApiToken(String queryString) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String jwtToken = null;
        String authenticationToken = null;


        if(StringUtils.hasText(queryString)) {
            // 파라미터가 있는 경우

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(queryString.getBytes("utf8"));

            String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            jwtToken = JWT.create()
                    .withClaim("access_key", accessKey)
                    .withClaim("nonce", UUID.randomUUID().toString())
                    .withClaim("query_hash", queryHash)
                    .withClaim("query_hash_alg", "SHA512")
                    .sign(algorithm);

            authenticationToken = "Bearer " + jwtToken;


        } else {
            // 파라미터가 없는 경우

            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            jwtToken = JWT.create()
                    .withClaim("access_key", accessKey)
                    .withClaim("nonce", UUID.randomUUID().toString())
                    .sign(algorithm);

            authenticationToken = "Bearer " + jwtToken;
        }

        logger.debug("generateApiToken : {}", authenticationToken);
        return authenticationToken;
    }

}
