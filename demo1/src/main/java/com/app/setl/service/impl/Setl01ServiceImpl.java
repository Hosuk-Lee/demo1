package com.app.setl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.base.config.DemoServiceException;
import com.app.base.util.MapUtil;
import com.app.base.util.StringUtil;
import com.app.setl.dao.Setl01DAO;
import com.app.setl.service.Setl01Service;

@Service("setl01Service")
public class Setl01ServiceImpl  implements Setl01Service{

	/** setl01DAO **/
    @Resource(name="setl01DAO")
    private Setl01DAO setl01DAO;
	
	@Override
	public Map<String, Object> getCustOrdList(Map<String, Object> inMap) throws Exception {
	    
	    Map<String, Object> rtnMap= new HashMap<String, Object>();
	    int ordCount = 0;
	    List<Map<String, Object>> rtnMapList = null;
		/* 단일 회원의 주문 목록 조회 */
		System.out.println("IMPL:getCustOrdList");
		
		int    page  = 1;      // default : 첫페이지 '1'
		int    limit = 10;     // default : '10'건
		String order = "ASC";  // default : ASC
		
		String rtnPreviousUrl = "";
		String rtnNextUrl = "";
		
        Map<String, Object> searchMap = new HashMap<String, Object>();
        if ( MapUtil.getInt(inMap.get("page")) != 0 )  {
            page  = MapUtil.getInt(inMap.get("page"));
        } 
        
        if ( MapUtil.getInt(inMap.get("limit")) != 0 ) {
            limit =  MapUtil.getInt(inMap.get("limit"));
        }

        if ( ! StringUtil.isEmpty( MapUtil.getNvlString( inMap.get("order")) ) )  {
            order = MapUtil.getNvlString(inMap.get("order")) ;
        }
        
        searchMap.put("page",page);
        searchMap.put("limit",limit);
        searchMap.put("order",order);
        
        /*-----------------------------------------------------------------*/
        // 정합성검증
        
        // limit 50건 이상
        /*-----------------------------------------------------------------*/
        
        ordCount = setl01DAO.countCustOrdList(searchMap);
        System.out.println("countCustOrdList" + ordCount);
        if ( ordCount == 0 ) {
            rtnMapList = new ArrayList<Map<String, Object>>();
            rtnMap.put("elements", rtnMapList);
            rtnMap.put("total_count", 0);
            rtnMap.put("previous_url", "");
            rtnMap.put("next_url", "");
        } else {
            rtnMapList = setl01DAO.getCustOrdList(searchMap);
            System.out.println(searchMap);
            System.out.println("countCustOrdList" + rtnMapList.size());
            if ( rtnMapList.size() == 0 ) {
                // @TODO 추후 개선.
                throw new DemoServiceException("API Rule 위배. 비정상적인 조회접근");
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
