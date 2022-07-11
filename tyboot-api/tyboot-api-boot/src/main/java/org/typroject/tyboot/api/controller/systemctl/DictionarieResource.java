package org.typroject.tyboot.api.controller.systemctl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.typroject.tyboot.api.face.systemctl.model.DictionarieModel;
import org.typroject.tyboot.api.face.systemctl.service.DictionarieService;
import org.typroject.tyboot.core.foundation.enumeration.UserType;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典管理
 */
@TycloudResource(name = "字典管理",module = "systemctl", resource = "DictionarieResource")
@RequestMapping(value = "/v1/systemctl/dictionary")
@RestController
public class DictionarieResource {
    private final Logger logger = LogManager.getLogger(DictionarieResource.class);

    @Autowired
    private DictionarieService dictionarieService;

    /**
     * 创建字典
     * @param dictionaryModel
     * @return
     * @throws Exception
     */
    @TycloudOperation( operation = "创建字典",ApiLevel = UserType.ANONYMOUS)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseModel<DictionarieModel> createDictionary(@RequestBody DictionarieModel dictionaryModel) throws Exception
    {
        dictionaryModel = dictionarieService.createDict(dictionaryModel);
        return ResponseHelper.buildResponse(dictionaryModel);
    }


    @TycloudOperation(operation = "删除字典",ApiLevel = UserType.AGENCY)
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseModel<Boolean> deleteDictionary(@RequestBody String[] ids) throws Exception
    {
        List<Long> seqs = new ArrayList<>();
        for(int i = 0;i<ids.length ;i++)
        {
            seqs.add(Long.parseLong(ids[i]));
        }
        return ResponseHelper.buildResponse(dictionarieService.deleteDicts(seqs));
    }


    @TycloudOperation(operation = "更新字典",ApiLevel = UserType.AGENCY)
    @RequestMapping(value = "/{sequenceNbr}", method = RequestMethod.PUT)
    public ResponseModel<DictionarieModel> updateDictionary(
            @RequestBody DictionarieModel dictionaryModel,
            @PathVariable(value="sequenceNbr") String sequenceNbr) throws Exception {
        dictionaryModel.setDictCode(sequenceNbr);
        return ResponseHelper.buildResponse(dictionarieService.updateDict(dictionaryModel));
    }


    /**
     * 根据code获取字典
     * @param dictCode  字典编号
     * @return
     * @throws Exception
     */
    @TycloudOperation(operation = "根据code获取字典",ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/{dictCode}", method = RequestMethod.GET)
    public ResponseModel<DictionarieModel> queryByCode(
            @PathVariable(value = "dictCode") String dictCode) throws Exception {
        return ResponseHelper.buildResponse(dictionarieService.queryByCode("SUPER_ADMIN",dictCode));
    }


    /**
     * 分页查询字典信息
     * @param agencyCode  机构编号
     * @param dictName  字典名称
     * @param dictCode 字典编号
     * @param current  当前页
     * @param size     分页长度
     * @return
     * @throws Exception
     */
    @TycloudOperation(operation = "分页查询字典信息",ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseModel<IPage> queryForPage(
            @RequestParam(value = "agencyCode",required = false) String agencyCode,
            @RequestParam(value = "dictName", required = false) String dictName,
            @RequestParam(value = "dictCode", required = false) String dictCode,
            @RequestParam(value ="current") int current,
            @RequestParam(value = "size") int size) throws Exception
    {
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        return ResponseHelper.buildResponse(dictionarieService.queryDictPage(page,agencyCode,dictName,dictCode));
    }

    /**
     * 检查字典编号是否可用
     * @param dictCode 字典编号
     * @return
     * @throws Exception
     */
    @TycloudOperation(operation = "检查字典编号是否可用",ApiLevel = UserType.ANONYMOUS)
    @RequestMapping(value = "/{dictCode}/available", method = RequestMethod.GET)
    public ResponseModel<Boolean> isDictionaryCodeAvailable(
            @PathVariable(value = "dictCode") String dictCode) throws Exception {
        return ResponseHelper.buildResponse(ValidationUtil.isEmpty(dictionarieService.queryByCode("SUPER_ADMIN",dictCode)));
    }

    @TycloudOperation(operation = "测试接口",ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/string/teststring", method = RequestMethod.GET)
    public String isDictionaryCodeAvailable() throws Exception {
        return "success";
    }



}