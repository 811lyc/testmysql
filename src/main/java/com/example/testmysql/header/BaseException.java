package com.example.testmysql.header;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Zone
 * @version 1.0
 * @date 2020/4/9 12:24
 */

@Data
/**
 *
 * @Data相当于@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode这5个注解的合集。
 * 1. 此注解会生成equals(Object other) 和 hashCode()方法。
 * 2. 它默认使用非静态，非瞬态的属性
 * 3. 可通过参数exclude排除一些属性
 * 4. 可通过参数of指定仅使用哪些属性
 * 5. 它默认仅使用该类中定义的属性且不调用父类的方法
 * 6. 可通过callSuper=true解决上一点问题。让其生成的方法中调用父类的方法。
 */
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -8342563436976549780L;

    private Integer code;
    private String message;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
