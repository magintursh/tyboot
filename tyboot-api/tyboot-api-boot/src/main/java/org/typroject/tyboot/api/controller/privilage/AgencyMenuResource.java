package org.typroject.tyboot.api.controller.privilage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.typroject.tyboot.core.restful.doc.TycloudResource;

/**
 * <p>
 * 角色与菜单关系表 前端控制器
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
@RestController
@TycloudResource(name = "机构菜单权限", module = "privilege", resource = "agencymenu")
@RequestMapping(value = "/v1/privilege/agencymenu")
public class AgencyMenuResource {

}
