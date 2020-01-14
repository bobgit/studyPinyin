package github.com.bobgit.study.pinyin.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPUtils {
	private static final Logger logger = LoggerFactory.getLogger(IPUtils.class);

	private static final List<String> IP_HEADER_LIST = Arrays.asList("X-Forwarded-For", "Proxy-Client-IP",
			"WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR");

	private IPUtils() {

	}

/*

	public static final String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
	public static final Pattern pattern = Pattern.compile("^(?:" + _255 + "\\.){3}" + _255 + "$");

	public static String longToIpV4(long longIp) {
		int octet3 = (int) ((longIp >> 24) % 256);
		int octet2 = (int) ((longIp >> 16) % 256);
		int octet1 = (int) ((longIp >> 8) % 256);
		int octet0 = (int) ((longIp) % 256);
		return octet3 + "." + octet2 + "." + octet1 + "." + octet0;
	}

	public static long ipV4ToLong(String ip) {
		String[] octets = ip.split("\\.");
		return (Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16)
				+ (Integer.parseInt(octets[2]) << 8) + Integer.parseInt(octets[3]);
	}

	public static boolean isIPv4Private(String ip) {
		long longIp = ipV4ToLong(ip);
		return (longIp >= ipV4ToLong("10.0.0.0") && longIp <= ipV4ToLong("10.255.255.255"))
				|| (longIp >= ipV4ToLong("172.16.0.0") && longIp <= ipV4ToLong("172.31.255.255"))
				|| longIp >= ipV4ToLong("192.168.0.0") && longIp <= ipV4ToLong("192.168.255.255");
	}

	public static boolean isIPv4Valid(String ip) {
		return pattern.matcher(ip).matches();
	}*/

	public static String getIpAddressForTest(HttpServletRequest request){

		logger.info("=====================================远程地址获取情况================================================");
		StringBuilder sb = new StringBuilder();
		String requestPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ request.getRequestURI();
//		logger.info("客户请求类的情况:{}",JsonUtils.serialize(request));
		Enumeration<String> headerNames = request.getHeaderNames();
		while ((headerNames.hasMoreElements())){
			String headerElement = headerNames.nextElement();
			logger.info("客户请求报文头信息 HeaderNames:{}={}",headerElement,request.getHeader(headerElement));
			sb.append("\r\n<br />").append("客户请求报文头信息 HeaderNames:"+headerElement+"="+request.getHeader(headerElement));
		}
		logger.info("客户请求类getMethod:{}",request.getMethod());
		logger.info("客户请求类getContextPath:{}",request.getContextPath());
		logger.info("客户请求类getHttpServletMapping:{}",JsonUtils.serialize(request.getHttpServletMapping()));
		logger.info("客户请求类getServletPath:{}",request.getServletPath());
		logger.info("客户请求类getRemoteHost:{}",request.getRemoteHost());
		logger.info("客户请求类getRemoteUser:{}",request.getRemoteUser());
		logger.info("客户请求类getLocalAddr:{}",request.getLocalAddr());
		logger.info("客户请求类getRemoteAddr:{}",request.getRemoteAddr());
		logger.info("客户请求类getPathInfo:{}",request.getPathInfo());

		sb.append("\r\n<br />").append("getMethod:"+request.getMethod());
		sb.append("\r\n<br />").append("getContextPath:"+request.getContextPath());
		sb.append("\r\n<br />").append("getHttpServletMapping:"+JsonUtils.serialize(request.getHttpServletMapping()));
		sb.append("\r\n<br />").append("getServletPath:"+request.getServletPath());
		sb.append("\r\n<br />").append("getRemoteHost:"+request.getRemoteHost());
		sb.append("\r\n<br />").append("getRemoteUser:"+request.getRemoteUser());
		sb.append("\r\n<br />").append("getLocalAddr:"+request.getLocalAddr());
		sb.append("\r\n<br />").append("getRemoteAddr:"+request.getRemoteAddr());
		sb.append("\r\n<br />").append("getPathInfo:"+request.getPathInfo());



		String ipAddress = "";
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Forwarded-For");//Squid 服务代理
			logger.info("代理检测 X-Forwarded-For:{}",ipAddress);
			sb.append("\r\n<br />").append("代理检测 X-Forwarded-For:"+ipAddress);
		}

		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");//HTTP_X_FORWARDED_FOR服务代理
			logger.info("代理检测 HTTP_X_FORWARDED_FOR:{}",ipAddress);
			sb.append("\r\n<br />").append("代理检测 HTTP_X_FORWARDED_FOR:"+ipAddress);
		}
		else{
			logger.info("[Squid 服务代理] IP地址为:{}",ipAddress);
		}
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");//apache 服务代理
			logger.info("代理检测 Proxy-Client-IP:{}",ipAddress);
			sb.append("\r\n<br />").append("代理检测 Proxy-Client-IP:"+ipAddress);
		}
		else{
			logger.info("[HTTP_X_FORWARDED_FOR服务代理] IP地址为:{}",ipAddress);
		}
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");//weblogic 服务代理
			logger.info("代理检测 WL-Proxy-Client-IP:{}",ipAddress);
			sb.append("\r\n<br />").append("代理检测 WL-Proxy-Client-IP:"+ipAddress);
		}
		else{
			logger.info("[apache 服务代理] IP地址为:{}",ipAddress);
		}
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("HTTP_CLIENT_IP");//有些代理服务器
			logger.info("代理检测 HTTP_CLIENT_IP:{}",ipAddress);
			sb.append("\r\n<br />").append("代理检测 HTTP_CLIENT_IP:"+ipAddress);
		}
		else{
			logger.info("[weblogic 服务代理] IP地址为:{}",ipAddress);
		}
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Real-IP");//nginx服务代理
			logger.info("代理检测 X-Real-IP:{}",ipAddress);
			sb.append("\r\n<br />").append("代理检测 X-Real-IP:"+ipAddress);
		}
		else{
			logger.info("[有些代理服务器] IP地址为:{}",ipAddress);
		}
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {

			}
			else{
				logger.info("[远程] IP地址为:{}",ipAddress);
				sb.append("\r\n<br />").append("[远程] IP地址为:"+ipAddress);
			}
		}
		else{
			logger.info("[nginx服务代理] IP地址为:{}",ipAddress);
			sb.append("\r\n<br />").append("[nginx服务代理]:"+ipAddress);
		}

		if (StringUtils.isNotBlank(ipAddress)) {
			String[] arr = ipAddress.split(",");
			if(arr!=null){
				for (String ip: arr) {
					if(!StringUtils.equalsIgnoreCase("unknown",ip)){
						ipAddress =StringUtils.replaceChars(ip," ","");
						break;
					}
				}
			}
		}
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = "";
		}
		sb.append("\r\n<br />").append("[最终获取IP结果]:"+ipAddress);

		logger.info("[专门测试]远程IP情况 [{}]访问服务路径:{}",ipAddress,requestPath);
		return sb.toString();
	}


	/**
	 * Java获取客户端真实IP地址,而request.getRemoteAddr（）的方法获取的IP实际上是代理服务器的地址,无法伪造
	 * 获得用户远程地址,尽可能的获取到真实ip，但不能保证一定可以获取到真实ip，而且代理服务器请求头中获取的ip是可伪造的。
	 * 不能确保获得的一定是客户端ip
	 */
	public static String getRemoteAddress(HttpServletRequest request){
		String ipAddress = "";
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Forwarded-For");//Squid 服务代理
		}

		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");//HTTP_X_FORWARDED_FOR服务代理
		}
		else{
			logger.info("[Squid 服务代理] IP地址为:{}",ipAddress);
		}


		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");//apache 服务代理
		}
		else{
			logger.info("[HTTP_X_FORWARDED_FOR服务代理] IP地址为:{}",ipAddress);
		}

		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");//weblogic 服务代理
		}
		else{
			logger.info("[apache 服务代理] IP地址为:{}",ipAddress);
		}


		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("HTTP_CLIENT_IP");//有些代理服务器
		}
		else{
			logger.info("[weblogic 服务代理] IP地址为:{}",ipAddress);
		}


		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Real-IP");//nginx服务代理
		}
		else{
			logger.info("[有些代理服务器] IP地址为:{}",ipAddress);
		}

		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {

			}
			else{
				logger.info("[远程] IP地址为:{}",ipAddress);
			}
		}
		else{
			logger.info("[nginx服务代理] IP地址为:{}",ipAddress);
		}

		if (StringUtils.isNotBlank(ipAddress)) {
			String[] arr = ipAddress.split(",");
			if(arr!=null){
				for (String ip: arr) {
					if(!StringUtils.equalsIgnoreCase("unknown",ip)){
						ipAddress =StringUtils.replaceChars(ip," ","");
						break;
					}
				}
			}
		}
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = "";
		}

		String requestPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ request.getRequestURI();
		logger.info("目前远程IP[{}]访问服务路径:{}",ipAddress,requestPath);
		return ipAddress;

