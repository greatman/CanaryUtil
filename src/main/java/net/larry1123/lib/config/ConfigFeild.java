/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 25, 2013 9:22:45 PM
 */
package net.larry1123.lib.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.FIELD)
public @interface ConfigFeild {

    /**
     * TODO
     *
     * @return
     */
    String name() default "";

    /**
     * TODO
     *
     * @return
     */
    String[] comments() default "";

    /**
     * TODO
     *
     * @return
     */
    String spacer() default "";

}
