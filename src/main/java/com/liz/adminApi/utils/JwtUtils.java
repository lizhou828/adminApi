/*
 *Project: bigData
 *File: com.diyun.bigData.appApi.utils.JwtUtils.java <2019年05月24日}>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/

package com.liz.adminApi.utils;

import com.liz.adminApi.constants.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lizhou
 * @version 1.0
 * @Date 2019年05月24日 09时30分
 */
public class JwtUtils {
    /**
     * 创建新token
     * @param username
     * @return
     */
    public static String createNewToken(String username){
        //获取当前时间
        Date now = new Date(System.currentTimeMillis());
        //过期时间(30天)
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, 30);
        Date date2=calendar.getTime();
        return Jwts.builder().setSubject(username)
                .setIssuer("API of Glorypty")
                .setExpiration(date2)
                .signWith(SignatureAlgorithm.HS256, Constant.JWT_SECRET_KEY)
                .compact();
    }

    /**
     * 验证jwt
     */
    public static Claims verify(String token) {
        return Jwts.parser().setSigningKey(Constant.JWT_SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static String getUserId(String token) {
        Claims claims = verify(token);
        if(null == claims || StringUtils.isEmpty(claims.getSubject())){
            return null;
        }
        return claims.getSubject();
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.valueOf(1264978791);
        System.out.println(Double.valueOf("12649787911"));
    }
}
