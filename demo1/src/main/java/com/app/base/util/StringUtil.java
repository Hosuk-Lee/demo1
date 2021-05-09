package com.app.base.util;

import java.io.BufferedInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @Description String Util 클래스
 * @author 이호석
 * @since 2021.05.06
 * @version 1.0
 *
 *  수정일       수정자     수정내용
 *  ----------   --------   ---------------------------
 *  2021.05.06   이호석     최초 생성
 *
 *
 */

public class StringUtil {


	/**
     * 빈 문자열 <code>""</code>.
     */
    public static final String EMPTY = "";

    /**
     * <p>Padding을 할 수 있는 최대 수치</p>
     */
    private static final int PAD_LIMIT = 8192;

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     * @param source 원본 문자열 배열
     * @param output 더할문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, String output, int slength) {
        String returnVal = null;
        if (source != null) {
            if (source.length() > slength) {
                returnVal = source.substring(0, slength) + output;
            } else {
            	returnVal = source;
            }
        }
        return returnVal;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     * @param source 원본 문자열 배열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int slength) {
        String result = null;
        if (source != null) {
            if (source.length() > slength) {
                result = source.substring(0, slength);
            } else {
            	result = source;
            }
        }
        return result;
    }

    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * </p>
     *
     * <pre>
     *  StringUtil.isEmpty(null)      = true
     *  StringUtil.isEmpty("")        = true
     *  StringUtil.isEmpty(" ")       = false
     *  StringUtil.isEmpty("bob")     = false
     *  StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || "".equals(str);
    }

    /**
     * 주어진 문자열이 Null이거나 트림(Trim) 후에 비어있는지(Empty) 여부를 검사한다.
     *
     * @param s String
     * @return boolean
     */
    public static boolean isEmptyTrim(String s) {
        return s == null || "".equals(s.trim());
    }


    /**
     * <p>기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.</p>
     *
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @param remove  입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String remove(String str, char remove) {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }

    /**
     * <p>문자열 내부의 콤마 character(,)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeCommaChar(null)       = null
     * StringUtil.removeCommaChar("")         = ""
     * StringUtil.removeCommaChar("asdfg,qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str 입력받는 기준 문자열
     * @return " , "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeCommaChar(String str) {
        return remove(str, ',');
    }
    /**
     * <p>문자열 내부의 콜론 character(:)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeCommaChar(null)       = null
     * StringUtil.removeCommaChar("")         = ""
     * StringUtil.removeCommaChar("asdfg:qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str 입력받는 기준 문자열
     * @return " : "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeColonChar(String str) {
        return remove(str, ':');
    }
    /**
     * <p>문자열 내부의 마이너스 character(-)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeMinusChar(null)       = null
     * StringUtil.removeMinusChar("")         = ""
     * StringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @return " - "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeMinusChar(String str) {
        return remove(str, '-');
    }


    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;

        while (srcStr.indexOf(subject) >= 0) {
            preStr = srcStr.substring(0, srcStr.indexOf(subject));
            nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
            srcStr = nextStr;
            rtnStr.append(catString("", preStr, object));
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }

    /**
     * 원본 문자열의 포함된 특정 문자열 첫번째 한개만 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열 / source 특정문자열이 없는 경우 원본 문자열
     */
    public static String replaceOnce(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        if (source.indexOf(subject) >= 0) {
            preStr = source.substring(0, source.indexOf(subject));
            nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
            rtnStr.append(catString(preStr, object, nextStr));
            return rtnStr.toString();
        } else {
            return source;
        }
    }

    /**
     * <code>subject</code>에 포함된 각각의 문자를 object로 변환한다.
     *
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replaceChar(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;

        char chA;

        for (int i = 0; i < subject.length(); i++) {
            chA = subject.charAt(i);

            if (srcStr.indexOf(chA) >= 0) {
                preStr = srcStr.substring(0, srcStr.indexOf(chA));
                nextStr = srcStr.substring(srcStr.indexOf(chA) + 1, srcStr.length());
                srcStr = rtnStr.append(catString(preStr, object, nextStr)).toString();
            }
        }

        return srcStr;
    }

    /**
     * <p><code>str</code> 중 <code>searchStr</code>의 시작(index) 위치를 반환.</p>
     *
     * <p>입력값 중 <code>null</code>이 있을 경우 <code>-1</code>을 반환.</p>
     *
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf("", "")           = 0
     * StringUtil.indexOf("aabaabaa", "a")  = 0
     * StringUtil.indexOf("aabaabaa", "b")  = 2
     * StringUtil.indexOf("aabaabaa", "ab") = 1
     * StringUtil.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str  검색 문자열
     * @param searchStr  검색 대상문자열
     * @return 검색 문자열 중 검색 대상문자열이 있는 시작 위치 검색대상 문자열이 없거나 null인 경우 -1
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.indexOf(searchStr);
    }


    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>defaultStr</code>을 반환한다.
     * </p>
     *
     * <pre>
     * StringUtil.decode(null, null, "foo", "bar")= "foo"
     * StringUtil.decode("", null, "foo", "bar") = "bar"
     * StringUtil.decode(null, "", "foo", "bar") = "bar"
     * StringUtil.decode("하이", "하이", null, "bar") = null
     * StringUtil.decode("하이", "하이  ", "foo", null) = null
     * StringUtil.decode("하이", "하이", "foo", "bar") = "foo"
     * StringUtil.decode("하이", "하이  ", "foo", "bar") = "bar"
     * </pre>
     *
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @param defaultStr sourceStr와 compareStr의 값이 다를 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 defaultStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr, String defaultStr) {
        if (sourceStr == null && compareStr == null) {
            return returnStr;
        }

        if (sourceStr == null && compareStr != null) {
            return defaultStr;
        }

        if (sourceStr.trim().equals(compareStr)) {
            return returnStr;
        }

        return defaultStr;
    }

    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>sourceStr</code>을 반환한다.
     * </p>
     *
     * <pre>
     * StringUtil.decode(null, null, "foo") = "foo"
     * StringUtil.decode("", null, "foo") = ""
     * StringUtil.decode(null, "", "foo") = null
     * StringUtil.decode("하이", "하이", "foo") = "foo"
     * StringUtil.decode("하이", "하이 ", "foo") = "하이"
     * StringUtil.decode("하이", "바이", "foo") = "하이"
     * </pre>
     *
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 sourceStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr) {
        return decode(sourceStr, compareStr, returnStr, sourceStr);
    }

    /**
     * 객체가 null인지 확인하고 null인 경우 "" 로 바꾸는 메서드
     * @param str 원본 객체
     * @return resultVal 문자열
     */
    public static String nullToEmpty(String str)
    {
    	String result = "";
    	if(str != null){
    		result = str;
    	}else{
    		result = "";
    	}
        return result;
    }

    /**
     * <p>문자열에서 {@link Character#isWhitespace(char)}에 정의된
     * 모든 공백문자를 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeWhitespace(null)         = null
     * StringUtil.removeWhitespace("")           = ""
     * StringUtil.removeWhitespace("abc")        = "abc"
     * StringUtil.removeWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str  공백문자가 제거도어야 할 문자열
     * @return the 공백문자가 제거된 문자열, null이 입력되면 <code>null</code>이 리턴
     */
    public static String removeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }

        return new String(chs, 0, count);
    }

    /**
     * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @return result 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator) throws NullPointerException {
        String[] returnVal = null;
        int cnt = 1;

        int index = source.indexOf(separator);
        int index0 = 0;
        while (index >= 0) {
            cnt++;
            index = source.indexOf(separator, index + 1);
        }
        returnVal = new String[cnt];
        cnt = 0;
        index = source.indexOf(separator);
        while (index >= 0) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);

        return returnVal;
    }

    /**
	 * 입력한 문자열을 입력한  int 배열에 정의 길이 단위로 문자열 배열을 만들어 반환한다.
	 *
	 * @param sData
	 * @param nArrIdx
	 * @return
	 * @return String[]
	 */
	public static String[] split(String sData, int[] nArrIdx) {
		if (sData == null) {
			return new String[0];
		}

		byte[] bListData = sData.getBytes();
		byte[] bListDest = null;
		int nSrcPos = 0;
		String[] o_sListData = new String[nArrIdx.length];

		for (int i=0; i<nArrIdx.length; i++) {
			bListDest = new byte[nArrIdx[i]];
			System.arraycopy(bListData, nSrcPos, bListDest, 0, nArrIdx[i]);
			nSrcPos += nArrIdx[i];
			o_sListData[i] = new String(bListDest);
		}

		return o_sListData;
	}

	/**
	 * 입력한 문자열을 입력한  int 길이 만큼 잘라서 iSize 크기만큼 문자열 배열을 만들어 반환한다.
	 *
	 * @param sData
	 * @param iLength
	 * @param iSize
	 * @return String[]
	 */
	public static String[] split(String sData, int iLength, int iSize) {
		if (sData == null) sData = "";

		byte[] bListData = sData.getBytes();
		byte[] bListDest = null;
		int nSrcPos = 0;

		String[] resultData = new String[iSize];

		for ( int i = 0; i < iSize; i++ ) {
			int iBLength = iLength;
			bListDest = new byte[iLength];
			if ( nSrcPos + iLength > bListData.length ) {
				iBLength = bListData.length - nSrcPos;
			}
			System.arraycopy(bListData, nSrcPos, bListDest, 0, iBLength);
			nSrcPos += iBLength;
			resultData[i] = rightPad(new String(bListDest).trim(), iLength, "");
		}

		return resultData;
	}

    /**
     * <p>입력된 String의 앞쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripStart(null, *)          = null
     * StringUtil.stripStart("", *)            = ""
     * StringUtil.stripStart("abc", "")        = "abc"
     * StringUtil.stripStart("abc", null)      = "abc"
     * StringUtil.stripStart("  abc", null)    = "abc"
     * StringUtil.stripStart("abc  ", null)    = "abc  "
     * StringUtil.stripStart(" abc ", null)    = "abc "
     * StringUtil.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripStart(String str, String stripChars) {
        int strLen;
        if (str == null){
            return str;
        }

        strLen = str.length();
    	if (strLen == 0){
            return str;
        }

        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }

        return str.substring(start);
    }


    /**
     * <p>입력된 String의 뒤쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripEnd(null, *)          = null
     * StringUtil.stripEnd("", *)            = ""
     * StringUtil.stripEnd("abc", "")        = "abc"
     * StringUtil.stripEnd("abc", null)      = "abc"
     * StringUtil.stripEnd("  abc", null)    = "  abc"
     * StringUtil.stripEnd("abc  ", null)    = "abc"
     * StringUtil.stripEnd(" abc ", null)    = " abc"
     * StringUtil.stripEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripEnd(String str, String stripChars) {
        int end;

        if (str == null){
            return str;
        }

        end = str.length();
    	if (end == 0){
            return str;
        }

        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }

        return str.substring(0, end);
    }

    /**
     * <p>입력된 String의 앞, 뒤에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.strip(null, *)          = null
     * StringUtil.strip("", *)            = ""
     * StringUtil.strip("abc", null)      = "abc"
     * StringUtil.strip("  abc", null)    = "abc"
     * StringUtil.strip("abc  ", null)    = "abc"
     * StringUtil.strip(" abc ", null)    = "abc"
     * StringUtil.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String strip(String str, String stripChars) {
	    if (isEmpty(str)) {
	        return str;
	    }

	    String srcStr = str;
	    srcStr = stripStart(srcStr, stripChars);

	    return stripEnd(srcStr, stripChars);
    }

    /**
     * 문자열을 지정한 분리자에 의해 지정된 길이의 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @param arraylength 배열 길이
     * @return 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator, int arraylength) throws NullPointerException {
        String[] returnVal = new String[arraylength];
        int cnt = 0;
        int index0 = 0;
        int index = source.indexOf(separator);
        while (index >= 0 && cnt < (arraylength - 1)) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);
        if (cnt < (arraylength - 1)) {
            for (int i = cnt + 1; i < arraylength; i++) {
                returnVal[i] = "";
            }
        }

        return returnVal;
    }

    /**
     * 문자열을 다양한 문자셋(EUC-KR[KSC5601],UTF-8..)을 사용하여 인코딩하는 기능 역으로 디코딩하여 원래의 문자열을
     * 복원하는 기능을 제공함 String temp = new String(문자열.getBytes("바꾸기전 인코딩"),"바꿀 인코딩");
     * String temp = new String(문자열.getBytes("8859_1"),"KSC5601"); => UTF-8 에서
     * EUC-KR
     *
     * @param srcString
     *            - 문자열
     * @param srcCharsetNm
     *            - 원래 CharsetNm
     * @param charsetNm
     *            - CharsetNm
     * @return 인(디)코딩 문자열
     * @exception MyException
     * @see
     */
    public static String getEncdDcd(String srcString, String srcCharsetNm, String cnvrCharsetNm) {

	    String rtnStr = null;

	    if (srcString == null){
	    	return null;
	    }

	    try {
	        rtnStr = new String(srcString.getBytes(srcCharsetNm), cnvrCharsetNm);
	    } catch (UnsupportedEncodingException e) {
	        rtnStr = null;
	    }

	    return rtnStr;
    }

    /**
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    public static synchronized String getTimeStamp() {

	    String rtnStr = null;

	    // 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
	    String pattern = "yyyyMMddHHmmssSSS";

	    try {
	        SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
	        Timestamp ts = new Timestamp(System.currentTimeMillis());

	        rtnStr = sdfCurrent.format(ts.getTime());
	    } catch (Exception ex) {
	    	// LogUtil.printTrace(log, ex);
	    }

	    return rtnStr;
    }


    public static String insertLeftChar(String source, int length, char ch)
    {
        StringBuffer temp = new StringBuffer(length);
        if(source.length() <= length)
        {
            for(int i = 0; i < length - source.length(); i++){
            	temp.append(ch);
            }

            temp.append(source);
        }
        return temp.toString();
    }

    public static String substring(String sStr, int startIndex, int endIndex) throws Exception{
        String rtn = "";
        int sStrLen = 0;

        if(startIndex > endIndex){
        	throw new Exception("StringUtil.substring() : startIndex가 endIndex보다 큽니다.");
        }
        try{
            if(!isEmpty(sStr)){
                sStrLen = sStr.length();
                if(sStrLen < startIndex){
                    rtn = "";
                }else if(sStrLen < endIndex){
                    rtn = sStr.substring(startIndex, sStrLen);
                }else{
                    rtn = sStr.substring(startIndex, endIndex);
                }
            }
        }catch(Exception e){
        	// 
        }
        return rtn;
    }

    /**
     * 주어진 문자열을 트림(Trim)한다.<br>
     * 주어진 문자열이 null 경우 비어있는(Empty) 문자열을 리턴한다.
     *
     * @param s String
     * @return String
     */
    public static String trim(String s) {
        if (s == null) {
            return "";
        }
        else {
            return s.trim();
        }
    }

    /**
     * 주어진 문자열을 트림(Trim)한다.<br>
     * 주어진 문자열이 null 경우 String rs 문자열을 리턴한다.
     *
     * @param s String
     * @return String
     */
    public static String trim(String s,String rs) {
        if (s == null) {
            return rs;
        }
        else {
            return s.trim();
        }
    }

    /**
     * 주어진 문자열에 prefix를 앞에 붙인다.<br>
     * 단, 이미 문자열이 prefix로 시작되지 않는 경우.
     *
     * @param s String
     * @param prefix String
     * @return String
     */
    public static String addPrefix(String s, String prefix) {
        if (s == null) {
            return prefix;
        }
        else {
            if (s.startsWith(prefix)) {
                return s;
            }
            else {
                return prefix + s;
            }
        }
    }

    /**
     * 주어진 문자열에 suffix를 뒤에 붙인다.<br>
     * 단, 이미 문자열이 suffix로 끝나지 않는 경우.
     *
     * @param s String
     * @param suffix String
     * @return String
     */
    public static String addSuffix(String s, String suffix) {
        if (s == null) {
            return suffix;
        }
        else {
            if (s.endsWith(suffix)) {
                return s;
            }
            else {
                return s + suffix;
            }
        }
    }

    /**
     * 주어진 문자열의 길이를 리턴한다.<br>
     * null인 경우 0을 리턴한다.
     *
     * @param s String
     * @return int
     */
    public static int getLength(String s) {
        if (s == null) {
            return 0;
        }
        else {
            return s.length();
        }
    }

    /**
     * 주어진 문자열을 파싱한 후 int형으로 리턴한다.<br>
     * 주어진 문자열을 파싱하지 못하는 경우 Integer.MIN_VALUE값을 리턴한다.
     *
     * @param s String
     * @return int
     */
    public static int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception e) {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * 주어진 문자열을 파싱한 후 int형으로 리턴한다.<br>
     * 주어진 문자열을 파싱하지 못하는 경우 기본 값을 리턴한다.
     *
     * @param s String
     * @param def 기본 값
     * @return int
     */
    public static int parseInt(String s, int def) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception e) {
            return def;
        }
    }

    /**
     * 주어진 문자열을 파싱한 후 long형으로 리턴한다.<br>
     * 주어진 문자열을 파싱하지 못하는 경우 Long.MIN_VALUE값을 리턴한다.
     *
     * @param s String
     * @return int
     */
    public static long parseLong(String s) {
        return parseLong(s, Long.MIN_VALUE);
    }

    /**
     * 주어진 문자열을 파싱한 후 long형으로 리턴한다.<br>
     * 주어진 문자열을 파싱하지 못하는 경우 기본 값을 리턴한다.
     *
     * @param s String
     * @param def 기본 값
     * @return long
     */
    public static long parseLong(String s, long def) {
        try {
            return Long.parseLong(s);
        }
        catch (Exception e) {
            return def;
        }
    }

    /**
     * 주어진 문자열을 파싱한 후 double형으로 리턴한다.<br>
     * 주어진 문자열을 파싱하지 못하는 경우 Double.NaN값을 리턴한다.
     *
     * @param s String
     * @return double
     */
    public static double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        }
        catch (Exception e) {
            return Double.NaN;
        }
    }

    /**
     * 주어진 문자열을 파싱한 후 double형으로 리턴한다.<br>
     * 주어진 문자열을 파싱하지 못하는 경우 기본 값을 리턴한다.
     *
     * @param s String
     * @param def 기본 값
     * @return long
     */
    public static double parseDouble(String s, double def) {
        try {
            return Double.parseDouble(s);
        }
        catch (Exception e) {
            return def;
        }
    }

	/**
	 * 원본 String을 특정 char로 채워서 지정된 길이의 String을 만듦.
	 *
	 * @param org		원본 String
	 * @param pattern	채우고자 하는 char
	 * @param tar_len	채워진 전체 String을 길이
	 * @return 특정 char로 채워진 String
	 */
	public static String fill(String org, char pattern, int tar_len) {
		if(org == null){
			org = "";
		}

		int org_len = org.length();
		if(org_len == tar_len){
			return org;
		}
		else if(org_len > tar_len){
			return org.substring(0, tar_len);
		}

		StringBuffer sb = new StringBuffer(org);
		for(int i=org_len; i<tar_len; i++){
			sb.append(pattern);
		}

		return sb.toString();
	}

	/**
     * leftPadByte
     *
     */
    public static String leftPadByte(String str, int size, String charset, String padStr, boolean isTrim, boolean isCutStr) throws Exception {
        byte[] strByte = null;

        if(str == null){
        	str = "";
        }
        if(isEmpty(padStr)){
        	padStr = " ";
        }
        if(isTrim){
        	str = str.trim();
        }

        if(charset != null){
        	strByte = str.getBytes(charset);
        }else{
        	strByte = str.getBytes();
        }

        int pads = size - strByte.length;
        if(pads == 0){
        	return str;
        }else if(pads < 0){
        	if(isCutStr){
        		pads = size;
        		return byteToString(strByte, 0, pads, charset);
        	}else{
//        		log.debug("str : [" + str + "][" + size + "]");
        		throw new Exception("입력된 문자의 길이가 너무 깁니다.");
        	}
        }else{
        	return padding(pads, padStr.charAt(0)).concat(str);
        }
    }

    /**
     * leftPadByte
     *
     */
    public static String leftPadByte(String str, int size, String charset) throws Exception {
        return leftPadByte(str, size, charset, null, false, false);
    }

    /**
     * leftPadByte
     *
     */
    public static String leftPadByte(String str, int size) throws Exception {
    	return leftPadByte(str, size, null, null, false, false);
    }

    /**
     * <p>Left pad a String with spaces (' ').</p>
     *
     * <p>The String is padded to the size of <code>size<code>.</p>
     *
     * <pre>
     * StringUtils.leftPad(null, *)   = null
     * StringUtils.leftPad("", 3)     = "   "
     * StringUtils.leftPad("bat", 3)  = "bat"
     * StringUtils.leftPad("bat", 5)  = "  bat"
     * StringUtils.leftPad("bat", 1)  = "bat"
     * StringUtils.leftPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @return left padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    /**
     * <p>Left pad a String with a specified character.</p>
     *
     * <p>Pad to a size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.leftPad(null, *, *)     = null
     * StringUtils.leftPad("", 3, 'z')     = "zzz"
     * StringUtils.leftPad("bat", 3, 'z')  = "bat"
     * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtils.leftPad("bat", 1, 'z')  = "bat"
     * StringUtils.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padChar  the character to pad with
     * @return left padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     * @since 2.0
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
//            return null;
        	str = "";
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return padding(pads, padChar).concat(str);
    }

    /**
     * <p>Left pad a String with a specified String.</p>
     *
     * <p>Pad to a size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.leftPad(null, *, *)      = null
     * StringUtils.leftPad(null, 3, "z")      = "zzz"
     * StringUtils.leftPad("", 3, "z")      = "zzz"
     * StringUtils.leftPad("bat", 3, "yz")  = "bat"
     * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtils.leftPad("bat", 1, "yz")  = "bat"
     * StringUtils.leftPad("bat", -1, "yz") = "bat"
     * StringUtils.leftPad("bat", 5, null)  = "  bat"
     * StringUtils.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padStr  the String to pad with, null or empty treated as single space
     * @return left padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
//            return null;
        	str = "";
        }
        str = str.trim();
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    /**
     * <p>Returns padding using the specified delimiter repeated
     * to a given length.</p>
     *
     * <pre>
     * StringUtils.padding(0, 'e')  = ""
     * StringUtils.padding(3, 'e')  = "eee"
     * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
     * </pre>
     *
     * <p>Note: this method doesn't not support padding with
     * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
     * as they require a pair of <code>char</code>s to be represented.
     * If you are needing to support full I18N of your applications
     * consider using {@link #repeat(String, int)} instead.
     * </p>
     *
     * @param repeat  number of times to repeat delim
     * @param padChar  character to repeat
     * @return String with repeated character
     * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
     * @see #repeat(String, int)
     */
    private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
        if (repeat < 0) {
            throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
        }
        char[] buf = new char[repeat];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = padChar;
        }
        return new String(buf);
    }

    /**
     * rightPadByte
     *
     */
    public static String rightPadByte(String str, int size, String charset, String padStr, boolean isTrim, boolean isCutStr) throws Exception {
        byte[] strByte = null;

        if(str == null){
        	str = "";
        }
        if(isEmpty(padStr)){
        	padStr = " ";
        }
        if(isTrim){
        	str = str.trim();
        }

        if(charset != null){
        	strByte = str.getBytes(charset);
        }else{
        	strByte = str.getBytes();
        }

        int pads = size - strByte.length;
        if(pads == 0){
        	return str;
        }else if(pads < 0){
        	if(isCutStr){
        		pads = size;
        		return byteToString(strByte, 0, pads, charset);
        	}else{
//        		log.debug("str : [" + str + "][" + size + "]");
        		throw new Exception("입력된 문자의 길이가 너무 깁니다.");
        	}
        }else{
        	return str.concat(padding(pads, padStr.charAt(0)));
        }
    }

    /**
     * rightPadByte
     *
     */
    public static String rightPadByte(String str, int size, String charset) throws Exception {
        return rightPadByte(str, size, charset, null, false, false);
    }

    /**
     * rightPadByte
     *
     */
    public static String rightPadByte(String str, int size) throws Exception {
    	return rightPadByte(str, size, null, null, false, false);
    }

    /**
     * <p>Right pad a String with spaces (' ').</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *)   = null
     * StringUtils.rightPad("", 3)     = "   "
     * StringUtils.rightPad("bat", 3)  = "bat"
     * StringUtils.rightPad("bat", 5)  = "bat  "
     * StringUtils.rightPad("bat", 1)  = "bat"
     * StringUtils.rightPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    /**
     * <p>Right pad a String with a specified character.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *, *)     = null
     * StringUtils.rightPad("", 3, 'z')     = "zzz"
     * StringUtils.rightPad("bat", 3, 'z')  = "bat"
     * StringUtils.rightPad("bat", 5, 'z')  = "batzz"
     * StringUtils.rightPad("bat", 1, 'z')  = "bat"
     * StringUtils.rightPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padChar  the character to pad with
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     * @since 2.0
     */
    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
