package com.app.cust.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.base.service.BaseGnrTxReqNewService;
import com.app.base.util.CryptoUtil;
import com.app.base.util.MapUtil;
import com.app.base.util.StringUtil;
import com.app.cust.dao.Cust01DAO;
import com.app.cust.service.Cust01Service;
import com.app.cust.util.CustUtil;
import com.app.table.cust.dao.TbCustBaseDAO;
import com.app.table.cust.model.TbCustBaseVO;

@Service("cust01Service")
public class Cust01ServiceImpl  implements Cust01Service{

    /** 기본(공통) KEY 생성  **/
    @Resource(name="baseGnrTxReqNewService")
    private BaseGnrTxReqNewService baseGnrTxReqNewService;
    
    @Resource(name="tbCustBaseDAO")
    private TbCustBaseDAO tbCustBaseDAO; 
    
    /** cust01DAO **/
    @Resource(name="cust01DAO")
    private Cust01DAO cust01DAO;
    
    @Override
    public Map<String, Object> signUp(Map<String, Object> inMap) throws Exception {
        
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        
        // 입력 필수값 검증 START
        if ( StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("nm")) ) )  {
            throw new RuntimeException("이름을 입력하세요.");
        }
        if ( StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("alias")) ) )  {
            throw new RuntimeException("별명을 입력하세요.");
        }
        if ( StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("pwd")) ) )  {
            throw new RuntimeException("비밀번호를 입력하세요.");
        }
        if ( StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("telNo")) ) )  {
            throw new RuntimeException("전화번호를 입력하세요.");
        }
        if ( StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("email")) ) )  {
            throw new RuntimeException("이메일을 입력하세요.");
        }
        // 입력 필수값 검증 END
        
        // 입력값 LOCAL변수 SET
        String nm    = MapUtil.getNvlString( inMap.get("nm") );
        String alias = MapUtil.getNvlString( inMap.get("alias") );
        String pwd   = MapUtil.getNvlString( inMap.get("pwd") );
        String telNo = MapUtil.getNvlString( inMap.get("telNo") );
        String email = MapUtil.getNvlString( inMap.get("email") );
        String sex   = MapUtil.getNvlString( inMap.get("sex") );
        /*------------------------------------------------------------------------------*/
        // 정합성 검증 START
        
        // 이름 검증
        System.out.println("@@@@NM"+nm);
        if ( !CustUtil.nmCheck(nm ) ) {
            throw new RuntimeException("이름 정합성 오류."); 
        }
        // 별명 검증
        if ( !CustUtil.aliasCheck(alias) ) {
            throw new RuntimeException("별명 정합성 오류."); 
        }
        // 패스워드 검증
        if ( !CustUtil.passwdCheck( pwd ) ) {
            throw new RuntimeException("비밀번호 정합성 오류."); 
        }
        // 전화번호 검증
        if ( !CustUtil.telNoCheck( telNo ) ) {
            throw new RuntimeException("전화번호 정합성 오류."); 
        }
        // 이메일 검증
        if ( !CustUtil.emailCheck( email ) ) {
            throw new RuntimeException("이메일 정합성 오류."); 
        }
        // 성별 검증
        if ( !CustUtil.sexCheck( sex ) ) {
            throw new RuntimeException("성별 정합성 오류."); 
        }
        // 정합성 검증 END
        /*------------------------------------------------------------------------------*/
        
        // 고객번호 KEY 생성
        // @TODO I1:개인, C1:사업자, F1:외국인...
        // demo 버전에선 I1만 생성
        // 
        StringBuffer sbKeyTxt = new StringBuffer();
        sbKeyTxt.append("I1"); // 개인
        String custNo = baseGnrTxReqNewService.getKeyId_Tx_Requires_New("CUST0001", sbKeyTxt.toString(), "고객기본", 8);
        
        // 패스워드 암호화
        String encPwd = CryptoUtil.sha256(pwd);
     
        /*------------------------------------------------------------------------------*/
        // VO Setting Start
        TbCustBaseVO inCustBaseVO = new TbCustBaseVO();
        inCustBaseVO.setCustNo(custNo);
        inCustBaseVO.setCustGb("1"); // 1:개인, 2:기업, 3:외국인
        inCustBaseVO.setNm(nm);
        inCustBaseVO.setAlias(alias);
        inCustBaseVO.setPwd(encPwd);
        inCustBaseVO.setTelNo(telNo);
        inCustBaseVO.setEmail(email);
        inCustBaseVO.setSex(sex);
        
        // DAO call
        tbCustBaseDAO.doInsert(inCustBaseVO);
        
        return rtnMap;
    }
    
    @Override
    public Map<String, Object> signIn(Map<String, Object> inMap) throws Exception {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        
        // 입력 필수값 검증 START
        if ( StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("pwd")) ) )  {
            throw new RuntimeException("비밀번호를 입력하세요.");
        }
        if ( StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("email")) ) )  {
            throw new RuntimeException("이메일을 입력하세요.");
        }
        // 입력 필수값 검증 END
        
        // 입력값 LOCAL변수 SET
        String pwd   = MapUtil.getNvlString( inMap.get("pwd") );
        String email = MapUtil.getNvlString( inMap.get("email") );
        /*------------------------------------------------------------------------------*/
        // 패스워드 검증
        if ( !CustUtil.passwdCheck( pwd ) ) {
            throw new RuntimeException("비밀번호 정합성 오류."); 
        }
        // 이메일 검증
        if ( !CustUtil.emailCheck( email ) ) {
            throw new RuntimeException("이메일 정합성 오류."); 
        }
        /*------------------------------------------------------------------------------*/
        
        // 패스워드 암호화
        String encPwd = CryptoUtil.sha256(pwd);
        
        // Email로 고객번호 SELECT
        List<Map<String, Object>> rtnMapList = cust01DAO.selectCustInfoByEmail(inMap);
        if ( rtnMapList == null || rtnMapList.size() == 0 ) {
            throw new RuntimeException("등록되어 있지 않은 (ID)이메일주소 입니다.");
        }
        
        // 고객기본 원장 SELECT
        // VO Setting Start
        TbCustBaseVO custBaseVO = new TbCustBaseVO();
        custBaseVO.setCustNo(MapUtil.getNvlString(rtnMapList.get(0).get("custNo")));
        custBaseVO = tbCustBaseDAO.doSelectForUpdate(custBaseVO);
        
        System.out.println("1."+custBaseVO.getCustNo());
        System.out.println("2."+custBaseVO.getEmail());
        System.out.println("S."+custBaseVO.getPwd());
        System.out.println("I."+encPwd);
        if ( ! encPwd.equals(custBaseVO.getPwd()) ) {
            throw new RuntimeException("비밀번호를 확인하여 주세요.");
        }
        
        // 인증서..?? 토큰..??
        return rtnMap;
    }

    
    @Override
    public Map<String, Object> getCustInfo(Map<String, Object> inMap) throws Exception {
        
        /* 변수 선언 */
        List<Map<String, Object>> rtnMapList = null;
        
        // 입력 필수값 검증 START
        if ( StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("type")) ) )  {
            throw new RuntimeException("비밀번호를 입력하세요.");
        }
        
        /* ---------------------------------------------------------------------*/
        if ( "telNo".equals( inMap.get("type") ) )  {
            if ( StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("telNo")) ) )  {
                throw new RuntimeException("전화번호를 입력하세요.");
            }
        }
        else
        if ( "email".equals( inMap.get("type") ) )  {
            if ( StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("email")) ) )  {
                throw new RuntimeException("이메일을 입력하세요.");
            }
        }
        
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("telNo", MapUtil.getNvlString( inMap.get("telNo")));
        searchMap.put("email", MapUtil.getNvlString( inMap.get("email")));
        
        /* 회원정보 조회 */
        System.out.println("IMPL:getCustInfo");
        System.out.println("inMap : " + searchMap);
        
        if ( "telNo".equals( inMap.get("type") ) )  {
            rtnMapList = cust01DAO.selectCustInfoByTelNo(searchMap);
        }
        else
        if ( "email".equals( inMap.get("type") ) )  {
            rtnMapList = cust01DAO.selectCustInfoByEmail(searchMap);
        }
        else {
            throw new RuntimeException("System Code Error 조회구분 오류.");
        }
        
        if ( rtnMapList == null || rtnMapList.size() == 0 ) {
            Map<String, Object> defaultMap= new HashMap<String, Object>();
            defaultMap.put("custNo", "");
            defaultMap.put("custGb", "");
            defaultMap.put("nm"    , "");
            defaultMap.put("alias" , "");
            defaultMap.put("telNo" , "");
            defaultMap.put("email" , "");
            defaultMap.put("sex"   , "");
            return defaultMap; 
        } else {
            rtnMapList.get(0).remove("pwd");
        }
        
        return rtnMapList.get(0);
    }

    @Override
    public Map<String, Object> custInfoList(Map<String, Object> inMap) {
        
        Map<String, Object> rtnMap= new HashMap<String, Object>();
        int ordCount = 0;
        List<Map<String, Object>> rtnMapList = null;
        /* 회원 목록 조회 */
        System.out.println("IMPL:getCustOrdList");
        
        String nm    = "";
        String email = "";
        int    page  = 1;      // default : 첫페이지 '1'
        int    limit = 10;     // default : '10'건
        String order = "ASC";  // default : ASC
        
        String rtnPreviousUrl = "";
        String rtnNextUrl = "";
        
        Map<String, Object> searchMap = new HashMap<String, Object>();
        if ( MapUtil.getInt(inMap.get("page")) != 0 )  {
            page  = MapUtil.getInt(inMap.get("page"));
        } else 
        
        if ( MapUtil.getInt(inMap.get("limit")) != 0 ) {
            limit =  MapUtil.getInt(inMap.get("limit"));
        }

        if ( ! StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("order")) ) )  {
            order = MapUtil.getNvlString(inMap.get("order")) ;
        }
        
        inMap.put("nm"   , nm      );
        inMap.put("email", email   );
        inMap.put("page" , page    );
        inMap.put("limit", limit   );
        inMap.put("order", order   );
        
        /*-----------------------------------------------------------------*/
        // 정합성검증
        
        // limit 50건 이상
        /*-----------------------------------------------------------------*/
        
        ordCount = cust01DAO.countCustInfoList(searchMap);
        System.out.println("countCustOrdList" + ordCount);
        if ( ordCount == 0 ) {
            rtnMapList = new ArrayList<Map<String, Object>>();
            rtnMap.put("elements", rtnMapList);
            rtnMap.put("total_count", 0);
            rtnMap.put("previous_url", "");
            rtnMap.put("next_url", "");
        } else {
            rtnMapList = cust01DAO.getCustInfoList(searchMap);
            System.out.println(searchMap);
            System.out.println("countCustOrdList" + rtnMapList.size());
            if ( rtnMapList.size() == 0 ) {
                // @TODO 추후 개선.
                throw new RuntimeException("API Rule 위배. 비정상적인 조회접근");
            }
            rtnMap.put("elements", rtnMapList);
            rtnMap.put("total_count", ordCount);
            // 이전PAGE 여부
            if ( MapUtil.getInt(inMap.get("page")) <= 1 ) { rtnMap.put("previous_url", ""); }
            else {
                rtnPreviousUrl = "/api/setlSvc/custOrdList?page="+(page-1)+"&limit="+limit+"&order="+order;
                rtnMap.put("previous_url", rtnPreviousUrl);
            }
            if ( ordCount <= page*limit ) { rtnMap.put("next_url", ""); }
            else {
                rtnNextUrl = "/api/setlSvc/custOrdList?page="+(page+1)+"&limit="+limit+"&order="+order;
                rtnMap.put("next_url", rtnNextUrl);
            }
            
        }
        return rtnMap;
    }

    
}
