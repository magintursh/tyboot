package org.typroject.tyboot.api.controller.privilage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.typroject.tyboot.api.face.privilage.model.RoleMenuModel;
import org.typroject.tyboot.api.face.privilage.service.RoleMenuService;
import org.typroject.tyboot.core.foundation.context.RequestContext;
import org.typroject.tyboot.core.foundation.enumeration.UserType;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

import java.util.List;

/**
 * <p>
 * 角色菜单
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */

@TycloudResource(name = "角色菜单",module = "privilege", resource = "rolemenu")
@RequestMapping(value = "/v1/privilege/rolemenu")
@RestController
public class RoleMenuResource {



    @Autowired
    RoleMenuService roleMenuService;


    /**
     * 更新角色菜单权限
     * @param sequenceNBR 物理主键
     * @param menuIds  菜单id数
     * @return
     * @throws Exception
     */
    @TycloudOperation(name = "更新角色菜单权限",ApiLevel = UserType.AGENCY)
    @RequestMapping(value = "/role/{sequenceNBR}", method = RequestMethod.PUT)
    public ResponseModel<List<RoleMenuModel>> updateByRole(@PathVariable Long sequenceNBR, @RequestBody String [] menuIds) throws Exception
    {
        return ResponseHelper.buildResponse(this.roleMenuService.updateByRole(menuIds,sequenceNBR, RequestContext.getAgencyCode()));
    }



}
