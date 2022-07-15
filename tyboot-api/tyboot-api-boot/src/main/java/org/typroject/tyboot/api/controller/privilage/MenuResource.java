package org.typroject.tyboot.api.controller.privilage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.typroject.tyboot.api.face.privilage.model.MenuModel;
import org.typroject.tyboot.api.face.privilage.service.MenuService;
import org.typroject.tyboot.api.face.privilage.service.RoleMenuService;
import org.typroject.tyboot.api.face.privilage.service.UserRoleService;
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
 * 菜单权限
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
@TycloudResource(name = "菜单权限",module = "privilege", resource = "menu")
@RequestMapping(value = "/v1/privilege/menu")
@RestController
public class MenuResource {




    @Autowired
    MenuService menuService;

    @Autowired
    UserRoleService userRoleService;


    @Autowired
    RoleMenuService roleMenuService;


    /**
     *创建菜单
     * @param menuModel
     * @return
     */
    @TycloudOperation( ApiLevel = UserType.AGENCY,needAuth = false)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseModel<MenuModel> createMenu(@RequestBody MenuModel menuModel)
    {

        menuModel.setAgencyCode(RequestContext.getAgencyCode());
        menuModel.setCreateUserId(RequestContext.getExeUserId());
        menuModel.setCreateTime(new Date());
        return ResponseHelper.buildResponse( menuService.createMenuList(menuModel));
    }


    /**
     * 查询单个权限菜单
     * @param sequenceNbr 物理主键
     * @return
     * @throws Exception
     */
    @TycloudOperation( name = "查询单个权限菜单",ApiLevel = UserType.AGENCY)
    @RequestMapping(value = "/{sequenceNBR}", method = RequestMethod.GET)
    public ResponseModel<MenuModel> seleteOne(@PathVariable Long sequenceNbr) throws Exception
    {
        return ResponseHelper.buildResponse(this.menuService.queryBySeq(sequenceNbr));
    }




    @TycloudOperation(name = "根据机构获取菜单",ApiLevel = UserType.AGENCY)
    @RequestMapping(value = "/agency/menus", method = RequestMethod.GET)
    public ResponseModel<List<MenuModel>> selectByAgency() throws Exception
    {
        return ResponseHelper.buildResponse(this.menuService.queryForList(null, RequestContext.getAgencyCode(),null,null));
    }






    @TycloudOperation(name = "更新菜单权限",ApiLevel = UserType.AGENCY)
    @RequestMapping(value = "/{sequenceNBR}", method = {RequestMethod.PUT,RequestMethod.PATCH})
    public ResponseModel<MenuModel> updateMenu(@RequestBody MenuModel menuModel, @PathVariable Long sequenceNBR) throws Exception
    {
        menuModel.setSequenceNbr(sequenceNBR);
        return ResponseHelper.buildResponse(menuService.updateMenu(menuModel));
    }


    @TycloudOperation( name = "删除权限菜单",ApiLevel = UserType.SUPER_ADMIN)
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public ResponseModel<String> deleteMenu(@PathVariable String ids) throws Exception
    {
        return ResponseHelper.buildResponse(menuService.deleteMenu(ids));
    }













}