//            return null;
        	str = "";
        }
        int pads = size - str.length();
//        int pads = size - str.getBytes().length;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(padding(pads, padChar));
    }

    /**
     * <p>Right pad a String with a specified String.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *, *)      = null
     * StringUtils.rightPad("", 3, "z")      = "zzz"
     * StringUtils.rightPad("bat", 3, "yz")  = "bat"
     * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtils.rightPad("bat", 1, "yz")  = "bat"
     * StringUtils.rightPad("bat", -1, "yz") = "bat"
     * StringUtils.rightPad("bat", 5, null)  = "bat  "
     * StringUtils.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padStr  the String to pad with, null or empty treated as single space
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad(String str, int size, String padStr) {
        if (str == null) {
//            return null;
            str = "";
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
//        int strLen = str.getBytes().length;
        int pads = size - strLen;
        if (pads <= 0) {
        	System.out.println("@@@");
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
        	System.out.println("###");
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
        	System.out.println("if");
            return str.concat(padStr);
        } else if (pads < padLen) {
        	System.out.println("else if");
            return str.concat(padStr.substring(0, pads));
        } else {
        	System.out.println("else");
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            System.out.println("Sttttt [" + String.format("0x%02X", (int)padding[0]) + "]");
            return str.concat(new String(padding));
        }
    }

    /**
     * <p>Right pad a String with spaces (' ').</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *)   = null
     * StringUtils.rightPad("", 3)     = "   "
     * StringUtils.rightPad("bat", 3)  = "bat"
     * StringUtils.rightPad("bat", 5)  = "bat  "
     * StringUtils.rightPad("bat", 1)  = "bat"
     * StringUtils.rightPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad2(String str, int size) {
        return rightPad2(str, size, ' ');
    }

    /**
     * <p>Right pad a String with a specified character.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *, *)     = null
     * StringUtils.rightPad("", 3, 'z')     = "zzz"
     * StringUtils.rightPad("bat", 3, 'z')  = "bat"
     * StringUtils.rightPad("bat", 5, 'z')  = "batzz"
     * StringUtils.rightPad("bat", 1, 'z')  = "bat"
     * StringUtils.rightPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padChar  the character to pad with
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     * @since 2.0
     */
    public static String rightPad2(String str, int size, char padChar) {
        if (str == null) {
//            return null;
        	str = "";
        }
        int pads = size - str.getBytes().length;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad2(str, size, String.valueOf(padChar));
        }
        return str.concat(padding(pads, padChar));
    }

    /**
     * <p>Right pad a String with a specified String.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *, *)      = null
     * StringUtils.rightPad("", 3, "z")      = "zzz"
     * StringUtils.rightPad("bat", 3, "yz")  = "bat"
     * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtils.rightPad("bat", 1, "yz")  = "bat"
     * StringUtils.rightPad("bat", -1, "yz") = "bat"
     * StringUtils.rightPad("bat", 5, null)  = "bat  "
     * StringUtils.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padStr  the String to pad with, null or empty treated as single space
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad2(String str, int size, String padStr) {
        if (str == null) {
//            return null;
            str = "";
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.getBytes().length;
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

	 /**
     * 반각문자로 변경한다
     * @param src 변경할값
     * @return String 변경된값
     */
    public static String toHalfChar(String src)
    {
        StringBuffer strBuf = new StringBuffer();
        char c = 0;
        int nSrcLength = src.length();
        for (int i = 0; i < nSrcLength; i++)
        {
            c = src.charAt(i);
            //영문이거나 특수 문자 일경우.
            if (c >= '！' && c <= '～')
            {
                c -= 0xfee0;
            }
            else if (c == '　')
            {
                c = 0x20;
            }
            // 문자열 버퍼에 변환된 문자를 쌓는다
            strBuf.append(c);
        }
        return strBuf.toString();
    }


    /**
     * 전각문자로 변경한다.
     * @param src 변경할값
     * @return String 변경된값
     */
    public static String toFullChar(String src)
    {
        // 입력된 스트링이 null 이면 null 을 리턴
        if (src == null){
        	return null;
        }

        // 변환된 문자들을 쌓아놓을 StringBuffer 를 마련한다
        StringBuffer strBuf = new StringBuffer();
        char c = 0;
        int nSrcLength = src.length();
        for (int i = 0; i < nSrcLength; i++)
        {
            c = src.charAt(i);
            //영문이거나 특수 문자 일경우.
            if (c >= 0x21 && c <= 0x7e)
            {
                c += 0xfee0;
            }
            //공백일경우
            else if (c == 0x20)
            {
                c = 0x3000;
            }
            // 문자열 버퍼에 변환된 문자를 쌓는다
            strBuf.append(c);
        }
        return strBuf.toString();
    }

	/**
	 * input값이 null이면 str을 반환 null이 아니면 input값을 반환
	 *
	 * @param input
	 * @param str
	 * @return
	 * @return String
	 */
	public static String NVL(String input, String str) {
	    if(input == null) {
	        return str;
	    } else {
	        return input;
	    }
	}


	/**
     * String.valueOf()로 값을 가져왔을때 빈 값인 경우 String 형식의 "null"을 반환하는데
     * 이 경우 null 체크는 "null"과 비교한다.
     *
     * @param input
     * @param str
     * @return
     * @return String
     */
    public static String strNullChk(String input, String str) {
        if("null".equals(input)) {
            return str;
        } else {
            return input;
        }
    }

	/**
	 * 입력한 문자가 입력한 길이와 같을 경우 TRUE, 그 외의 경우에는 FALSE를 리턴한다.
	 *
	 * @param input    (길이를 체크할 문자열)
	 * @param length   (원하는 길이)
	 * @return
	 * @return boolean (결과)
	 */
	public static boolean validStringLength(String input, int length) {
		boolean result = false;

		if(input == null) {
			result = false;
	    } else if(input.length() == length) {
	    	result = true;
	    } else {
	    	result = false;
	    }
	    return result;
	}

	/**
	 * byte 배열에서 일부의 데이터를 읽어 String으로 리턴하는 함수
	 *
	 * @param input    문자1자의 byte 형태 (예)String.getBytes("KSC5601")
	 * @return         10진수 형태의 String 문자열
	 */
    public static String byteToString(byte[] src, int offset, int len) {
    	byte[] dest = new byte[len];
    	System.arraycopy(src, offset, dest, 0, len);
    	return new String(dest);
    }
	/**
	 * byte 배열에서 일부의 데이터를 읽어 캐릭터 셋을 적용하여 String으로 리턴하는 함수
	 *
	 * @param input    문자의 byte 형태 (예)String.getBytes("KSC5601")
	 * @return         10진수 형태의 String 문자열
	 */
    public static String byteToString(byte[] src, int offset, int len, String charset) throws Exception{
    	byte[] dest = new byte[len];
    	System.arraycopy(src, offset, dest, 0, len);
    	String utfDesc = null;
    	if("CP933".equals(charset)) {
    		utfDesc = cp933ToUtf8(dest);
		} else if("euc-kr".equals(charset)) {
			utfDesc = euckrToUtf8(dest);
		}
    	return utfDesc;
    }
	/**
	 * byte 배열에서 일부의 데이터를 읽어 byte 배열로 리턴하는 함수
	 *
	 * @param input    문자의 byte 형태 (예)String.getBytes("KSC5601")
	 * @return         byte 배열
	 */
    public static byte[] byteToByte(byte[] src, int offset, int len) {
    	byte[] dest = new byte[len];
    	System.arraycopy(src, offset, dest, 0, len);
    	return dest;
    }
	/**
	 * 두개의 Byte 배열을 합치는 함수
	 *
	 * @param input    문자1자의 byte 형태 (예)String.getBytes("KSC5601")
	 * @return         byte 배열
	 */
    public static byte[] catByteToByte(byte[] src1, byte[] src2) {
    	byte[] dest = new byte[src1.length + src2.length];
    	System.arraycopy(src1, 0, dest, 0, src1.length);
    	System.arraycopy(src2, 0, dest, src1.length, src2.length);
    	return dest;
    }

    /**
	 * 주어진 byte 배열을 정해진 길이만큼 0x00으로 채우는 함수
	 *
	 * @param input    문자1자의 byte 형태 (예)String.getBytes("KSC5601")
	 * @return         byte 배열
	 */
    public static byte[] rightPadByteNull(byte[] src, int len) {
    	byte[] dest = new byte[len];
    	byte charNull = (byte)0x00;
    	int srcLen = src.length;
    	System.arraycopy(src, 0, dest, 0, srcLen);
    	for(int i = 0; i < len - srcLen; i++) {
    		dest[srcLen + i] = charNull;
    	}
    	return dest;
    }

    /**
	 * 소수점이 있는 값을 소수점 중심으로 좌우에 "0"을 길이만큼 채우는 함수
	 *
	 * @param input    소수점이 들어가 있는 숫자(없어도 무방함)
	 * @return         문자형
	 */
    public static String leftRightDotPad(String source, int left, int right) {
    	StringBuilder desc = new StringBuilder();
    	if(StringUtils.isEmpty(source))
    		source = "";
    	String src = source.trim();
    	int pos = src.indexOf('.');
    	if(pos < 0){
        	if(src.startsWith("-")){
        		desc.append("-" + leftPad(src.substring(1), left -1, "0"));
        	}else{
            	desc.append(leftPad(src, left, "0"));
        	}
        	desc.append(".");
        	desc.append(rightPad("", right, "0"));
    	} else if(pos == 0){
        	desc.append(leftPad("", left, "0"));
        	desc.append(".");
        	if(src.length() == 1) {
            	desc.append(rightPad("", right, "0"));
        	} else {
        		desc.append(rightPad(src.substring(1), right, "0"));
        	}
    	} else if(pos == src.length() - 1){
    		desc.append(leftPad(src.substring(0, src.length()-1), left, "0"));
        	desc.append(".");
        	desc.append(rightPad("", right, "0"));
    	} else {
        	String[] sArr = split(src.trim(), ".");
        	if(sArr[0].startsWith("-")){
        		desc.append("-" + leftPad(sArr[0].substring(1), left -1, "0"));
        	}else{
            	desc.append(leftPad(sArr[0], left, "0"));
        	}
        	desc.append(".");
        	desc.append(rightPad(sArr[1], right, "0"));
    	}
    	return desc.toString();

    }
    /**
	 * leftRightDotPad 후 길이만큼 절삭
	 *
	 * @param input    소수점이 들어가 있는 숫자(없어도 무방함)
	 * @return         문자형
	 */
    public static String leftRightDotPadCutting(String source, int left, int right) {
    	String[] arr  = StringUtils.split(leftRightDotPad(source, left, right), ".");

    	return new StringBuilder().append(StringUtils.right(arr[0], left))
    		.append(".").append(StringUtils.left(arr[1], right)).toString();
    }
    /**
	 * "euc-kr"형태의 Byte 배열을 UTF-8 문자형으로 변환
	 *
	 * @param input    euc-kr 바이트 배열
	 * @return         문자형
	 */
    public static String euckrToUtf8(byte[] src) throws Exception{
    	String desc = new String(src, "euc-kr");
    	return new String(desc.getBytes("UTF-8"), "UTF-8");

    }
    /**
	 * "cp933"형태의 Byte 배열을 UTF-8 문자형으로 변환
	 *
	 * @param input    euc-kr 바이트 배열
	 * @return         문자형
	 */
    public static String cp933ToUtf8(byte[] src) throws Exception{
    	String desc = new String(src, "cp933");
    	return new String(desc.getBytes("UTF-8"), "UTF-8");

    }

    /**
	 * 입력한 문자열을 입력한  int 배열에 정의 길이 단위로 문자열 배열을 만들어 반환한다.
	 *
	 * @param sData
	 * @param nArrIdx
	 * @return
	 * @return String[]
	 */
	public static String[] split(byte[] bListData, int[] nArrIdx, String charset) throws Exception{
		if (bListData == null) {
			return new String[0];
		}

		byte[] bListDest = null;
		int nSrcPos = 0;
		String[] o_sListData = new String[nArrIdx.length];

		for (int i=0; i<nArrIdx.length; i++) {
			bListDest = new byte[nArrIdx[i]];
			System.arraycopy(bListData, nSrcPos, bListDest, 0, nArrIdx[i]);
			nSrcPos += nArrIdx[i];
			if("CP933".equals(charset)) {
				o_sListData[i] = cp933ToUtf8(bListDest);
			} else if("euc-kr".equals(charset)) {
				o_sListData[i] = euckrToUtf8(bListDest);
			}
			else{
				o_sListData[i] = new String(bListDest, charset);
			}
		}

		return o_sListData;
	}

    /**
	 * 입력한 문자열을 입력한  int 배열에 정의 길이 단위로 문자열 배열을 만들어 0x00 값을 제거하고 반환한다.
	 *
	 * @param sData
	 * @param nArrIdx
	 * @return
	 * @return String[]
	 */
	public static String[] splitRmvNull(byte[] bListData, int[] nArrIdx, String charset) throws Exception{
		if (bListData == null) {
			return new String[0];
		}
		byte[] bListDest = null;
		int nSrcPos = 0;
		String[] o_sListData = new String[nArrIdx.length];
		int zero = 0;

		for (int i=0; i<nArrIdx.length; i++) {
			bListDest = new byte[nArrIdx[i]];
			System.arraycopy(bListData, nSrcPos, bListDest, 0, nArrIdx[i]);
			nSrcPos += nArrIdx[i];
			//byte 배열에서 0x00을 제거하고 실 데이터만 리턴한다.
			byte[] bDestRmvNull = null;
			int pos = zero;
			for(int j = 0; j < bListDest.length; j++){
				if(bListDest[j] == (byte)0x00) {
					pos = j;
					break;
				}
			}
			if(pos == 0) {
				pos = bListDest.length;
			}
			bDestRmvNull = new byte[pos];
			System.arraycopy(bListDest, 0, bDestRmvNull, 0, pos);

			if("CP933".equals(charset)) {
				o_sListData[i] = cp933ToUtf8(bDestRmvNull);
			} else if("euc-kr".equals(charset)) {
				o_sListData[i] = euckrToUtf8(bDestRmvNull);
			}
		}

		return o_sListData;
	}

	/**
	 * 문자 NUMERIC Check
	 *
	 */
    public static boolean chrNCheck(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        else {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
	 * 문자 SPECIAL Check
	 *
	 */
    public static boolean chrSCheck(String s) {
    	boolean result = false;

        if (s == null || "".equals(s)) {
        	result = false;
        } else {
        	Pattern p = Pattern.compile("[^/\\-\\?\\:\\(\\)\\.\\,\\'\\+\\{\\}\\ ]");      // 특수문자가 아닌 패턴을 찾음
    		Matcher m = p.matcher(s);
    		if(m.find()) {
    			result = false;       // 다른 문자가 발견되었슴
    		} else {
    			result = true;        // 다른문자 없음
    		}
        }
        return result;
    }

    /**
	 * 문자 ALPHABETIC Check
	 *
	 */
    public static boolean chrACheck(String s) {
    	boolean result = false;

        if (s == null || "".equals(s)) {
        	result = false;
        } else {
    		Pattern p = Pattern.compile("[^a-zA-Z\\ ]");      // a-z 또는 A-Z가 아닌 패턴을 찾음
    		Matcher m = p.matcher(s);
    		if(m.find()) {
    			result = false;       // 다른 문자가 발견되었슴
    		} else {
    			result = true;        // 다른문자 없음
    		}
        }
        return result;
    }

    /**
	 * 문자 ALPHA-NUMBERIC Check
	 *
	 */
    public static boolean chrANCheck(String s) {
    	boolean result = false;

        if (s == null || "".equals(s)) {
        	result = false;
        } else {
        	Pattern p = Pattern.compile("[^a-zA-Z0-9\\ ]");      // a-z 또는 A-Z 또는 0-9 가 아닌 패턴을 찾음
    		Matcher m = p.matcher(s);
    		if(m.find()) {
    			result = false;       // 다른 문자가 발견되었슴
    		} else {
    			result = true;        // 다른 문자 없음
    		}
        }
        return result;
    }

    /**
	 * 문자 SPECIAL-NUMBERIC Check
	 *
	 */
    public static boolean chrSNCheck(String s) {
    	boolean result = false;

        if (s == null || "".equals(s)) {
        	result = false;
        } else {
        	Pattern p = Pattern.compile("[^0-9\\/\\-\\?\\:\\(\\)\\.\\,\\'\\+\\{\\}]");      // 숫자나 특수만가 아닌 패턴을 찾음
    		Matcher m = p.matcher(s);
    		if(m.find()) {
    			result = false;       // 다른 문자가 발견되었슴
    		} else {
    			result = true;        // 다른 문자 없음
    		}
        }
        return result;
    }

    /**
	 * 문자  ALPHA-NUMBERIC SPECIAL SPACE Check
	 *
	 */
    public static boolean chrANSCheck(String s) {
    	boolean result = false;

    	String CheckString = "[^a-zA-Z0-9\\/\\-\\?\\:\\(\\)\\.\\,\\'\\+\\{\\}\\ \\\n\\\r]";
        if (s == null || "".equals(s)) {
        	result = false;
        } else {
        	Pattern p = Pattern.compile(CheckString);      // a-z 또는 A-Z 또는 0-9 space 가 아닌 패턴을 찾음
    		Matcher m = p.matcher(s);
    		if(m.find()) {
    			result = false;       // 다른 문자가 발견되었슴
    		} else {
    			result = true;        // 다른 문자 없음
    		}
        }
        return result;
    }

    /**
     * SWIFT 문자  ALPHA-NUMBERIC SPECIAL SPACE Check
     *
     * swift 가능문자 인경우 true
     * swift 불가능문자 인경우 false
     * @param s
     * @return
     */
    public static boolean chrSwiftANSCheck(String s) {
    	boolean result = false;

    	//== 특수문자만 구성검증
    	if ( "/".equals(trim(s)) ) return false;
    	if ( ":".equals(trim(s)) ) return false;
    	if ( "-".equals(trim(s)) ) return false;

    	//== 첫번째 문자 검증
    	if ( s.indexOf(":") == 0 ) return false;
    	if ( s.indexOf("-") == 0 ) return false;


//    	String CheckString = "[^a-zA-Z0-9\\/\\-\\?\\:\\(\\)\\.\\,\\'\\+\\{\\}\\ ]";
    	String CheckString = "[^a-zA-Z0-9\\/\\-\\?\\:\\(\\)\\.\\,\\'\\+\\{\\}\\=\\!\\%\\&\\*\\<\\>\\;\\{\\@\\#\\_\\ ]";
        if (s == null || "".equals(s)) {
        	result = false;
        } else {
        	Pattern p = Pattern.compile(CheckString);      // a-z 또는 A-Z 또는 0-9 space 가 아닌 패턴을 찾음
    		Matcher m = p.matcher(s);
    		if(m.find()) {
    			result = false;       // 다른 문자가 발견되었슴
    		} else {
    			result = true;        // 다른 문자 없음
    		}
        }
        return result;
    }

    /**
	 * 문자 NUMERIC-SPACE Check
	 *
	 */
	public static boolean chrNSCheck(String s) {
		boolean result = false;

        if (s == null || "".equals(s)) {
        	result = false;
        }
        else {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!Character.isDigit(c) && !Character.isSpaceChar(c)) {
                	result = false;
                }
            }
            result = true;
        }
        return result;
    }

    /**
	 * 3개 문자열 조립
	 *
	 */
	public static String catString(String str1, String str2, String str3) {
		StringBuilder sbDesc = new StringBuilder();
		sbDesc.append(str1).append(str2).append(str3);
		return sbDesc.toString();
	}

    /**
	 * 4개 문자열 조립
	 *
	 */
	public static String catString(String str1, String str2, String str3,String str4) {
		StringBuilder sbDesc = new StringBuilder();
		sbDesc.append(str1).append(str2).append(str3).append(str4);
		return sbDesc.toString();
	}

    /**
	 * 5개 문자열 조립
	 *
	 */
	public static String catString(String str1, String str2, String str3,String str4,String str5) {
		StringBuilder sbDesc = new StringBuilder();
		sbDesc.append(str1).append(str2).append(str3).append(str4).append(str5);
		return sbDesc.toString();
	}

	/**
	 * "ms949"형태의 Byte 배열을 UTF-8 문자형으로 변환
	 *
	 * @param input    euc-kr 바이트 배열
	 * @return         문자형
	 */
    public static String ms949ToUtf8(byte[] src) throws Exception{
    	String desc = new String(src, "MS949");
    	return new String(desc.getBytes("UTF-8"), "UTF-8");

    }

    /**
     * rightPadByteNotTrim(String str, int size, String charset)
     *
     */
    public static String rightPadByteNotTrim(String str, int size, String charset) throws Exception {
    	return rightPadByte(str, size, charset, null, false, false);
    }

    /**
     * rightPadByteNotTrim(String str, int size, String charset, boolean isCutStr)
     *
     */
    public static String rightPadByteNotTrim(String str, int size, String charset, boolean isCutStr) throws Exception {
    	return rightPadByte(str, size, charset, null, false, isCutStr);
    }

    /**
	 * 입력한 문자열을 입력한  int 배열에 정의 길이 단위로 문자열 배열을 만들어 0x00 값을 제거하고 반환한다.
	 *
	 * @param sData
	 * @param nArrIdx
	 * @return
	 * @return String[]
	 */
	public static String removeNull(byte[] bListData, String charset) throws Exception{
		String destRmvNull = "";
		byte[] bDestRmvNull = null;
		for(int j = 0; j < bListData.length; j++){
			if(bListData[j] == (byte)0x00) {
				bDestRmvNull = new byte[j];
				System.arraycopy(bListData, 0, bDestRmvNull, 0, j);
				break;
			}
		}
		if(bDestRmvNull.length != 0) {
			if("CP933".equalsIgnoreCase(charset)) {
				destRmvNull = cp933ToUtf8(bDestRmvNull);
			} else if("euc-kr".equalsIgnoreCase(charset)) {
				destRmvNull = euckrToUtf8(bDestRmvNull);
			} else if("UTF-8".equalsIgnoreCase(charset)) {
				destRmvNull = new String(bDestRmvNull, "UTF-8");
			} else if("MS949".equalsIgnoreCase(charset)) {
				destRmvNull = ms949ToUtf8(bDestRmvNull);
			}
		}
		return destRmvNull;
	}

    /**
     * rightPadByte(String str, int size, boolean isCutStr)
     *
     */
    public static String rightPadByte(String str, int size, boolean isCutStr) throws Exception {
        return rightPadByte(str, size, null, null, true, isCutStr);
    }

    /**
     * rightPadByte(String str, int size, String charset, boolean isCutStr)
     *
     */
    public static String rightPadByte(String str, int size, String charset, boolean isCutStr) throws Exception {
        return rightPadByte(str, size, charset, null, true, isCutStr);
    }

    /**
     * leftPadByte(String str, int size, boolean isCutStr)
     *
     */
    public static String leftPadByte(String str, int size, boolean isCutStr) throws Exception {
        return leftPadByte(str, size, null, null, true, isCutStr);
    }

    /**
     * leftPadByte(String str, int size, String charset, boolean isCutStr)
     *
     */
    public static String leftPadByte(String str, int size, String charset, boolean isCutStr) throws Exception {
        return leftPadByte(str, size, charset, null, true, isCutStr);
    }
    /**
   	 * SHA256 암호화
   	 * 조작자 비밀번호
   	 * @param data
   	 * @return
   	 */
   	public static String encBySha256(String data) throws Exception{
   		String retVal = "";

   		MessageDigest md = MessageDigest.getInstance("SHA-256");
   		md.update(data.getBytes());

   		byte byteData[] = md.digest();
   		StringBuffer sb = new StringBuffer();

   		for (int i = 0; i < byteData.length; i++) {
   			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
   		}
   		StringBuffer hexString = new StringBuffer();

   		for (int i = 0; i < byteData.length; i++) {
   			String hex = Integer.toHexString(0xff & byteData[i]);

   			if (hex.length() == 1) {
   				hexString.append("0");
   			}
   			hexString.append(hex);
   		}
   		retVal = hexString.toString();

   		return retVal;
   	}


   	/**
   	 * 문자열을 바이트로 cutLen 만큼 자른다.
   	 * byteCutter("가나다", 4) ==> "가"
   	 * @param str 문자열
   	 * @param cutLen size
   	 * @return
   	 * @throws Exception
   	 */
   	public static String byteCutter(String str, int cutLen) throws Exception {
        if(str.getBytes().length > cutLen) {
            StringBuilder builder = new StringBuilder(cutLen);
            int cnt = 0;
            for(char ch:str.toCharArray()) {
                cnt += String.valueOf(ch).getBytes().length;
                if(cnt > cutLen)
                    break;
                builder.append(ch);
            }
            return builder.toString();
        }

        return str;
    }

   	/**
   	 * AES 방식의 복호화
   	 *
   	 * @param message
   	 * @return
   	 * @throws Exception
   	 */
   	public static String decrypt(String	message) throws	Exception {
		SecretKeySpec skeySpec = new SecretKeySpec("the!K$labktuc374".getBytes(), "AES");

		// Instantiate the cipher
		Cipher cipher =	Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);

		byte[] original = cipher.doFinal(hexToByteArray(message));

		return new String(original);

	}

	/**
	 * hex to byte[] : 16진수 문자열을 바이트 배열로 변환한다.
	 *
	 * @param hex
	 * @return
	 */
	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}

		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	/**
	 * Blob 데이터를 String 으로 변환한다.
	 * @param blob
	 * @return
	 * @throws Exception
	 */
	public static String toString(Blob blob) throws Exception {
		String result = "";
    	BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
    	int nBlobSize = (int) blob.length();
    	byte[] bytes = new byte[nBlobSize];
    	in.read(bytes, 0, nBlobSize);
    	in.close();
    	result = new String(bytes);
    	return result;
	}


}

