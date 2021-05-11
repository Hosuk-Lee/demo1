package com.app.cust.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.base.util.CODE;
import com.app.base.util.MapUtil;
import com.app.cust.service.Cust01Service;
import com.app.setl.web.SetlResMessage;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
@RequestMapping(value="/api/custSvc")
public class Cust01Controller {

    
    @Resource(name = "cust01Service")
    private Cust01Service cust01Service;
    
    @ApiOperation(value = "단일 회원 상세 정보 조회(전화번호)", 
                  notes = "단일 회원 상세 정보 조회\n"
                          + "- 전화번호로 회원 정보를 검색합니다.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = CustResMessage.custInfo_200),
        @ApiResponse(code = 401, message = "인증 오류 (로그인 만료, 토큰 인증오류 등)"),
        @ApiResponse(code = 500, message = "서버 오류"),

    })
    @GetMapping("/custInfo/telNo/{telNo}")
    public @ResponseBody Map<String, Object> custInfoGetByTelNo(
            @ApiParam(value = "검색 전화번호", required = true, example = "01099990001") @PathVariable(name="telNo",required = true) String telNo ) {
        Map<String, Object> inMap = new HashMap<String, Object>();
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            
            System.out.println("getCustInfo");
            System.out.println("----------------------");
            System.out.println("6." + telNo );
            System.out.println("----------------------");

            inMap.put("type" , "telNo" );
            inMap.put("telNo", telNo   );
            
            rtnMap = cust01Service.getCustInfo(inMap);
        } 
        catch (Exception e) {
            
            e.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , CODE.SYSTEM_ERROR_MESSAGE);
            
            // 시스템 오류여도 알려주지 않는다.
            rtnMap= new HashMap<String, Object>();
            rtnMap.put("custNo", "");
            rtnMap.put("custGb", "");
            rtnMap.put("nm"    , "");
            rtnMap.put("alias" , "");
            rtnMap.put("telNo" , "");
            rtnMap.put("email" , "");
            rtnMap.put("sex"   , "");
        }
        return rtnMap;
    }
    
    @ApiOperation(value = "단일 회원 상세 정보 조회 (이메일)",
                  notes = "단일 회원 상세 정보 조회\n"
                        + "- 이메일로 회원 정보를 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CustResMessage.custInfo_200),
            @ApiResponse(code = 401, message = "인증 오류 (로그인 만료, 토큰 인증오류 등)"),
            @ApiResponse(code = 500, message = "서버 오류"),
    })
    @GetMapping("/custInfo/email/{email}")
    public @ResponseBody Map<String, Object> custInfoGetByEmail(
            @ApiParam(value = "검색 이메일", required = true, example = "MAIL0001@idus.com") @PathVariable(name="email",required = true) String email ) {
        Map<String, Object> inMap = new HashMap<String, Object>();
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            
            System.out.println("custInfoGetByEmail");
            System.out.println("----------------------");
            System.out.println("6." + email );
            System.out.println("----------------------");

            inMap.put("type" , "email" );
            inMap.put("email", email   );
            
            rtnMap = cust01Service.getCustInfo(inMap);
        } 
        catch (Exception e) {
            
            e.printStackTrace(); // 모니터링을 위한 System Error LOGGING [DB or FILE] 
            
            // 시스템 오류여도 알려주지 않는다.
            rtnMap= new HashMap<String, Object>();
            rtnMap.put("custNo", "");
            rtnMap.put("custGb", "");
            rtnMap.put("nm"    , "");
            rtnMap.put("alias" , "");
            rtnMap.put("telNo" , "");
            rtnMap.put("email" , "");
            rtnMap.put("sex"   , "");
            
        }
        return rtnMap;
    }
    
    @ApiOperation(value = "여러 회원 목록 조회", 
                  notes = "단일 회원의 주문 목록 조회\n"
                        + "- 페이지네이션으로 일정 단위로 조회합니다.\n"
                        + "- 이름, 이메일을 이용하여 검색 기능이 필요합니다.\n"
                        + "- 각 회원의 마지막 주문 정보를 출력합니다.\n")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = CustResMessage.custInfoList_200),
        @ApiResponse(code = 401, message = "인증 오류 (로그인 만료, 토큰 인증오류 등)"),
        @ApiResponse(code = 500, message = "서버 오류"),
    })
    @GetMapping("/custInfoList")
    public @ResponseBody Map<String, Object> custInfoList(
            @ApiParam(value = "검색 이름 (LIKE 조건)",   required = false, example = "NAME1") @RequestParam(name="nm",required = false) String nm,
            @ApiParam(value = "검색 이메일 (LIKE 조건)", required = false, example = "MAIL0001@idus.com") @RequestParam(name="email",required = false) String email,
            @ApiParam(value = "회원 목록 시작 페이지(기본값:1)", required = false, example = "1") @RequestParam(name="page",required = true) String page, 
            @ApiParam(value = "한 페이지에 가져올 회원 최대 수(기본값: 10, 최댓값: 50)", required = false, example = "10") @RequestParam(name="limit",required = true) String limit,
            @ApiParam(value = "회원 목록 정렬 순서, 오름차순(\"asc\") 또는 내림차순(\"desc\")(기본값 \"asc\")", required = false, example = "asc") @RequestParam(name="order",required = true) String order )
    {
        Map<String, Object> inMap = new HashMap<String, Object>();
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            
            System.out.println("custInfoList");
            System.out.println("----------------------");
            System.out.println("1." + nm    );
            System.out.println("2." + email );
            System.out.println("3." + page  );
            System.out.println("4." + limit );
            System.out.println("5." + order );
            System.out.println("----------------------");

            inMap.put("nm"   , nm      );
            inMap.put("email", email   );
            inMap.put("page" , page    );
            inMap.put("limit", limit   );
            inMap.put("order", order   );
            
            /* 여러 회원 목록 조회 :
                - 페이지네이션으로 일정 단위로 조회합니다.
                - 이름, 이메일을 이용하여 검색 기능이 필요합니다.
                - 각 회원의 마지막 주문 정보
            */
            rtnMap = cust01Service.custInfoList(inMap);
        } 
        catch (Exception e) {
            
            e.printStackTrace(); // 모니터링을 위한 System Error LOGGING [DB or FILE] 
            
            // 시스템 오류여도 알려주지 않는다.
            rtnMap = new HashMap<String, Object>();
            List<Map<String, Object>> rtnMapList = new ArrayList<Map<String, Object>>();
            rtnMap.put("elements", rtnMapList);
            rtnMap.put("total_count", 0);
            rtnMap.put("previous_url", "");
            rtnMap.put("next_url", "");
            
        }
        return rtnMap;
    }
    
    
    
    
    
    
    
    
    /*------------------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------------------*/
    
    /**
     * 
     * 단일회원조회 사용안함(X)
     * 
     */
