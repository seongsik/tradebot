package kr.sik.tradebot.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;

public class QueryStringBuilder {


    // Java 클래스를 쿼리 문자열로 변환하는 메서드
    public static String buildQueryString(Object obj) {
        // 결과를 저장할 StringBuilder 생성
        StringBuilder queryString = new StringBuilder();

        // 클래스의 모든 필드를 반복하여 처리
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true); // 필드 접근 가능하도록 설정

            try {
                Object value = field.get(obj); // 필드의 값 가져오기
                if (value != null) {
                    // 값이 null이 아닌 경우에만 쿼리 문자열에 추가
                    if (queryString.length() > 0) {
                        queryString.append("&"); // 첫 번째 필드가 아닌 경우 & 추가
                    }
                    // 필드 이름과 값을 쿼리 문자열로 추가
                    queryString.append(URLEncoder.encode(field.getName(), "UTF-8"))
                            .append("=")
                            .append(URLEncoder.encode(value.toString(), "UTF-8"));
                }
            } catch (IllegalAccessException | UnsupportedEncodingException e) {
                e.printStackTrace(); // 예외 처리
            }
        }

        return queryString.toString(); // 완성된 쿼리 문자열 반환
    }

}
