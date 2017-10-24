package ua.kiev.dimoon.questcreator.common.utils.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecuritiUtils {
    /**
     * Returns true if user authenticated and isn't anonymous.
     * Else false
     * @return
     */
    public static boolean isAuthenticated() {
        return  SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken);
    }
}
