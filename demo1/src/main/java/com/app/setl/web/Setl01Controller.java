package com.app.setl.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.base.util.MapUtil;
import com.app.setl.service.Setl01Service;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description 결제내역 조회
*              01-10 까지는 조회서비스 성격에 맞게 소스를 분배 나타한다.
*              
* @author 이호석
* @since 2021.05.03
* @version 1.0
*
*  수정일       수정자      수정내용
*  ----------   --------    ---------------------------
*  2021.05.03   이호석      최초 생성
*
*
*/

@RestController
@RequestMapping(value="/api/setlSvc")
public class Setl01Controller {


    @Resource(name = "setl01Service")
    private Setl01Service setl01Service;
    
    @ApiOperation(value = "custOrdList", notes = "단일 회원의 주문 목록 조회")
    @GetMapping("/custOrdList")
    public @ResponseBody Map<String, Object> custOrdList(
            @ApiParam(value = "회원 주문 목록 시작 페이지(기본값:1)", required = false, example = "1") @RequestParam(name="page",required = true) String page, 
            @ApiParam(value = "한 페이지에 가져올 주문 최대 수(기본값: 10, 최댓값: 50)", required = false, example = "10") @RequestParam(name="limit",required = true) String limit,
            @ApiParam(value = "주문목록 정렬 순서, 오름차순(\"asc\") 또는 내림차순(\"desc\")(기본값 \"asc\")", required = false, example = "asc") @RequestParam(name="order",required = true) String order
            ) {
        
        Map<String, Object> inMap = new HashMap<String, Object>();
        Map<String, Object> rtnMap = null;
        
        try {
            
            System.out.println("getCustInfo"+inMap);
            System.out.println("----------------------");
            System.out.println("1." + page  );
            System.out.println("2." + limit );
            System.out.println("3." + order );
            
            inMap.put("page" , page   );
            inMap.put("limit", limit  );
            inMap.put("order", order  );
            rtnMap = setl01Service.getCustOrdList(inMap);
        } catch (Exception e) {
            rtnMap = new HashMap<String, Object>();
            List<Map<String, Object>> rtnMapList = new ArrayList<Map<String, Object>>();
            rtnMap.put("elements", rtnMapList);
            rtnMap.put("total_count", 0);
            rtnMap.put("previous_url", "");
            rtnMap.put("next_url", "");

            e.printStackTrace();
        }
        return rtnMap;
    }
    
}
