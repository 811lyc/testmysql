package com.example.testmysql.untils;

import com.example.testmysql.contants.JwtConstants;

import com.example.testmysql.header.BaseException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author wangjiahao
 * @date 2020/5/27
 */
@Slf4j
public class JwtUtils {


    /**
     * 签发JWT
     *
     * @param id        全局唯一的id
     * @param subject   可以是JSON数据 尽可能少
     * @param ttlMillis 失效时间
     * @return String
     */
    public static String createToken(String id, String subject, long ttlMillis) {

        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        // Map<String, Object> claims = new HashMap<>(10);
        // claims.put("accountNum", "13361928119");

        // 生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                // .setClaims(claims)
                // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。这里其实就是new一个JwtBuilder，设置jwt的body
                .setId(id)
                // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                // 签发者
                //.setIssuer("user")
                // 签发时间
                .setIssuedAt(now)
                // 签名算法以及密匙
                .signWith(signatureAlgorithm, generalKey());
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            // 过期时间
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    /**
     * 加密一次
     *
     * @return 加密串
     */
    public static SecretKey generalKeyOne() {
        byte[] encodedKey = Base64Utils.decode(JwtConstants.JWT_SECERT.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 获取token信息，同时也做校验处理
     *
     * @param token token
     * @return body
     */
    public static Claims getTokenBody(String token) {
        try {
            //得到DefaultJwtParser
            return Jwts.parser()
                    //设置签名的秘钥 //签名秘钥，和生成的签名的秘钥一模一样
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();//设置需要解析的jwt
        } catch (ExpiredJwtException | SignatureException expired) {
            log.error("【token过期】 > 登录用户 {} ", token);
            throw new BaseException(401, "过期");
        } catch (MalformedJwtException malformedJwt) {
            log.error("【token无效】 > 登录用户 {} ", token);
            throw new BaseException(401, "无效");
        }
    }

    /**
     * 由字符串生成加密key
     *
     * @return generalKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64Utils.decode(JwtConstants.JWT_SECERT.getBytes(StandardCharsets.UTF_8));
        // 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

//    public static String getDeviceNo() {
//        String token = IntegralUtil.getHttpServletRequest().getHeader("Authorization").substring(7);
//        //TODO 根据标识查询redis 如 API:AUTH:JWT:KEY
//        return JwtUtils.getTokenBody(token).getSubject();
//    }

    /**
     * 从token中获取用户名
     *
     * @param token token
     * @return username
     */
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 从token中获取ID，同时做解密处理
     *
     * @param token token
     * @return 同时做解密处理
     */
    public static String getObjectId(String token) {
        return getTokenBody(token).getId();
    }

    /**
     * 是否已过期
     *
     * @param token token
     * @return 过期无法判断，只能通过捕获ExpiredJwtException异常
     */
    @Deprecated
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

}