/*
		String ipAddresses = request.getHeader("X-Forwarded-For");//Squid 服务代理
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("Proxy-Client-IP");//apache 服务代理
		}
		else if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("WL-Proxy-Client-IP");//weblogic 服务代理
		}
		else if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("HTTP_CLIENT_IP");//有些代理服务器
		}
		else if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("X-Real-IP");//nginx服务代理
		}
		else{
			ipAddresses = null;
		}
		return ipAddresses != null ? ipAddresses : request.getRemoteAddr();
*/

/*
		String remoteAddr = request.getHeader("X-Real-IP");
		if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		}else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		}else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
		*/
	}
	public static String getSimpleRemoteAddress(HttpServletRequest request) {
		String ipAddress = getRemoteAddress(request);
		String requestPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ request.getRequestURI();
		logger.info("目前简单远程[{}]访问服务路径:{}",ipAddress,requestPath);
		return ipAddress;//获取无法伪造的IP,代理服务器并不穿透,直接获取
	}

	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 *
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		return getRemoteAddress(request);
/*		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
		String ip = "";
		Iterator<String> iterHeader = IPUtils.IP_HEADER_LIST.iterator();
		while (iterHeader.hasNext()) {
			String header = iterHeader.next();
			ip = request.getHeader(header);
			if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
				logger.debug("{} = {}", header, ip);
				break;
			}
		}
		if (StringUtils.isBlank(ip)) {
			ip = request.getRemoteAddr();
			logger.debug("remote addr = {}", ip);
		} else {
			String[] toks = StringUtils.split(ip, ",");
			for (int index = 0; index < toks.length && toks.length > 1; index++) {
				String strIp = toks[index];
				if (!"unknown".equalsIgnoreCase(strIp)) {
					ip = strIp;
					break;
				}
			}
		}
		if (StringUtils.isBlank(ip)) {
			logger.error("无法获取请求方的IP");
		}
		return ip;*/
	}

    /**
     * 获取报文头，没有获取的话，默认赋值
	 * @param request
     * @param headerKey
     * @param defaultValue
     * @return
     */
	public static String getHeaderValueByKey(HttpServletRequest request, String headerKey, String defaultValue) {
		String result = request.getHeader(headerKey);
		if(StringUtils.isBlank(result)){
			result = defaultValue;
		}
		return result;
	}



	/**
	 *获取外网本机的IP地址的方法
	 * @return
	 */
	public static String getV4IP(){
		String ip = "";
		String chinaz = "http://ip.chinaz.com";

		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		try {
			url = new URL(chinaz);
			urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			while((read=in.readLine())!=null){
				inputLine.append(read+"\r\n");
			}
			//System.out.println(inputLine.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
		Matcher m = p.matcher(inputLine.toString());
		if(m.find()){
			String ipstr = m.group(1);
			ip = ipstr;
			//System.out.println(ipstr);
		}
		return ip;
	}



///////////////////////////////////一下全是尝试//////////////////////////////////////

	/**
	 * 获取服务器地址
	 *
	 * @return Ip地址
	 */
	public static String getServerIp() {
		// 获取操作系统类型
		String sysType = System.getProperties().getProperty("os.name");
		String ip;
		logger.info("系统类型:{}",sysType);
		if (sysType.toLowerCase().startsWith("win")) {  // 如果是Windows系统，获取本地IP地址
			String localIP = null;
			try {
				localIP = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				logger.error(e.getMessage(), e);
			}
			if (localIP != null) {
				return localIP;
			}
		} else {
			ip = getIpByEthNum("eth0"); // 兼容Linux
			if (ip != null) {
				return ip;
			}
		}
		return "获取服务器IP错误";
	}

	/**
	 * 根据网络接口获取IP地址
	 * @param ethNum 网络接口名，Linux下是eth0
	 * @return
	 */
	private static String getIpByEthNum(String ethNum) {
		try {
			Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				if (ethNum.equals(netInterface.getName())) {
					Enumeration addresses = netInterface.getInetAddresses();
					while (addresses.hasMoreElements()) {
						ip = (InetAddress) addresses.nextElement();
						if (ip != null && ip instanceof Inet4Address) {
							return ip.getHostAddress();
						}
					}
				}
			}
		} catch (SocketException e) {
			logger.error(e.getMessage(), e);
		}
		return "获取服务器IP错误";
	}

	public static  InetAddress catchLocalIp(){
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
			logger.info("Localaddress:{}",JsonUtils.toJsonString(address));
		} catch (UnknownHostException e) {
			logger.info("尝试获取服务器IP地址失败:"+e.getMessage(),e);
		}
		return address;
	}
	public static  InetAddress catchServicIp(){
		InetAddress address = null;
		try {
			address = InetAddress.getByName("www.baidu.com");
			logger.info("Servicaddress:{}",JsonUtils.toJsonString(address));
		} catch (UnknownHostException e) {
			logger.info("尝试获取服务器IP地址失败:"+e.getMessage(),e);
		}
		return address;
	}
}
