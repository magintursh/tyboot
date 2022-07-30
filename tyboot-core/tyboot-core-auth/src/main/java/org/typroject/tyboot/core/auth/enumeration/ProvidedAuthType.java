
package org.typroject.tyboot.core.auth.enumeration;

import org.typroject.tyboot.core.auth.authentication.LoginAuthenticatorHandler;

/**
 * Created by yaohelang on 2017/9/20.
 */
public interface ProvidedAuthType {

     Class<? extends LoginAuthenticatorHandler> getAuthenticator();


     String getAuthType();
}