//    @PostMapping("/getCustInfo")
    public @ResponseBody Map<String, Object> custInfoPost(@RequestBody Map<String, Object> inMap) {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            
            System.out.println("getCustInfo"+inMap);
            System.out.println("----------------------");
            System.out.println("5." + MapUtil.getNvlString(inMap.get("telNo") ));
            System.out.println("6." + MapUtil.getNvlString(inMap.get("email") ));
            System.out.println("----------------------");
            
            rtnMap = cust01Service.getCustInfo(inMap);
        
        } catch (RuntimeException re) {
            System.out.println("@@ERROR@@" + re.getMessage());
            re.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , re.getMessage());
        } 
        catch (Exception e) {
            
            e.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , CODE.SYSTEM_ERROR_MESSAGE);
            
        }
        return rtnMap;
    }


// ------------------------------------------------------------------------------------------------------
//    단일회원조회 사용안함(X)
//    @GetMapping("/getCustInfo")
    public @ResponseBody Map<String, Object> custInfoGet(@RequestParam  Map<String, Object> inMap) {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            
            System.out.println("getCustInfo"+inMap);
            System.out.println("----------------------");
            System.out.println("5." + MapUtil.getNvlString(inMap.get("telNo") ));
            System.out.println("6." + MapUtil.getNvlString(inMap.get("email") ));
            System.out.println("----------------------");
            
            rtnMap = cust01Service.getCustInfo(inMap);
        } catch (RuntimeException re) {
            System.out.println("@@ERROR@@" + re.getMessage());
            re.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , re.getMessage());
        } 
        catch (Exception e) {
            
            e.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , CODE.SYSTEM_ERROR_MESSAGE);
            
        }
        return rtnMap;
    }
}
