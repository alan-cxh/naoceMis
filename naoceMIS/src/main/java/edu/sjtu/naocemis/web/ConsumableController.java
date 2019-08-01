package edu.sjtu.naocemis.web;

import edu.sjtu.common.util.NicResult;
import edu.sjtu.naocemis.dto.ConsumableQueryDto;
import edu.sjtu.naocemis.dto.UserDto;
import edu.sjtu.naocemis.service.ConsumableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author alan_chen
 * @date 2019/7/31
 */
@RestController
@RequestMapping("consumable")
public class ConsumableController {

    @Autowired
    ConsumableService consumableService;


    /**
     * 模糊查询列表
     * @param request
     * @param dto
     * @return
     */
    @GetMapping("getConsumableList")
    public NicResult getConsumableList(HttpServletRequest request,  ConsumableQueryDto dto) {
        // todo 判断经办人和管理员，过滤回显数据
        return NicResult.ok( consumableService.getList(dto));
    }

    /**
     * 根据flowId查询详情
     * @param flowId
     * @return
     */
    @GetMapping("get/{flowId}")
    public NicResult getConsumable(@PathVariable String flowId) {
        return NicResult.ok(consumableService.getByFlowId(flowId));
    }

    @GetMapping("getApplyerList")
    public NicResult getApplyerList( UserDto userDto) {
        return NicResult.ok(consumableService.getApplyerList(userDto));
    }

    /**
     *  导出
     */
    @RequestMapping("/ExcelDownload")
    public void excelDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }


}
