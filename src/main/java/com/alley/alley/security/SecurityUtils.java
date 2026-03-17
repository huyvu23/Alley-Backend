package com.alley.alley.security;

import com.alley.alley.user.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    /**
     * Lấy thông tin người dùng hiện tại từ Security Context.
     * 
     * @return Đối tượng User đang đăng nhập.
     * @throws RuntimeException nếu không tìm thấy thông tin đăng nhập.
     */
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            return (User) principal;
        }

        throw new RuntimeException("Current user not found in security context");
    }

    /**
     * Lấy ID của người dùng hiện tại.
     */
    public String getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
