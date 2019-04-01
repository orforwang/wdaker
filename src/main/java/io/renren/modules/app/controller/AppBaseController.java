package io.renren.modules.app.controller;

import io.renren.modules.sys.service.config.api.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Stream;


/**
 * App Base Controller
 *
 *
 * 公共方法
 *
 */
public abstract class AppBaseController {

    @Autowired
    private ShiroService shiroService;

    /**
     *
     * 是否具有某种权限
     *
     * @param userId
     * @param perms
     * @return
     */
    protected boolean hasPermisstion(long userId, String[] perms) {
        Set<String> userPermissions = shiroService.getUserPermissions(userId);
        if (userPermissions.size() == 0) {
            return false;
        }
        return Stream.of(perms).allMatch(x -> userPermissions.stream().anyMatch(h -> h.equals(x)));
    }
}
