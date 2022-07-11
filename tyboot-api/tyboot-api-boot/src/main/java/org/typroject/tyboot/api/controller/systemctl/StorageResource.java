package org.typroject.tyboot.api.controller.systemctl;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.typroject.tyboot.core.restful.doc.TycloudResource;

import java.util.Set;

/**
 * 存储服务
 */
@RestController
@RequestMapping(name = "存储服务",value = "/v1/systemctl/storage")
@TycloudResource(module = "systemctl", resource = "storage", description = "存储服务")
public class StorageResource {

	//@Autowired
	//private Storage storage;

	//@Value("${qiniu.bucket}")
	private String qiniuBucket;

	private static Set<String> spaceNames;
	
	/*@TycloudOperation( ApiLevel = UserType.ANONYMOUS)
	@ApiOperation(value="根据空间获取七牛token")
	@RequestMapping(value = "/token/{space}", method = RequestMethod.GET)
	public ResponseModel<String> flushQiniuToken(@PathVariable String space) throws Exception {
		return ResponseHelper.buildResponse(storage.flushQiniuToken(this.checkSpaceName(space)));
	}


	@TycloudOperation( ApiLevel = UserType.ANONYMOUS)
	@ApiOperation(value="获取图片空间accessToken")
	@RequestMapping(value = "/token/zoulu/image", method = RequestMethod.GET)
	public ResponseModel<String> flushQiniuToken() throws Exception {
		return ResponseHelper.buildResponse(storage.flushQiniuToken(qiniuBucket));
	}


	
	@TycloudOperation( ApiLevel = UserType.ANONYMOUS)
	@RequestMapping(value = "/{space}", method = RequestMethod.DELETE)
	public ResponseModel<String> deleteFile(
			@PathVariable String space,
			@RequestParam(value ="fileName") String fileName) throws Exception {
		storage.deleteFile(this.checkSpaceName(space), fileName);
		return ResponseHelper.buildResponse("SUCCEED");
	}


	private String checkSpaceName(String spaceName) throws Exception
	{
		if(ValidationUtil.isEmpty(qiniuBucket))
			throw new Exception("空间名称有误.");

		if(ValidationUtil.isEmpty(spaceNames))
		{
			spaceNames = new HashSet<>();
			String [] names = StringUtil.string2Array(qiniuBucket);
			for(String name:names)
			{
				spaceNames.add(name);
			}

		}

		if(!spaceNames.contains(spaceName))
		{
			throw new Exception("空间名称有误.");
		}
		return spaceName;
	}
*/

}
