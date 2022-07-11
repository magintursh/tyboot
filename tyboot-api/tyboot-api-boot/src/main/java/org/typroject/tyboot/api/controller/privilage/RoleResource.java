package org.typroject.tyboot.api.controller.privilage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.typroject.tyboot.api.face.privilage.model.RoleModel;
import org.typroject.tyboot.api.face.privilage.service.RoleService;
import org.typroject.tyboot.api.face.privilage.service.UserRoleService;
import org.typroject.tyboot.component.event.RestEventTrigger;
import org.typroject.tyboot.core.foundation.constans.CoreConstans;
import org.typroject.tyboot.core.foundation.constans.PropertyValueConstants;
import org.typroject.tyboot.core.foundation.context.RequestContext;
import org.typroject.tyboot.core.foundation.enumeration.UserType;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * 角色表 前端控制器
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
@TycloudResource(name = "角色管理",module = "privilege", resource = "role")
@RequestMapping(value = "/v1/privilege/role")
@RestController
public class RoleResource {




    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @TycloudOperation( operation = "分页查询角色信息",ApiLevel = UserType.AGENCY,needAuth = false)
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseModel<Page<RoleModel>> queryForPage (
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value ="current") int current,
            @RequestParam(value = "size") int size) throws Exception
    {
        Page<RoleModel> page = new Page();
        page.setCurrent(current);
        page.setSize(size);

        String agencyCode = null;
        if(!CoreConstans.CODE_SUPER_ADMIN.equals(RequestContext.getAgencyCode()))
        {
            agencyCode = RequestContext.getAgencyCode();
        }
        return ResponseHelper.buildResponse(roleService.qeuryByName(page,roleName, agencyCode));
    }


    /**
     *
     * @return
     */
    @TycloudOperation(operation = "获取当前机构所有的角色",ApiLevel = UserType.AGENCY)
    @RequestMapping(value = "/agency", method = RequestMethod.GET)
    public ResponseModel<List<RoleModel>> selectByAgency ()
    {
        return ResponseHelper.buildResponse(roleService.selectByAgency(RequestContext.getAgencyCode()));
    }








    @TycloudOperation(operation = "查询单个角色信息",ApiLevel = UserType.AGENCY,needAuth = false)
    @RequestMapping(value = "/{sequenceNBR}", method = RequestMethod.GET)
    public ResponseModel<RoleModel> queryByCode(
            @PathVariable(value = "sequenceNBR") Long  sequenceNBR) throws Exception {
        return ResponseHelper.buildResponse(roleService.queryBySeq(sequenceNBR));
    }

    @TycloudOperation( operation = "创建角色",ApiLevel = UserType.AGENCY)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseModel<RoleModel> createRole(@RequestBody RoleModel roleModel) throws Exception
    {
        roleModel.setCreateTime(new Date());
        roleModel.setLockStatus(PropertyValueConstants.LOCK_STATUS_UNLOCK);
        roleModel.setCreateUserId(RequestContext.getExeUserId());
        roleModel.setAgencyCode(RequestContext.getAgencyCode());
        roleModel = roleService.createRole(roleModel);
        return ResponseHelper.buildResponse(roleModel);
    }

    @TycloudOperation(operation = "更新角色",ApiLevel = UserType.AGENCY)
    @RequestMapping(value = "/{sequenceNBR}", method = RequestMethod.PUT)
    @RestEventTrigger("roleUpdateEventHandler")
    public ResponseModel<RoleModel> updateRole(@RequestBody RoleModel roleModel, @PathVariable Long sequenceNBR) throws Exception
    {
        roleModel.setSequenceNbr(sequenceNBR);
        return ResponseHelper.buildResponse(roleService.updateRole(roleModel));
    }



    @TycloudOperation( operation = "删除角色",ApiLevel = UserType.SUPER_ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseModel<Boolean> deleteMenu(@PathVariable Long   id) throws Exception
    {

        return ResponseHelper.buildResponse(roleService.deleteRole(id));
    }


	
}
