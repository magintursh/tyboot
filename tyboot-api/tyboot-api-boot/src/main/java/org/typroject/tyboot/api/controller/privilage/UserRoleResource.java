package org.typroject.tyboot.api.controller.privilage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.typroject.tyboot.core.restful.doc.TycloudResource;

/**
 * <p>
 * 用户角色关系表 前端控制器
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
@TycloudResource(name = "用户角色",module = "privilege", resource = "userrole")
@RequestMapping(value = "/v1/privilege/userrole")
@RestController
public class UserRoleResource {


}
